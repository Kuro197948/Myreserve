package com.example.app.controller;

import jakarta.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.PasswordResetToken;
import com.example.app.domain.PasswordSetupForm;
import com.example.app.mapper.MemberMapper;
import com.example.app.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PasswordSetupController {

    private final PasswordResetService passwordResetService;
    private final MemberMapper memberMapper;

    @GetMapping("/members/password/setup")
    public String showSetup(@RequestParam("token") String token, Model model) {

        PasswordResetToken resetToken = passwordResetService.findValidToken(token);

        if (resetToken == null) {
            return "members/tokenInvalid";
        }

        PasswordSetupForm form = new PasswordSetupForm();
        form.setToken(token);

        model.addAttribute("form", form);

        return "members/passwordSetup";
    }

    @PostMapping("/members/password/setup")
    public String setupPassword(
            @Valid PasswordSetupForm form,
            Errors errors,
            Model model) {

        if (errors.hasErrors()) {
            return "members/passwordSetup";
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.reject("password.mismatch", "パスワードが一致しません");
            return "members/passwordSetup";
        }

        PasswordResetToken resetToken =
                passwordResetService.findValidToken(form.getToken());

        if (resetToken == null) {
            return "members/tokenInvalid";
        }

        String hashed = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt());

        memberMapper.updatePasswordById(resetToken.getMemberId(), hashed);

        passwordResetService.deleteToken(form.getToken());

        return "members/passwordComplete";
    }
}