package com.example.demo.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.DateCalculation;
import com.example.demo.model.SearchDate;
import com.example.demo.model.User;
import com.example.demo.service.CalculationService;

@Controller
public class CalculationController {

	@Autowired
	private CalculationService calcService;
	
	@GetMapping("/home")
	public String getHome(@AuthenticationPrincipal User user, @ModelAttribute SearchDate searchDate, Model model) {
		//home.htmlに遷移
		return "home/home";
	}
	
	@GetMapping("/search")
	public String search(@AuthenticationPrincipal User user, @ModelAttribute @Validated SearchDate searchDate , BindingResult bindingResult, Model model) throws ParseException {
		
		//入力チェックに引っかかった場合、home.htmlに戻る
		if (bindingResult.hasErrors()) {
			return getHome(user, searchDate, model);
		}
		
		//検索処理
		List<DateCalculation> resultDate = calcService.selectAll();
		
		//計算結果の変数を用意
		Map<Integer, Object> resultCalc = new LinkedHashMap<>();		
		
		//検索結果分の日付の計算処理
		for (int i = 0; i < resultDate.size(); i++) {
			DateCalculation date = resultDate.get(i);
			LocalDate result = searchDate.getSearchDate();
			result = result.plusYears(date.getAdjustmentYear());
			result = result.plusMonths(date.getAdjustmentMonth());
			result = result.plusDays(date.getAdjustmentDay());
			
			resultCalc.put(i, result);
		}
		
		//結果を画面へ渡す
		model.addAttribute("resultDate", resultDate);
		model.addAttribute("resultCalc", resultCalc);
		
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
	
	@GetMapping("/update/{id}")
	public String updateDisplay(@PathVariable("id") int id, Model model) {
		
		//更新対象のデータを取得
		DateCalculation updateDate = calcService.selectOne(id);
		
		//結果を画面へ渡す
		model.addAttribute("updateDate", updateDate);
		
		//update.htmlに遷移
		return "home/update";
	}
	
	@PostMapping("/update/{id}")
	public String update(@ModelAttribute DateCalculation date) {
		
		//更新処理
		calcService.update(date);
		
		//home.htmlにリダイレクト
		return "redirect:/home";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		//削除処理
		calcService.delete(id);
		
		//home.htmlにリダイレクト
		return "redirect:/home";
	}
}
