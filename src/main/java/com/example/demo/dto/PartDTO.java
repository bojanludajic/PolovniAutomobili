package com.example.demo.dto;

import java.math.BigDecimal;

public class PartDTO {
	
	private long id;

	private int availability;

	private String make;

	private String model;

	private String name;

	private BigDecimal price;

	public PartDTO(long id, int availability, String make, String model, String name, BigDecimal price) {
		super();
		this.id = id;
		this.availability = availability;
		this.make = make;
		this.model = model;
		this.name = name;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

}
