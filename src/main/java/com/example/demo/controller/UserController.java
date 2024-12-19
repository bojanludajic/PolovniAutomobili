package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ListingDTO;
import com.example.demo.security.CarService;
import com.example.demo.service.ListingService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
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
		
		List<ListingDTO> listingDTOs = new ArrayList<>();
		List<Listing> listings = ls.findAll();
		
		for(Listing l : listings) {
			ListingDTO listingDTO = new ListingDTO(
					l.getIdListing(),
					l.getImage(),
					l.getEngineSize(),
					l.getHorsepower(),
					l.getMake(),
					l.getMileage(),
					l.getModel(),
					l.getName(),
					l.getPrice(),
					l.getYear()
					);
			String base64Image = Base64.getEncoder().encodeToString(l.getImage());
			listingDTO.setBase64Image(base64Image);
			listingDTOs.add(listingDTO);
		}
		
		m.addAttribute("listings", listingDTOs);

		return "home";
	}

	@GetMapping("/newListing")
	public String getCarsForListing(Model m, 
			@RequestParam(value = "make", required = false) String make) {
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
	public String saveListing(@ModelAttribute("listing") Listing listing,
			Principal p, @RequestParam("uploadImage") MultipartFile file) {
		if (p != null) {
			try {
				User u = us.findByUsername(p.getName());
				
				if(!file.isEmpty()) {
					byte[] bytes = file.getBytes();
					listing.setImage(bytes);
					String base64Image = Base64.getEncoder().encodeToString(bytes);
				}
				
				listing.setIdUser(u.getIdUser());
				
				ls.saveListing(listing);
			} catch (Exception ex) {
				ex.printStackTrace();
				return "error";
			}
		}
		return "redirect:/user/home";
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
	
	@GetMapping("/deleteListing")
	public String deleteListing(@RequestParam("id") Integer id) {
		ls.deleteListing(id);
		return "redirect:/user/myListings";
	}

}
