package com.sample.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnotherController {

	@RequestMapping("/login")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping("/signup")
	public String signUpPage(){
		return "home";
	}
}
