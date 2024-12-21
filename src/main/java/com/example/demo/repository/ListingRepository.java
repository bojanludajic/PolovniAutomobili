package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer>, ListingSearchRepository {

	@Query("Select l from Listing l where l.idUser= :id")
	public List<Listing> getListingsForUser(@Param("id") Integer id);
	
}
