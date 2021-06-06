package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	@GetMapping
	public String getLogout() {
		
		//ログイン画面遷移
		return "redirect:/login";
	}
}
