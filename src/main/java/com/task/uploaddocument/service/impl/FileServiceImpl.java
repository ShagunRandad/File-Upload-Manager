package com.task.uploaddocument.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.uploaddocument.service.FileService;
import com.task.uploaddocument.service.reposistry.FileRepository;

@Service
public class FileServiceImpl implements FileService {
	
	Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	FileRepository fileRepository;

	@Override
	public boolean deleteFileById(Integer id) {
		try {
		fileRepository.deleteById(id);
		return true;
		}catch (Exception e) {
			log.info("Error in deleting file :");
		}
		return false;
	}



}
