package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CarService;
import com.example.demo.service.ListingService;

import model.Listing;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	CarService cs;
	
	@Autowired
	ListingService ls;
	
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
	
	@GetMapping("/submit")
    public String searchListings(
        @RequestParam(required = false) String make,
        @RequestParam(required = false) String model,
        @RequestParam(required = false) Integer priceMin,
        @RequestParam(required = false) Integer priceMax,
        @RequestParam(required = false) Integer yearMin,
        @RequestParam(required = false) Integer yearMax,
        @RequestParam(required = false) Integer sizeMin,
        @RequestParam(required = false) Integer sizeMax,
        @RequestParam(required = false) Integer powerMin,
        @RequestParam(required = false) Integer powerMax,
        Model m
    ) {
        System.out.println("a");
        List<Listing> listings = ls.searchListings(
            make, model, priceMin, priceMax, yearMin, yearMax, sizeMin, sizeMax, powerMin, powerMax
        );
        
        m.addAttribute("listings", listings);
       
        return "searchResults";
    }
	
	
}
