package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Favorite;
import model.FavoritePK;
import model.Listing;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePK> {
	
	@Query("Select f.listing from Favorite f where f.user.idUser = :x")
	List<Listing> favoritesForUser(@Param("x") Integer idUser);
	
	@Modifying
	@Transactional
	@Query("Delete from Favorite f where f.user.idUser = :x and f.listing.idListing = :y")
	void deleteFavorite(@Param("x") Integer idUser, @Param("y") Integer idListing);

}
