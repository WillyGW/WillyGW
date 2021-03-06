package com.study.springmvc.case04.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvc.case04.entity.stock;
import com.study.springmvc.case04.validator.StockValidator;

@Controller
@RequestMapping("/case04/stock")
public class StockContoller {
	private List<stock> stocks = new CopyOnWriteArrayList<>();
	
	@Autowired
	private StockValidator stockValidator;
	
	@GetMapping("/")
	public String index(@ModelAttribute stock stock, Model model) {
		model.addAttribute("stocks", stocks);
		
		return "case04/stock";
	}
	
	@PostMapping("/")
	public String add(@Valid stock stock, BindingResult result, Model model) {
		stockValidator.validate(stock, result);
		if(result.hasErrors()) {
			model.addAttribute("stocks", stocks);
			return "case04/stock";
		}
		stocks.add(stock);
		return "redirect:./";
	}
	
}
