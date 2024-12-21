package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ListingRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.Listing;

@Service
public class ListingService {

	@Autowired
	ListingRepository lr;
	
	@PersistenceContext
    private EntityManager entityManager;

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

	public List<Listing> searchListings(String make, String model, Integer priceMin, Integer priceMax, Integer yearMin,
			Integer yearMax, Integer sizeMin, Integer sizeMax,
			Integer powerMin, Integer powerMax) {
		return lr.search(make, model, priceMin, priceMax, yearMin, yearMax, sizeMin, sizeMax, powerMin, powerMax);
		
	}

}
