package com.example.app.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.PasswordResetToken;
import com.example.app.mapper.PasswordResetTokenMapper;
import com.example.app.util.TokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenMapper passwordResetTokenMapper;

    @Override
    public PasswordResetToken createToken(Integer memberId) {
        passwordResetTokenMapper.deleteByMemberId(memberId);

        PasswordResetToken token = new PasswordResetToken();
        token.setMemberId(memberId);
        token.setToken(TokenUtil.generateToken());
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(30));

        passwordResetTokenMapper.insert(token);
        return token;
    }

    @Override
    public PasswordResetToken findValidToken(String token) {
        PasswordResetToken resetToken = passwordResetTokenMapper.selectByToken(token);

        if (resetToken == null) {
            return null;
        }

        if (resetToken.getExpiresAt() == null || resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            return null;
        }

        return resetToken;
    }

    @Override
    public void deleteToken(String token) {
        passwordResetTokenMapper.deleteByToken(token);
    }
}