package com.task.uploaddocument.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.task.uploaddocument.Entity.File;
import com.task.uploaddocument.Entity.Login;
import com.task.uploaddocument.helper.SessionHelper;
import com.task.uploaddocument.service.FileService;
import com.task.uploaddocument.service.RegisterUserService;
import com.task.uploaddocument.util.FileUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UploadFileController {
	@Autowired
	RegisterUserService registerUserService;
	@Autowired
	FileService fileService;
	
	@PostMapping("/{userId}/files")
    public String  uploadFile(@PathVariable String userId,
                                        @RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes
                                        , HttpServletRequest request,
                                        HttpServletResponse response) throws IOException {
		
		 if (!SessionHelper.isSessionValid(request)) {
			  redirectAttributes.addFlashAttribute("message", "Session expired. Please login again.");
			  response.sendRedirect("/");
			  return null;
	        }
        try {
            File uploadedFile = registerUserService.uploadFile(userId, file);
            redirectAttributes.addFlashAttribute("message", "File uploaded successfully.");
            return "redirect:/users/" + userId + "/files";
        }  catch (Exception e) {
        	   redirectAttributes.addFlashAttribute("error", "File size exceeds 100 MB. Please upload a smaller file.");
        	   return "redirect:/users/" + userId + "/files";
        }
    }
	  
	    
	@GetMapping("/{userId}/files")
	public String getFilesByUserId(@PathVariable String userId, Model model,@ModelAttribute("message") String message,RedirectAttributes redirectAttributes
			, HttpServletRequest request,
            HttpServletResponse response
			) throws IOException {
		
		 if (!SessionHelper.isSessionValid(request)) {
			  redirectAttributes.addFlashAttribute("message", "Session expired. Please login again.");
			  response.sendRedirect("/");
			  return null;
	        }
		List<File> files = registerUserService.getFilesByUserId(userId);
		 for (File file : files) {
	            byte[] decompressedData = FileUtils.decompressImage(file.getData());
	            file.setData(decompressedData);
	            file.setBase64Data(FileUtils.convertToBase64(decompressedData)); // Set Base64 data
	        }
		model.addAttribute("files", files);
		model.addAttribute("userId", userId);
		 if (message != null) {
		        model.addAttribute("message", message);
		    }
		return "dashboard";
	}
	
	
	@PostMapping(value="/{userId}/files/{id}/delete")
	public String deletefile(@PathVariable String userId,@PathVariable Integer id,Model model,RedirectAttributes redirectAttributes
			, HttpServletRequest request,
             HttpServletResponse response
			) throws IOException {
		
		  if (!SessionHelper.isSessionValid(request)) {
			  redirectAttributes.addFlashAttribute("message", "Session expired. Please login again.");
			  response.sendRedirect("/");
			  return null;
	        }
		  
	if(	fileService.deleteFileById(id)) {
	     redirectAttributes.addFlashAttribute("message", "File deleted succesfully");
         return "redirect:/users/" + userId + "/files";
	}else {
	     redirectAttributes.addFlashAttribute("message", "File not deleted");
         return "redirect:/users/" + userId + "/files";
	}

	}
	
	
	  @PostMapping("/login")
	    public String login(@RequestParam String email, @RequestParam String password, Model model,HttpServletRequest request) {
	        Optional<Login> userOptional = registerUserService.loginUser(email, password);
	        if (userOptional.isPresent()) {
	            String userId = String.valueOf(userOptional.get().getId());
	            HttpSession session = request.getSession(true);
	            session.setAttribute("userId", userId);
	            return "redirect:/users/" + userId + "/files";
	        } else {
	            model.addAttribute("error", "Invalid email or password");
	            return "login"; 
	        }
	    }
	

}
