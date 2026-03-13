package com.example.app.service;

import com.example.app.domain.PasswordResetToken;

public interface PasswordResetService {

    PasswordResetToken createToken(Integer memberId);

    PasswordResetToken findValidToken(String token);

    void deleteToken(String token);
}