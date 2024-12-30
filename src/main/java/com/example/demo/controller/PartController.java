package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.PartService;

import model.Part;

@Controller
@RequestMapping("/parts")
public class PartController {
	
	@Autowired
	PartService ps;
	
	@GetMapping("/partsForModel")
	public String getParts(@RequestParam String make, @RequestParam String model, Model m) {
		try {
			List<Part> parts = ps.getParts(make, model);
	
			m.addAttribute("make", make);
			m.addAttribute("model", model);
			m.addAttribute("parts", parts);
			
			return "parts";
		} catch(Exception ex) {
			m.addAttribute("message", "Nema delova za ovaj auto!");
			
			return "error";
		}
	}
	
	@GetMapping("/order")
	public String orderPart(@RequestParam String make, @RequestParam String model, @RequestParam String name, RedirectAttributes redirectAttributes) {
		try {
			ps.orderPart(make, model, name);
		} catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		
		redirectAttributes.addFlashAttribute("message", "Uspesno ste porucili deo (" + make + " " + model + " " + name + ")! Mozete ga ocekivati u radnji \"Bojan Auto\" u Novom Sadu u roku od 3 do 5 radnih dana.");
		
		return "redirect:/parts/partsForModel?make=" + make + "&model=" + model;
	}

}
