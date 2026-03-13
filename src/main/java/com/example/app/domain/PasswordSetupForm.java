package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PasswordSetupForm {

    @NotBlank
    private String token;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}