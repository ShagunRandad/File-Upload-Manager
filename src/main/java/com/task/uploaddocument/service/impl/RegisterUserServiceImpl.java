package com.task.uploaddocument.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.uploaddocument.Entity.Login;
import com.task.uploaddocument.dto.LoginDto;
import com.task.uploaddocument.service.RegisterUserService;
import com.task.uploaddocument.service.reposistry.LoginRegisterReposistry;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
	
	private static final Logger logger = LogManager.getLogger(RegisterUserServiceImpl.class);
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginRegisterReposistry loginRegisterReposistry;
	
	@Autowired 
	ModelMapper modelMapper;

	@Override
	public boolean regiterUser(LoginDto loginDto) {
		try {
			loginDto.setPassword(passwordEncoder.encode(loginDto.getPassword()));	
		loginRegisterReposistry.save(modelMapper.map(loginDto, Login.class));
		  logger.info("User registered successfully: {}", loginDto.getName());
		  return true;
		}catch (Exception e) {
			logger.info("Error registering user");
		}
		return false;
	}

}
