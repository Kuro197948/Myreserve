package com.example.app.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.NewsForm;
import com.example.app.service.MemberAdminService;
import com.example.app.service.NewsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admins/club")

@RequiredArgsConstructor
public class NewsController {

	
 private final NewsService newsService;
 private final MemberAdminService memberService;

 @GetMapping("/announcements")
 public String list(Model model) {
	 var newsList = newsService.getNewsList();


	    model.addAttribute("newsList", newsList);

	    return "admins/club/announcements";
	}
 @GetMapping("/detail/{id}")
 public String detail(@PathVariable Integer id, Model model) {
 model.addAttribute("news", newsService.getNewsById(id));
 return "admins/club/detail";
 }
 @GetMapping("/save")
 public String addGet(Model model) {
 model.addAttribute("memberTypeList", memberService.getTypeList());
 model.addAttribute("newsForm", new NewsForm());
 return "admins/club/save";
 }
 @PostMapping("/save")
 public String addPost(
 HttpSession session,
 @Valid NewsForm newsForm,
 Errors errors,
 RedirectAttributes ra,
 Model model) {
 // バリデーション
	 MultipartFile upfile = newsForm.getUpfile();
	 if(!upfile.isEmpty()) {
	 // 画像か否か判定する
	 String type = upfile.getContentType();
	 if(!type.startsWith("image/")) {
	 // 画像ではない場合、エラーメッセージを表示
	 errors.rejectValue("upfile", "error.not_image_file");
	 }
	 }
	 
	 
 if(errors.hasErrors()) {
 model.addAttribute("memberTypeList",
memberService.getTypeList());
 return "admins/club/save";
 }
 // 投稿者名は管理者のログイン ID とする
 newsForm.setAuthor((String) session.getAttribute("loginId"));

//データベースへ追加
newsService.addNews(newsForm);

ra.addFlashAttribute("statusMessage", "お知らせを追加しました。");
return "redirect:/admins/club/announcements";
}
 
 @PostMapping("/delete/{id}")
 public String deleteNews(@PathVariable Integer id,
                          RedirectAttributes ra) {

     newsService.deleteById(id);  // ←あなたのService名に合わせる

     ra.addFlashAttribute("statusMessage", "削除しました");

     return "redirect:/admins/club/announcements"; // 一覧に戻す
 }



}

