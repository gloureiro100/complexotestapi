package com.gabriel.albuquerque.complexotest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.albuquerque.complexotest.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
