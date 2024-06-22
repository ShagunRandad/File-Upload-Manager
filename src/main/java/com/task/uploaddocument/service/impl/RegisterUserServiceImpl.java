package com.task.uploaddocument.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.task.uploaddocument.Entity.File;
import com.task.uploaddocument.Entity.Login;
import com.task.uploaddocument.dto.LoginDto;
import com.task.uploaddocument.service.RegisterUserService;
import com.task.uploaddocument.service.reposistry.FileRepository;
import com.task.uploaddocument.service.reposistry.LoginRegisterReposistry;
import com.task.uploaddocument.util.FileUtils;

import jakarta.transaction.Transactional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
	
	private static final Logger logger = LogManager.getLogger(RegisterUserServiceImpl.class);
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	LoginRegisterReposistry loginRegisterReposistry;
	@Autowired
	FileRepository fileRepository;
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
	
	 @Override
	    public File uploadFile(String userId, MultipartFile multipartFile) throws IOException {
	        Login login = loginRegisterReposistry.findById(Integer.parseInt(userId))
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        File file = new File();
	        file.setFilename(multipartFile.getOriginalFilename());
	        file.setData(FileUtils.compressImage(multipartFile.getBytes()));
	        file.setLogin(login);
	      
	        return fileRepository.save(file);
	    }
	
	@Override
	 public List<File> getFilesByUserId(String userId) {
	        Login login = loginRegisterReposistry.findById(Integer.parseInt(userId)).orElseThrow(() -> new RuntimeException("User not found"));
	        return login.getFiles();
	    }

}
