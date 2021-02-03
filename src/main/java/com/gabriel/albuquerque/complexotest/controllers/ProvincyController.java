package com.gabriel.albuquerque.complexotest.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.albuquerque.complexotest.entities.Provincy;
import com.gabriel.albuquerque.complexotest.repositories.ProvincyRepository;

@RestController
@RequestMapping({ "/provinces" })
public class ProvincyController {

	private ProvincyRepository repository;

	public ProvincyController(ProvincyRepository repo) {
		this.repository = repo;
	}

	@GetMapping
	public List<Provincy> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Provincy> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

}
