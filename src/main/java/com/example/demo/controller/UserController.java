package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.security.CarService;
import com.example.demo.service.ListingService;
import com.example.demo.service.UserService;

import model.Listing;
import model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService us;

	@Autowired
	CarService cs;

	@Autowired
	ListingService ls;

	@GetMapping("/registerUser")
	public String newUser() {
		return "register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User u, Model m) {
		try {
			us.save(u);
			m.addAttribute("message", "Uspesna registracija!");
		} catch (Exception ex) {
			m.addAttribute("message", "Neuspesna registracija!");
		}

		return "login";
	}

	@GetMapping("/home")
	public String getIndex(Model m, Principal p) {
		if (p != null) {
			String username = p.getName();
			User user = us.findByUsername(username);
			m.addAttribute("user", user);
		}
		
		List<Listing> listings = ls.findAll();
	    for (Listing listing : listings) {
	        if (listing.getImage() != null) {
	            String base64Image = Base64.getEncoder().encodeToString(listing.getImage());
	            listing.setBase64Image(base64Image);
	            ls.saveListing(listing);
	        }
	    }
	    
		m.addAttribute("listings", ls.findAll());

		return "home";
	}

	@GetMapping("/cars")
	public String getCarsForListing(Model m, @RequestParam(value = "make", required = false) String make) {
		m.addAttribute("makes", cs.getMakes());
		if (make != null) {
			m.addAttribute("selectedMake", make);
			m.addAttribute("models", cs.getModels(make));
		} else {
			m.addAttribute("models", new ArrayList<>());
		}
		return "newListing";
	}

	@PostMapping("/saveListing")
	public String saveListing(@ModelAttribute("listing") Listing listing, Principal p) {
		if (p != null) {
			try {
				User u = us.findByUsername(p.getName());
				listing.setIdUser(u.getIdUser());
				ls.saveListing(listing);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "redirect:/";
	}
	
	@GetMapping("/myListings")
	public String getListingsForUser(Model m, Principal p) {
		if(p != null) {
			try {
				User u = us.findByUsername(p.getName());
				List<Listing> userListings = ls.findByUser(u.getIdUser());
				if(userListings.isEmpty()) {
					m.addAttribute("message", "Nemate nijedan oglas!");
				} else {
					m.addAttribute("listings", userListings);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "personalListings";
	}

}
