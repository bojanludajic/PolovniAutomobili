package com.example.demo.dto;

public class CarRequestDTO {
	
	
	String make;
	
	String model;
	
	String name;

	public CarRequestDTO(String make, String model, String name) {
		this.make = make;
		this.model = model;
		this.name = name;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
