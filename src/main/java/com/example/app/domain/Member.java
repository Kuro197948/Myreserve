package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Member {
	
	private Integer id;
	
	@NotBlank
	@Size(max=10)
	private String name;
	
	private LocalDateTime created;
	private MemberType type;
}
