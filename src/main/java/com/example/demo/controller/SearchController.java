package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CarService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	CarService cs;
	
	@GetMapping("/")
	public String getSearchPage(Model m, @RequestParam(value = "make", required = false) String make) {
		m.addAttribute("makes", cs.getMakes());
		if(make != null) {
			m.addAttribute("selectedMake", make);
			m.addAttribute("models", cs.getModels(make));
		} else {
			m.addAttribute("models", new ArrayList<>());
		}
		
		return "search";
	}
	
	
}
