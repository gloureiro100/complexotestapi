package com.gabriel.albuquerque.complexotest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gabriel.albuquerque.complexotest.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("select c from Customer c where c.name like %?1%")
	public List<Customer> findByNameLike(String name);
	
}
