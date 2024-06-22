package com.task.uploaddocument.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.task.uploaddocument.Entity.File;
import com.task.uploaddocument.dto.LoginDto;

@Service
public interface RegisterUserService {

   boolean regiterUser(LoginDto loginDto);
	
   File uploadFile(String userId, MultipartFile file) throws IOException;
   
   List<File> getFilesByUserId(String userId);
	
}
