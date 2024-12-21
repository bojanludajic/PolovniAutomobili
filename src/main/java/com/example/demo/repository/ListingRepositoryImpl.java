package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.Listing;

@Repository
public class ListingRepositoryImpl implements ListingSearchRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Listing> search(String make, String model, Integer priceMin, Integer priceMax, Integer yearMin,
			Integer yearMax, Integer sizeMin, Integer sizeMax, Integer powerMin, Integer powerMax) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Listing> criteriaQuery = criteriaBuilder.createQuery(Listing.class);
		Root<Listing> listingRoot = criteriaQuery.from(Listing.class);

		List<Predicate> predicates = new ArrayList<>();
		
		if (!make.isEmpty()) {
			predicates.add(criteriaBuilder.equal(listingRoot.get("make"), make));
		}
		if (!model.isEmpty()) {
			predicates.add(criteriaBuilder.equal(listingRoot.get("model"), model));
		}
		if (priceMin != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(listingRoot.get("price"), priceMin));
		}
		if (priceMax != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(listingRoot.get("price"), priceMax));
		}
		if (yearMin != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(listingRoot.get("year"), yearMin));
		}
		if (yearMax != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(listingRoot.get("year"), yearMax));
		}
		if (sizeMin != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(listingRoot.get("engineSize"), sizeMin));
		}
		if (sizeMax != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(listingRoot.get("engineSize"), sizeMax));
		}
		if (powerMin != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(listingRoot.get("horsepower"), powerMin));
		}
		if (powerMax != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(listingRoot.get("horsepower"), powerMax));
		}
		
		if (!predicates.isEmpty()) {
	        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
	    }
		
		return em.createQuery(criteriaQuery).getResultList();
	}

}
