package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {

			
	   @GetMapping("/admins/club/home")
	    public String home(Model model) {
	    	return "admins/club/home";
	    }
	
	   }
	   
