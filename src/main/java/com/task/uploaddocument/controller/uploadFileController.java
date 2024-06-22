package com.task.uploaddocument.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.task.uploaddocument.Entity.File;
import com.task.uploaddocument.service.FileService;
import com.task.uploaddocument.service.RegisterUserService;
import com.task.uploaddocument.util.FileUtils;

@Controller
@RequestMapping("/users")
public class UploadFileController {
	
	@Autowired
	RegisterUserService registerUserService;
	@Autowired
	FileService fileService;
	
	@PostMapping("/{userId}/files")
    public ResponseEntity<?> uploadFile(@PathVariable String userId,
                                        @RequestParam("file") MultipartFile file) {
        try {
            File uploadedFile = registerUserService.uploadFile(userId, file);
            return ResponseEntity.ok(uploadedFile);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body("File size exceeds 100 MB. Please upload a smaller file.");
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file. Please try again later.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File size exceeds 100 MB. Please upload a smaller file.");
        }
    }
	  
	    
	@GetMapping("/{userId}/files")
	public String getFilesByUserId(@PathVariable String userId, Model model) {
		List<File> files = registerUserService.getFilesByUserId(userId);
		 for (File file : files) {
	            byte[] decompressedData = FileUtils.decompressImage(file.getData());
	            file.setData(decompressedData);
	            file.setBase64Data(FileUtils.convertToBase64(decompressedData)); // Set Base64 data
	        }
		model.addAttribute("files", files);
		model.addAttribute("userId", userId);
		return "dashboard";
	}
	
	
	@PostMapping(value="/deleteUploadedFile/{id}")
	public String deletefile(@PathVariable Integer id,Model model) {
	if(	fileService.deleteFileById(id)) {
		model.addAttribute("message", "File deleted succesfully");
	}else {
		model.addAttribute("message", "File not deleted");
	}
	return null;
		
	}
	

}
