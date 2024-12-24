package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ListingService;
import com.example.demo.service.RateLimitService;

import jakarta.servlet.http.HttpServletRequest;
import model.Listing;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	ListingService ls;
	
	@Autowired
	RateLimitService rateLimitService;
	
	@GetMapping("/")
    public String getHomePage(Model m, HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		if(rateLimitService.isRateLimited(sessionId, "home")) {
			m.addAttribute("rateLimitExceeded", "greska429");
			return "error";
		}
		
		List<Listing> listings = ls.findAll();
		Collections.shuffle(listings);
        m.addAttribute("listings", listings);  
        
        return "home";  
    }
	
}
