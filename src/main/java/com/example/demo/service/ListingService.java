package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.ListingRepository;

import model.Listing;

@Service
public class ListingService {

	@Autowired
	ListingRepository lr;
	
	public void saveListing(Listing listing) {
		try {
			lr.save(listing);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Listing> findAll() {
		return lr.findAll();
	}
	
	public Listing findyById(Integer id) {
		return lr.getById(id);
	}
	
	public List<Listing> findByUser(Integer id) {
		return lr.getListingsForUser(id);
	}
	
	public void deleteListing(Integer id) {
		lr.deleteById(id);
	}
	
}
