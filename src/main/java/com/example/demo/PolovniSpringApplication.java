package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Arrays;


@SpringBootApplication
@EntityScan("model")
public class PolovniSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PolovniSpringApplication.class, args);
	}

}
