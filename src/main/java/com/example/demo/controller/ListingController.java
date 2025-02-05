package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ListingService;
import com.example.demo.service.UserService;

import model.Listing;
import model.User;

@Controller
@RequestMapping("/listing")
public class ListingController {
	
	final ListingService ls;
	
	final UserService us;

	public ListingController(ListingService ls, UserService us) {
		this.ls = ls;
		this.us = us;
	}

	@PostMapping("/saveListing")
	public String newListing() {
		return "";
	}
	
	@GetMapping("/")
	public String getListingPage(@RequestParam("id") Integer id, Model m, Principal p) {
		if(p != null) {
			User u = us.findByUsername(p.getName());
			m.addAttribute("idUser", u.getIdUser());
		} else {
			m.addAttribute("idUser", -1);
		}
		
		Listing l = ls.findyById(id);
		m.addAttribute("listing", l);
		
		return "listingPage";
	}

}
