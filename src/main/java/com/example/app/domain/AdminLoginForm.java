package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AdminLoginForm {
	
	private Integer id;
	
	@NotBlank
	private String loginId;
	private String loginPass;
}
