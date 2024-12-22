package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ListingService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

import model.Listing;
import model.Message;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService us;
	
	@Autowired
	ListingService ls;
	
	@Autowired
	MessageService ms;
	
	@GetMapping("/listingManagement")
	public String manageListings(Model m) {
		List<Listing> allListings = ls.findAll();
		m.addAttribute("listings", allListings);
		
		return "adminListingManagement";
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/deleteListing")
	public String deleteListing(@RequestParam("id") Integer id) {
		try {
			ls.deleteListing(id);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			return "redirect:/admin/ListingManagement";
		}
	}
	
	@GetMapping("/allMessages")
	public String allMessages(Model m) {
		List<Message> allMessages = ms.findAll();
		m.addAttribute("messages", allMessages);
		
		return "adminMessageManagement";
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/deleteMessage")
	public String deleteMessage(@RequestParam("idMessage") Integer idMessage) {
		try {
			ms.deleteMessage(idMessage);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			return "redirect:/admin/allMessages";
		}
	}

}
