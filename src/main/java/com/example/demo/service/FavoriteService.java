package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.ListingRepository;
import com.example.demo.repository.UserRepository;

import model.Favorite;
import model.FavoritePK;
import model.Listing;
import model.User;

@Service
public class FavoriteService {
	
	@Autowired
	FavoriteRepository fr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	ListingRepository lr;
	
	public List<Listing> favoritesForUser(Integer idUser) {
		return fr.favoritesForUser(idUser);
	}
	
	public void newFavorite(Integer idUser, Integer idListing) {
		User u = ur.findById(idUser).orElse(null);
		Listing l = lr.findById(idListing).orElse(null);
		
		if(u != null && l != null) {
			FavoritePK pk = new FavoritePK();
			pk.setIdUser(idUser);
			pk.setIdListing(idListing);
			
			Favorite f = new Favorite();
			f.setId(pk);
			f.setUser(u);
			f.setListing(l);
			
			fr.save(f);
		} else {
			throw new IllegalArgumentException("Unet nepostojeci user/oglas");
		}
	}
	
	public void deleteFavorite(Integer idUser, Integer idListing) {
		fr.deleteFavorite(idUser, idListing);
	}

}
