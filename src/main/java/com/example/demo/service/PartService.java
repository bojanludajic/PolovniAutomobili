package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.dto.CarRequestDTO;
import com.example.demo.dto.PartDTO;

@Service
@Lazy
public class PartService {

	final RestClient restClient;

	public PartService(RestClient restClient) {
        this.restClient = restClient;
	}

	public List<PartDTO> getParts(String make, String model) {
		String url = "/partsForModel?make=" + make + "&model=" + model;

		return restClient.get()
				.uri(url)
				.retrieve()
				.body(new ParameterizedTypeReference<>() {
                });
	}
	
	public ResponseEntity<String> orderPart(String make, String model, String name) {
		String url = "/order";

		CarRequestDTO dto = new CarRequestDTO(make, model, name);

		return restClient.put()
				.uri(url)
				.body(dto)
				.retrieve()
				.toEntity(String.class);
	}
	

}
