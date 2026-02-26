package com.example.app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NoCacheInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
    res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    res.setHeader("Pragma", "no-cache");
    res.setDateHeader("Expires", 0);
    return true;
  }
}