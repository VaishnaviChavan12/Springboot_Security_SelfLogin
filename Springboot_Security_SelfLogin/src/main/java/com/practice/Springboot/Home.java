package com.practice.Springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/logout-success")
	public String logout()
	{
		return "logout";
	}
}
