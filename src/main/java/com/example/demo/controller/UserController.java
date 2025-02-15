package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.TooManyRequestsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.CarService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ListingService;
import com.example.demo.service.MessageService;
import com.example.demo.service.RateLimitService;
import com.example.demo.service.UserService;

import model.Listing;
import model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	final UserService us;

	final CarService cs;

	final ListingService ls;

	final FavoriteService fs;

	final MessageService ms;

	final RateLimitService rateLimitService;

	final ImageService is;

	public UserController(UserService us, CarService cs, ListingService ls,
						  FavoriteService fs, MessageService ms, RateLimitService rls,
						  ImageService is) {

		this.us = us;
		this.cs = cs;
		this.ls = ls;
		this.fs = fs;
		this.ms = ms;
		this.rateLimitService = rls;
		this.is = is;
	}

	@GetMapping("/newListing")
	public String newListing(Model m, @RequestParam(value = "make", required = false) String make,
			Principal p) {
		if(p != null) {
			if(rateLimitService.isRateLimited(p.getName(), "newListing")) {
				throw new TooManyRequestsException();

			}
		}
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
	public String saveListing(@ModelAttribute("listing") Listing listing, Principal p,
			@RequestParam("uploadImage") MultipartFile file) {
		if (p != null) {
			try {
				User u = us.findByUsername(p.getName());
				listing.setIdUser(u.getIdUser());
				
				if(!file.isEmpty()) {
					byte[] resizedImage = is.resizeAndRecompressImage(file, 480, 288, 0.8);
					listing.setImage(resizedImage);
				}

				ls.saveListing(listing);
			} catch (Exception ex) {
				return "error";
			}
		}
		return "redirect:/";
	}

	@GetMapping("/deleteListing")
	public String deleteListing(@RequestParam("id") Integer id, Principal p) {
		if (p != null) {
			User u = us.findByUsername(p.getName());
			if (ls.findyById(id).getIdUser() == u.getIdUser()) {
				ls.deleteListing(id);
			}
		}
		return "redirect:/user/myListings";
	}

	@GetMapping("/myListings")
	public String getListingsForUser(Model m, Principal p) {
		if (p != null) {
			try {
				User u = us.findByUsername(p.getName());
				List<Listing> userListings = ls.findByUser(u.getIdUser());
				if (userListings.isEmpty()) {
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

	@PostMapping("/favListing")
	public String favListing(Principal p, @RequestParam("idListing") Integer id) {
		if (p != null) {
			User u = us.findByUsername(p.getName());
			try {
				fs.newFavorite(u.getIdUser(), id);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return "redirect:/listing/?id=" + id;
	}

	@GetMapping("/favListings")
	public String myFavorites(Principal p, Model m) {
		if (p != null) {
			User u = us.findByUsername(p.getName());

			List<Listing> favorites = fs.favoritesForUser(u.getIdUser());
			if(favorites.isEmpty()) {
				m.addAttribute("message", "Nemate nijedan sacuvan oglas!");
			} else {
				m.addAttribute("favorites", favorites);
			}
		}

		return "favoriteListings";
	}
	
	@GetMapping("/deleteFavorite")
	public String deleteFavorite(Principal p, @RequestParam("idListing") Integer idListing) {
		if (p != null) {
			User u = us.findByUsername(p.getName());
			
			fs.deleteFavorite(u.getIdUser(), idListing);
		}
		
		return "redirect:/user/favListings";
	}
	
	@GetMapping("/newMessage")
	public String newMessage(Principal p, @RequestParam("idListing") Integer id, Model m) {
		if (p != null) {
			if(rateLimitService.isRateLimited(p.getName(), "message")) {
				throw new TooManyRequestsException();

			}
			User u = us.findByUsername(p.getName());
			Listing l = ls.findyById(id);
			User seller = us.findById(l.getIdUser());
			
			if(u == seller) {
				return "error";
			}
			
			m.addAttribute("user", u);
			m.addAttribute("listing", l);
			m.addAttribute("seller", seller);
			return "newMessage";
		} else {
			return "error";
		}
	}

	@PostMapping("/sendMessage")
	public String sendMessage(@RequestParam("idSender") Integer id,
			@RequestParam("idReceiver") Integer idRec,
			@RequestParam("text") String text) {
		if(!text.isBlank() && id != idRec) {
			ms.newMessage(id, idRec, text);
		}
		
		return "redirect:/";
	}

}
