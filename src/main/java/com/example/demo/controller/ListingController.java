package com.example.demo.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ListingService;

import jakarta.servlet.http.HttpServletRequest;
import model.Listing;

@Controller
@RequestMapping("/listing")
public class ListingController {
	
	@Autowired
	ListingService ls;
	
	@PostMapping("/saveListing")
	public String newListing() {
		return "";
	}
	
	@GetMapping("/")
	public String getListingPage(@RequestParam("id") Integer id, Model m) {
		Listing l = ls.findyById(id);
		m.addAttribute("listing", l);
		
		return "listingPage";
	}

}
