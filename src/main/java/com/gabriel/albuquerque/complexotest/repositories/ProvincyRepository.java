package com.gabriel.albuquerque.complexotest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.albuquerque.complexotest.entities.Provincy;

public interface ProvincyRepository extends JpaRepository<Provincy, Long> {

	public Provincy findByNameEquals(String name);
	
}
