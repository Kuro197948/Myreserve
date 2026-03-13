package com.example.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.MemberRegisterForm;
import com.example.app.domain.MemberRegisterResult;
import com.example.app.service.MailService;
import com.example.app.service.MemberRegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberRegisterController {

    private final MemberRegisterService memberRegisterService;
    private final MailService mailService;

    @GetMapping("/members/register")
    public String showRegister(Model model) {
        model.addAttribute("form", new MemberRegisterForm());
        return "members/register";
    }

    @PostMapping("/members/register")
    public String register(
            @Valid MemberRegisterForm form,
            Errors errors,
            Model model,
            HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "members/register";
        }

        MemberRegisterResult result = memberRegisterService.register(form);

        String token = result.getPasswordResetToken().getToken();

        String setupUrl = request.getScheme()
                + "://"
                + request.getServerName()
                + (request.getServerPort() == 80 || request.getServerPort() == 443 ? "" : ":" + request.getServerPort())
                + request.getContextPath()
                + "/members/password/setup?token="
                + token;

        mailService.sendPasswordSetupMail(result.getMember().getEmail(), setupUrl);

        model.addAttribute("member", result.getMember());

        return "members/registerComplete";
    }
}