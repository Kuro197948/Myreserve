package com.example.app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.domain.Admin;

@Controller
public class LoginHomeController {
		
	@GetMapping("/")
	public String Loginhome(Model model) {
		model.addAttribute("admin", new Admin());
		return "redirect:/loginhome";
	}
	@GetMapping({"/loginhome","/loginHome"})
	public String showLoginHome(HttpSession session, Model model) {
		//管理者ログイン済の判定キーに合わせて変更
	    if (session.getAttribute("loginId") != null) {
	        return "redirect:/admins/club/home"; // 管理者ログイン後ホームに
	    }
	    model.addAttribute("admin", new Admin());
	    return "loginhome";
	}
	}

