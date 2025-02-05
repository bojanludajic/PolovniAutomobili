package com.example.demo.controller;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.PartDTO;
import com.example.demo.service.PartService;

@Controller
@RequestMapping("/parts")
public class PartController {
	
	@Lazy
	final PartService ps;

	public PartController(PartService ps) {
		this.ps = ps;
	}

	@GetMapping("/partsForModel")
	public String getParts(@RequestParam String make, @RequestParam String model, Model m) {
		try {
			List<PartDTO> parts = ps.getParts(make, model);
			
			if(parts.isEmpty()) {
				m.addAttribute("message", "Nema delova za ovaj auto!");
				
				return "error";
			}
	
			m.addAttribute("make", make);
			m.addAttribute("model", model);
			m.addAttribute("parts", parts);
			
			return "parts";
		} catch(HttpClientErrorException.NotFound ex) {
			m.addAttribute("message", "Za ovaj model trenutno nema delova!");
			
			return "error";
		} catch(Exception ex) {
			m.addAttribute("message", "Servis za delove trenutno nije dostupan. Pokusaj ponovo kasnije.");
			
			return "error";
		}
	}
	
	@GetMapping("/order")
	public String orderPart(@RequestParam String make, @RequestParam String model, @RequestParam String name, RedirectAttributes redirectAttributes, Model m) {
		try {
			ps.orderPart(make, model, name);
		} catch(Exception ex) {
			m.addAttribute("message", "Ovaj deo trenutno nije dostupan!");
			return "error";
		}
		
		redirectAttributes.addFlashAttribute("message", "Uspesno ste porucili deo (" + make + " " + model + " " + name + ")! Mozete ga ocekivati u radnji \"Bojan Auto\" u Novom Sadu u roku od 3 do 5 radnih dana.");
		
		return "redirect:/parts/partsForModel?make=" + make + "&model=" + model;
	}

}
