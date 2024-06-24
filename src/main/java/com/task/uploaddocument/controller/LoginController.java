package com.task.uploaddocument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.task.uploaddocument.dto.LoginDto;
import com.task.uploaddocument.service.RegisterUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	RegisterUserService registerUserService;
	
	@GetMapping(value = "/")
	public String login() {
		return "login";
	}
		
	 @PostMapping(value = "/Register-User")
	    public String registerUser(@ModelAttribute LoginDto loginDto, Model model) {
	        boolean registrationResult = registerUserService.regiterUser(loginDto);
	        if (registrationResult) {
	            model.addAttribute("message", "Registration successful!");
	        } else {
	            model.addAttribute("error", "Registration failed!");
	        }
	        return "login"; 
	    }
	 
		@GetMapping("/logout")
		public String logout(HttpServletRequest request, Model model) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate(); 
			}
			model.addAttribute("message", "Logged out successfully!");
			return "login"; // Return the login template directly
		}

}
