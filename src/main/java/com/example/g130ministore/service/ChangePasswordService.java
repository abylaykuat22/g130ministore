package com.example.g130ministore.service;

import com.example.g130ministore.dto.UserRegisterRequest;

public interface ChangePasswordService {

    void changePassword(String oldPassword, String newPassword, String reNewPassword);
}
