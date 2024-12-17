package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ListingService;

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
		m.addAttribute("listing", ls.findyById(id));
		return "listingPage";
	}
	
	@GetMapping("/deleteListing")
	public String deleteListing(@RequestParam("id") Integer id) {
		ls.deleteListing(id);
		return "redirect:/user/myListings";
	}

}
