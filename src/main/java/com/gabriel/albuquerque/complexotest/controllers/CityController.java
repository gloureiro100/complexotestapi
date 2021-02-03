package com.gabriel.albuquerque.complexotest.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.albuquerque.complexotest.DTO.CityDTO;
import com.gabriel.albuquerque.complexotest.DTO.FindByNameDTO;
import com.gabriel.albuquerque.complexotest.services.CityService;

@RestController
@RequestMapping({ "/cities" })
public class CityController {

	private CityService service;

	public CityController(CityService cityService) {
		this.service = cityService;
	}

	@GetMapping
	public List<CityDTO> findAll() {
		return service.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<CityDTO> findById(@PathVariable long id) {
		CityDTO result = service.findById(id);
		
		return  result != null ? ResponseEntity.ok().body(result)
				:(ResponseEntity.notFound().build());
	}
	
	@PostMapping(path = {"byName"}, consumes = "application/json", produces = "application/json")
	public List<CityDTO> findByName(@RequestBody FindByNameDTO dto){
		return service.findByName(dto.getName());
	}
	
	@PostMapping(path = {"byProvincyName"}, consumes = "application/json", produces = "application/json")
	public List<CityDTO> findByProvincyName(@RequestBody FindByNameDTO dto){
		return service.findByProvincyName(dto.getName());
	}
	
	@PostMapping
	public void insertCity(@RequestBody CityDTO dto){
		service.insertCity(dto);
	}
}
