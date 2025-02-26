package com.example.g130ministore.service.impl;

import com.example.g130ministore.entity.User;
import com.example.g130ministore.exception.IncorrectRequestException;
import com.example.g130ministore.repository.UserRepository;
import com.example.g130ministore.service.AuthenticationService;
import com.example.g130ministore.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void changePassword(String oldPassword, String newPassword, String reNewPassword) {
        if (!newPassword.equals(reNewPassword)) {
            throw new IncorrectRequestException("New password and re new password do not match");
        }

        User user = authenticationService.currentUser();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IncorrectRequestException("Incorrect password");
        }

        user.setPasswordHash(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}
