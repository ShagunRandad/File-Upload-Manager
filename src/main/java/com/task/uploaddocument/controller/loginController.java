package com.task.uploaddocument.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
	
	
	@GetMapping(value="/")
	public String login() {
	return "login";
	}
	
	
	
	

}
