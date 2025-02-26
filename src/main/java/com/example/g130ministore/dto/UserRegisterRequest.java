package com.example.g130ministore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "DTO для регистрации нового пользователя")
public class UserRegisterRequest {

    private String username;
    private String password;
    private String rePassword;
    private String fullName;
    private String gender;
    private Integer age;
}
