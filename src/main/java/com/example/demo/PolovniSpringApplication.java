package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.example.demo.repository.UserRepository;

@SpringBootApplication
@EntityScan("model")
public class PolovniSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PolovniSpringApplication.class, args);
	}

}
