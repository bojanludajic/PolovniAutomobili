package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CarRepository;

import model.Car;

@Service
public class CarService {

	final
	CarRepository cr;

	public CarService(CarRepository cr) {
		this.cr = cr;
	}

	public List<Car> getCars() {
		return cr.findAll();
	}
	
	public List<String> getMakes() {
		return cr.getMakes();
	}
	
	public List<String> getModels(String make) {
		return cr.modelsForMake(make);
	}
	
}
