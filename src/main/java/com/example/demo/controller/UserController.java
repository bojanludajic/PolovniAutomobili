package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.UserService;

import model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService us;

	@GetMapping("/registerUser")
	public String newUser() {
		return "register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User u, Model m) {
		System.out.println("a");
		try {
			us.save(u);
			m.addAttribute("message", "Uspesna registracija!");
		} catch (Exception ex) {
			m.addAttribute("message", "Neuspesna registracija!");
		}
		
		return "login";
	}

	@GetMapping("/home")
    public String getIndex(Model m, Principal principal) {
	    String username = principal.getName();
	    User user = us.findByUsername(username);
	    m.addAttribute("user", user);
	    
	    return "index"; 
	}



}
