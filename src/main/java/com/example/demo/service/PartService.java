package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CarRequestDTO;
import com.example.demo.dto.PartDTO;

@Service
@Lazy
public class PartService {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public List<PartDTO> getParts(String make, String model) {
		String url = "http://localhost:8081/Parts/partsForModel?make=" + make + "&model=" + model;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(headers);
		
		ResponseEntity<List<PartDTO>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				requestEntity,
				new ParameterizedTypeReference<List<PartDTO>>() {}
		);
		
		return response.getBody();
	}
	
	public ResponseEntity<String> orderPart(String make, String model, String name) {
		String url = "http://localhost:8081/Parts/order";
		
		CarRequestDTO dto = new CarRequestDTO(make, model, name);
		
		HttpHeaders  headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<CarRequestDTO> requestEntity = new HttpEntity<>(dto, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				url, 
				HttpMethod.PUT,
				requestEntity,
				String.class
		);
				
		return response;
	}
	

}
