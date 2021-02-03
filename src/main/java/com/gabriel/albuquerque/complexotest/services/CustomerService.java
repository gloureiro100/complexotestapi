package com.gabriel.albuquerque.complexotest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gabriel.albuquerque.complexotest.DTO.CityDTO;
import com.gabriel.albuquerque.complexotest.DTO.CustomerDTO;
import com.gabriel.albuquerque.complexotest.entities.City;
import com.gabriel.albuquerque.complexotest.entities.Customer;
import com.gabriel.albuquerque.complexotest.repositories.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repo;

	public CustomerService(CustomerRepository repo) {
		this.repo = repo;
	}

	public List<CustomerDTO> findAll() {
		List<Customer> result = this.repo.findAll();

		return entityListToDTO(result);
	}

	public boolean deleteCustomer(long id) {
		if (this.findById(id) != null) {
			this.repo.deleteById(id);
			return true;
		}

		return false;
	}

	public CustomerDTO updateCustomer(long id, CustomerDTO customer) {
		Customer entity = this.repo.findById(id).orElse(null);

		if (entity != null) {
			entity.setName(customer.getName());
			this.repo.save(entity);
		}

		return this.entityToDTO(entity);
	}	

	public CustomerDTO findById(long id) {
		return entityToDTO(this.repo.findById(id).orElse(null));
	}

	public CustomerDTO insertCustomer(CustomerDTO dto) {
		CustomerDTO result = null;

		Customer entity = new Customer();

		entity.setAge(dto.getAge());
		entity.setBirthDate(dto.getBirthDate());
		entity.setName(dto.getName());
		entity.setSex(dto.getSex());
		entity.setId(0);

		City city = new City();
		city.setId(dto.getCity().getId());
		entity.setCity(city);

		this.repo.save(entity);

		result = entityToDTO(entity);

		return result;
	}
	
	public List<CustomerDTO> findByName(String name){
		return entityListToDTO(this.repo.findByNameLike(name));
	}

	private CustomerDTO entityToDTO(Customer customer) {
		CustomerDTO result = null;

		if (customer != null) {
			result = new CustomerDTO();

			result.setId(customer.getId());
			result.setAge(customer.getAge());
			result.setBirthDate(customer.getBirthDate());
			result.setName(customer.getName());
			result.setSex(customer.getSex());

			CityDTO cityDTO = CityService.entityToDTO(customer.getCity());

			result.setCity(cityDTO);
		}

		return result;
	}

	private List<CustomerDTO> entityListToDTO(List<Customer> list) {
		return list.stream().map(c -> {
			return entityToDTO(c);
		}).collect(Collectors.toList());
	}
}
