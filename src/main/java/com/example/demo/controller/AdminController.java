package com.example.demo.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ListingService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import model.Listing;
import model.Message;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	final UserService us;
	
	final ListingService ls;
	
	final MessageService ms;

	public AdminController(UserService us, ListingService ls, MessageService ms) {
		this.us = us;
		this.ls = ls;
		this.ms = ms;
	}

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
	
	@GetMapping("/reports")
	public String reports(Model m) {
		m.addAttribute("users", us.getNormalUsers());
		
		return "reports";
	}
	
	@GetMapping("/getListingReport")
	public void showReport(HttpServletResponse response, @RequestParam Integer idUser) throws Exception {
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ls.findByUser(idUser));
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/ListingReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<>();
		params.put("Name", us.getName(idUser));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        inputStream.close();
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=OglasiKorisnika.pdf");
		
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}

}
