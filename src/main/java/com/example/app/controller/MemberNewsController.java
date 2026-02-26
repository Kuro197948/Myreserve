package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.app.domain.News;
import com.example.app.domain.NewsDetail;
import com.example.app.mapper.NewsDetailMapper;
import com.example.app.service.NewsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberNewsController {

    private final NewsService newsService;
    private final NewsDetailMapper newsDetailMapper;

    // ✅ 一覧（これが無いと /members/club/announcements が404になる）
    @GetMapping("/members/club/announcements")
    public String list(Model model) {
        List<News> newsList = newsService.getNewsList();
        model.addAttribute("newsList", newsList);
        return "members/club/announcements";
    }

    // ✅ 詳細
    @GetMapping("/members/club/announcements/{id}")
    public String detail(@PathVariable Integer id, Model model) {

        News news = newsService.getNewsById(id);
        NewsDetail detail = newsDetailMapper.selectByNewsId(id);

        model.addAttribute("news", news);
        model.addAttribute("detail", detail);

        return "members/club/detail";
    }
}