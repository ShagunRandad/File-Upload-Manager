package com.task.uploaddocument.service;

import org.springframework.stereotype.Service;

@Service
public interface FileService {

	boolean deleteFileById(Integer id);
	
}
