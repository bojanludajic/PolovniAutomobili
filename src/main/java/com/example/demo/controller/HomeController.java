package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import com.example.demo.exception.TooManyRequestsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ListingService;
import com.example.demo.service.RateLimitService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import model.Listing;

@Controller
@RequestMapping("/")
public class HomeController {

	final ListingService ls;
	
	final RateLimitService rateLimitService;
	
	final UserService us;

	public HomeController(ListingService ls, RateLimitService rateLimitService, UserService us) {
		this.ls = ls;
		this.rateLimitService = rateLimitService;
		this.us = us;
	}

	@GetMapping("/")
    public String getHomePage(Model m, HttpServletRequest request) {
		if(rateLimitService.isRateLimited(request, "home")) {
			throw new TooManyRequestsException();
		}
		
		List<Listing> listings = ls.findAll();
		Collections.shuffle(listings);
        m.addAttribute("listings", listings);  
        
        return "home";  
    }
	
}
