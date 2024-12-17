package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	
	@Query("Select distinct c.make from Car c order by make")
	public List<String> getMakes();
	
	@Query("Select c.model from Car c where c.make = :make order by model")
	public List<String> modelsForMake(@Param("make") String make);
	
}
