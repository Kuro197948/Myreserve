package com.example.app.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Member;
import com.example.app.domain.MemberRegisterForm;
import com.example.app.domain.MemberRegisterResult;
import com.example.app.domain.MemberType;
import com.example.app.domain.PasswordResetToken;
import com.example.app.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberRegisterServiceImpl implements MemberRegisterService {

    private final MemberMapper memberMapper;
    private final PasswordResetService passwordResetService;

    @Override
    public MemberRegisterResult register(MemberRegisterForm form) {

        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setLoginPass(null);
        member.setCreated(LocalDateTime.now());

        MemberType type = new MemberType();
        type.setId(form.getTypeId());
        member.setType(type);

        memberMapper.insert(member);

        PasswordResetToken token = passwordResetService.createToken(member.getId());

        MemberRegisterResult result = new MemberRegisterResult();
        result.setMember(member);
        result.setPasswordResetToken(token);

        return result;
    }
}