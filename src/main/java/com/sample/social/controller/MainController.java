package com.sample.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@Autowired
	Environment env;
	
	@RequestMapping("/home")
	public String mainPage(){
		return "home";
	}


}
