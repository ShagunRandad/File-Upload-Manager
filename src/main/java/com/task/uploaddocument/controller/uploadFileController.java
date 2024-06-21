package com.task.uploaddocument.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class uploadFileController {
	
	@GetMapping(value="login.do")
	public String loginPage() {
		return "Hello We are learning in Thyleaf";
	}

}
