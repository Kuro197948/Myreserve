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
    public String showPasswordSetup(
            @RequestParam("token") String token,
            Model model) {

        PasswordResetToken passwordResetToken = passwordResetService.findValidToken(token);

        if (passwordResetToken == null) {
            model.addAttribute("errorMessage", "無効または期限切れのトークンです。");
            return "members/passwordSetupError";
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

        PasswordResetToken passwordResetToken = passwordResetService.findValidToken(form.getToken());

        if (passwordResetToken == null) {
            model.addAttribute("errorMessage", "無効または期限切れのトークンです。");
            return "members/passwordSetupError";
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "error.password.mismatch", "確認用パスワードが一致しません。");
        }

        if (errors.hasErrors()) {
            return "members/passwordSetup";
        }

        String hashedPassword = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt());

        memberMapper.updatePasswordById(passwordResetToken.getMemberId(), hashedPassword);

        passwordResetService.deleteToken(form.getToken());

        return "members/passwordSetupComplete";
    }
}