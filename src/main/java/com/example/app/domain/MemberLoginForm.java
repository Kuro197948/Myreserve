package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberLoginForm {

    @NotBlank
    private String name;

    @NotBlank
    private String loginPass;
}