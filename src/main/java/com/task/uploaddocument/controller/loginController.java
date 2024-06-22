package com.task.uploaddocument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.task.uploaddocument.dto.LoginDto;
import com.task.uploaddocument.service.RegisterUserService;

@Controller
public class LoginController {
	
	@Autowired
	RegisterUserService registerUserService;
	
	@GetMapping(value = "/")
	public String login() {
		return "login";
	}
	
	@PostMapping(value = "/Register-User")
	public ResponseEntity<String> registerUser(@RequestBody LoginDto loginDto, Model model) {
		boolean registrationResult = registerUserService.regiterUser(loginDto);
		if (registrationResult) {
			model.addAttribute("message", "Registration successful!"); 
			return new ResponseEntity<>("Registration successful!", HttpStatus.CREATED);
		} else {
			model.addAttribute("error", "Registration failed!");
			return new ResponseEntity<>("Registration failed!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
