package com.gabriel.albuquerque.complexotest.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.albuquerque.complexotest.DTO.CustomerDTO;
import com.gabriel.albuquerque.complexotest.services.CustomerService;

@RestController
@RequestMapping({ "/customers" })
public class CustomerController {

	private CustomerService service;

	public CustomerController(CustomerService customerService) {
		this.service = customerService;
	}

	@GetMapping
	public List<CustomerDTO> findAll() {
		return service.findAll();
	}

	@PostMapping
	public ResponseEntity<?> insertCustomer(@RequestBody CustomerDTO dto) throws URISyntaxException {
		CustomerDTO result = service.insertCustomer(dto);

		if (result != null) {
			String uriLocation = "http://localhost:8080/customers/" + result.getId();
			URI uri = new URI(uriLocation);
			return ResponseEntity.created(uri).build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<CustomerDTO> findById(@PathVariable long id) {
		CustomerDTO result = service.findById(id);

		return result != null ? ResponseEntity.ok().body(result) : (ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return this.service.deleteCustomer(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable("id") long id, @RequestBody CustomerDTO customer) {
		CustomerDTO result = this.service.updateCustomer(id, customer);
		return result != null ? ResponseEntity.ok().body(result) : ResponseEntity.notFound().build();
	}
}
