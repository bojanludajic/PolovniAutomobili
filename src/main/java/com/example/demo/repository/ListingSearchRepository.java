package com.example.demo.repository;

import java.util.List;

import model.Listing;

public interface ListingSearchRepository {
	
	public List<Listing> search(String make, String model, Integer priceMin, Integer priceMax, Integer yearMin,
			Integer yearMax, Integer sizeMin, Integer sizeMax, Integer powerMin, Integer powerMax);
	
}
