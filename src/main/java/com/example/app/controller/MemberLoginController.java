package com.example.app.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Member;
import com.example.app.domain.MemberLoginForm;
import com.example.app.domain.News;
import com.example.app.service.MemberAuthService;
import com.example.app.service.NewsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberLoginController {

    private final MemberAuthService service;
    private final HttpSession session;
    private final NewsService newsService; 
    
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
    public String login(
            @ModelAttribute("form") @Valid MemberLoginForm form,
            Errors errors,
            Model model) {

        if (errors.hasErrors()) {
            return "members/memberslogin";
        }

        Member member = service.login(form.getEmail(), form.getLoginPass());

        if (member == null) {
            errors.reject("error.incorrect_id_password", "メールアドレスまたはパスワードが正しくありません");
            return "members/memberslogin";
        }

        session.setAttribute("memberId", member.getId());
        session.setAttribute("memberTypeId", member.getType().getId());
        session.setAttribute("memberName", member.getName());

        return "redirect:/members/club/home";
    }
    @GetMapping("/members/club/home")
    public String memberShowHome(Model model) {
    	//最新ニュースを取得
    	List<News> latestNews = newsService.getNewsList();
    	if (latestNews.size() > 5) {
    		latestNews = latestNews.subList(0,  5);
    	}
    	model.addAttribute("latestNews", newsService.getLatestWithDetail(3));
        return "members/club/home";
    }

    @GetMapping("/members/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/members/memberslogin";
    }
}