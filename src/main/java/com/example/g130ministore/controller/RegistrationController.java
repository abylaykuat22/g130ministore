package com.example.g130ministore.controller;

import com.example.g130ministore.dto.UserRegisterRequest;
import com.example.g130ministore.exception.IncorrectRequestException;
import com.example.g130ministore.exception.UsernameAlreadyExistException;
import com.example.g130ministore.service.RegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
@Tag(name = "RegistrationController", description = "API для регистрации пользователей")
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/user")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegisterRequest request) {
        try {
            registrationService.registerUser(request);
            return ResponseEntity.ok().build();
        } catch (UsernameAlreadyExistException | IncorrectRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> registerAdmin() {
        // TODO: реализовать логику регистарции админов
        return ResponseEntity.ok().build();
    }
}