package com.example.g130ministore.controller;

import com.example.g130ministore.exception.IncorrectRequestException;
import com.example.g130ministore.service.ChangePasswordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change-password")
@RequiredArgsConstructor
@Tag(name = "ChangePasswordController", description = "API для изменения, сброса паролей")
@Slf4j
public class ChangePasswordController {

    private final ChangePasswordService changePasswordService;

    @PutMapping
    public ResponseEntity<?> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String reNewPassword
    ) {
        try {
            changePasswordService.changePassword(oldPassword, newPassword, reNewPassword);
            return ResponseEntity.ok().build();
        } catch (IncorrectRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}