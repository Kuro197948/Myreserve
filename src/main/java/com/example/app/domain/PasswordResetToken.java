package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PasswordResetToken {
    
    private Integer id;
    private Integer memberId;
    private String token;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}