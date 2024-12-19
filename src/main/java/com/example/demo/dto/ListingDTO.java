package com.example.demo.dto;

import java.io.IOException;
import java.util.Base64;


import lombok.Data;

@Data
public class ListingDTO {

	
	private int idListing;
    private byte[] image;
    private int engineSize;
    private int horsepower;
    private String make;
    private int mileage;
    private String model;
    private String name;
    private int price;
    private int year;
    private String base64Image;

    public ListingDTO() {}
	
	public ListingDTO(int idListing, byte[] image, int engineSize, int horsepower, String make, int mileage,
			String model, String name, int price, int year) {
		this.idListing = idListing;
		this.image = image;
		this.engineSize = engineSize;
		this.horsepower = horsepower;
		this.make = make;
		this.mileage = mileage;
		this.model = model;
		this.name = name;
		this.price = price;
		this.year = year;
	}


	public int getIdListing() {
        return idListing;
    }

    public void setIdListing(int idListing) {
        this.idListing = idListing;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getBase64Image() {
        return base64Image;
    }
	
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
}
