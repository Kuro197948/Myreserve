package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PasswordSetupForm {

    @NotBlank
    private String token;

    @NotBlank
    @Size(min = 4, max = 100)
    private String password;

    @NotBlank
    private String confirmPassword;
}