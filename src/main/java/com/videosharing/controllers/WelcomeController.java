package com.videosharing.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.models.User;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

	@GetMapping
	public String welcome() {
		return "home";
	}
	
}
