package com.example.app.mapper;

import com.example.app.domain.PasswordResetToken;

public interface PasswordResetTokenMapper {

    void insert(PasswordResetToken token);

    PasswordResetToken selectByToken(String token);

    void deleteByToken(String token);

    void deleteByMemberId(Integer memberId);
}