package com.example.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberAuthServiceImpl implements MemberAuthService {

    private final MemberMapper memberMapper;
    
   
    @Value("${app.member.common-password}")
    private String commonPassword;

    @Override
    public Member login(String name, String loginPass) {

        // ① 共通パスワードチェック（違えばDB見ない）
        if (!commonPassword.equals(loginPass)) {
            return null;
        }

        // ② nameで存在確認（軽量）
        Member member = memberMapper.selectByName(name);
        if (member == null) {
            return null;
        }

        // ③ typeまで必要なので、JOIN付きで再取得（フルMember）
        return memberMapper.selectById(member.getId());
    }
}