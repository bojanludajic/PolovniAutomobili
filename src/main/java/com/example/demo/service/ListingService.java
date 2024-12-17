package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ListingRepository;

import model.Listing;

@Service
public class ListingService {

	@Autowired
	ListingRepository lr;
	
	public void saveListing(Listing listing) {
		lr.save(listing);
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
