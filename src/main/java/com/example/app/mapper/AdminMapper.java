package com.example.app.mapper;

import com.example.app.domain.AdminLoginForm;

public interface AdminMapper {

	AdminLoginForm selectByLoginId(String loginId);
	
}
