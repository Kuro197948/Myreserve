package com.example.app.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.AdminLoginForm;
import com.example.app.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminLoginController {

    private final AdminService service;
    private final HttpSession session;

    // ログイン画面表示
    @GetMapping("/admins/adminslogin")
    public String showAdminsLogin(HttpSession session, Model model) {
       //ログイン済なら管理者ホームへ
    	if(session.getAttribute("loginId")!=null) {
    		return "redirect:/admins/club/home";
    	}
    	
    	model.addAttribute("admin", new AdminLoginForm());
        return "admins/adminslogin";
    }
    
 

    // ログイン処理
    @PostMapping("/admins/adminslogin")
    public String login(
            @Valid AdminLoginForm admin,
            Errors errors) {

        // 入力に不備がある場合
        if (errors.hasErrors()) {
            return "admins/adminslogin";
        }

        String loginId = admin.getLoginId();
        String loginPass = admin.getLoginPass();

        // ログインID・パスワードが正しくない場合
        if (!service.login(loginId, loginPass)) {

            // メッセージが見つからなくても500にならないようにデフォルト文言を追加
            errors.rejectValue(
                    "loginId",
                    "error.incorrect_id_password",
                    "ログイン ID またはパスワードが正しくありません"
            );

            return "admins/adminslogin";
        }

        // 正しいログイン情報
        session.setAttribute("loginId", loginId);

        return "redirect:/admins/club/home";
    }
    @GetMapping("/login")
    public String loginRoot() {
      return "redirect:/admins/adminslogin";
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/admins/adminslogin";
    }
}