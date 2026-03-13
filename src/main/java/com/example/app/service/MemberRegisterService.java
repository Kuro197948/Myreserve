package com.example.app.service;

import com.example.app.domain.MemberRegisterForm;
import com.example.app.domain.MemberRegisterResult;

public interface MemberRegisterService {

    MemberRegisterResult register(MemberRegisterForm form);
}