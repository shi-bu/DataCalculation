package com.example.demo.calculation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.calculation.model.DateCalculation;
import com.example.demo.calculation.service.CalculationService;

@Controller
public class CalculationController {

	@Autowired
	private CalculationService calcService;
	
	@GetMapping("/home")
	public String getHome(Model model) {
		//login.htmlに遷移
		return "home/home";
	}
	
	@GetMapping("/search")
	public String search(Model model) {
		
		//検索処理
		List<DateCalculation> resultDate = calcService.selectAll();
		model.addAttribute("resultDate", resultDate);
		
		//home.htmlにリダイレクト
		return "home/home"; 
	}
	
	@GetMapping("/create")
	public String createDisplay(@ModelAttribute DateCalculation date) {
		//create.htmlに遷移
		return "home/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute DateCalculation date) {
		
		//新規登録処理
		calcService.insert(date);

		//home.htmlにリダイレクト
		return "redirect:/home";
	}
}
