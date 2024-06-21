package com.task.uploaddocument.service;

import org.springframework.stereotype.Service;

import com.task.uploaddocument.dto.LoginDto;

@Service
public interface RegisterUserService {

	boolean regiterUser(LoginDto loginDto);
	
}
