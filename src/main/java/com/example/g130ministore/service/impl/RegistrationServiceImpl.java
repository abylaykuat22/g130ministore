package com.example.g130ministore.service.impl;

import com.example.g130ministore.dto.UserRegisterRequest;
import com.example.g130ministore.entity.Product;
import com.example.g130ministore.entity.Role;
import com.example.g130ministore.entity.User;
import com.example.g130ministore.exception.IncorrectRequestException;
import com.example.g130ministore.exception.UsernameAlreadyExistException;
import com.example.g130ministore.mapper.UserMapper;
import com.example.g130ministore.repository.ProductRepository;
import com.example.g130ministore.repository.RoleRepository;
import com.example.g130ministore.repository.UserRepository;
import com.example.g130ministore.service.ProductService;
import com.example.g130ministore.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void registerUser(UserRegisterRequest request) {
        boolean usernameIsExist = userRepository.findByUsername(request.getUsername()).isPresent();
        if (usernameIsExist) {
            throw new UsernameAlreadyExistException("Username already exist: " + request.getUsername());
        }

        if (!request.getPassword().equals(request.getRePassword())) {
            throw new IncorrectRequestException("Passwords do not match");
        }

        User user = UserMapper.INSTANCE.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findRoleUser();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);
    }
}
