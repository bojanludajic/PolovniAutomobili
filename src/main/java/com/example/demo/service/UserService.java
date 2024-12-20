package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import model.Role;
import model.User;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	RoleRepository rr;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> getUsers() {
		return ur.findAll();
	}
	
	public User findByUsername(String username) {
		return ur.findByUsername(username);
	}
	
	public User findById(Integer id) {
		return ur.findById(id).get();
	}
	
	public void save(User u) {
		Role r = rr.findById(2).get();
		u.setRole(r);
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		ur.save(u);
	}
		
}
