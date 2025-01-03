package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.UserService;

import model.User;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService us;

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

}
