package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.UserService;

import model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService us;


	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

	@GetMapping("/registerUser")
	public String newUser() {
		return "register";
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
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
