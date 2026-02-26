package com.example.app.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Member;
import com.example.app.domain.MemberLoginForm;
import com.example.app.service.MemberAuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberLoginController {

    private final MemberAuthService service;
    private final HttpSession session;

    @GetMapping("/members/memberslogin")
    public String showMembersLogin(Model model) {
    	System.out.println("✅ showMembersLogin hit"); // ←追加

        if (session.getAttribute("memberId") != null) {
            return "redirect:/members/club/home";
        }

        model.addAttribute("form", new MemberLoginForm());
        return "members/memberslogin";
    }

    @PostMapping("/members/memberslogin")
    public String login(@Valid MemberLoginForm form, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "members/memberslogin";
        }

        Member member = service.login(form.getName(), form.getLoginPass());

        if (member == null) {
            errors.reject("error.incorrect_id_password", "ログインIDまたはパスワードが正しくありません");
            return "members/memberslogin";
        }

        session.setAttribute("memberId", member.getId());
        session.setAttribute("memberTypeId", member.getType().getId());
        session.setAttribute("memberName", member.getName());

        return "redirect:/members/club/home";
    }
    @GetMapping("/members/club/home")
    public String memberShowHome(Model model) {
    	
        return "members/club/home";
    }

    @GetMapping("/members/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/members/memberslogin";
    }
}