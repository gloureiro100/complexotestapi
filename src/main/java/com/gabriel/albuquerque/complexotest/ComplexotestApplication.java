package com.gabriel.albuquerque.complexotest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gabriel.albuquerque.complexotest.entities.City;
import com.gabriel.albuquerque.complexotest.entities.Customer;
import com.gabriel.albuquerque.complexotest.entities.EnumSex;
import com.gabriel.albuquerque.complexotest.entities.Provincy;
import com.gabriel.albuquerque.complexotest.repositories.CityRepository;
import com.gabriel.albuquerque.complexotest.repositories.CustomerRepository;
import com.gabriel.albuquerque.complexotest.repositories.ProvincyRepository;

@SpringBootApplication
public class ComplexotestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplexotestApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProvincyRepository provRepo, CityRepository cityRepo, CustomerRepository custRepo) {
		return args -> {
			
			//clear database
			custRepo.deleteAll();
			cityRepo.deleteAll();
			provRepo.deleteAll();
			
			//initing demo data 
			
			//Provincy
			
			Provincy p1 = new Provincy();
			Provincy p2 = new Provincy();

			p1.setName("RJ");
			provRepo.save(p1);

			p2.setName("SP");
			provRepo.save(p2);

			//Cities
			
			List<City> cities = new ArrayList<>();			
			List<Provincy> provincies = provRepo.findAll();
			
			p1 = provincies.stream().filter(prov -> prov.getName().equals("RJ")).findAny().orElse(null);
			p2 = provincies.stream().filter(prov -> prov.getName().equals("SP")).findAny().orElse(null);

			City c = new City();
			c.setName("campos");
			c.setProvincy(p1);
			cities.add(c);

			c = new City();
			c.setName("sao paulo");
			c.setProvincy(p2);
			cities.add(c);

			c = new City();
			c.setName("rio de janeiro");
			c.setProvincy(p1);
			cities.add(c);

			cities.forEach(city -> cityRepo.save(city));

			// Customers			
			Customer cust = new Customer();
			cust.setAge(30);
			cust.setBirthDate(LocalDate.of(1990, 12, 28));
			cust.setCity(c);
			cust.setName("gabriel loureiro de albuquerque");
			cust.setSex(EnumSex.MALE);
			
			custRepo.save(cust);
			
			cust = new Customer();
			cust.setAge(25);
			cust.setBirthDate(LocalDate.of(1995, 12, 28));
			cust.setCity(c);
			cust.setName("Maria das Dores");
			cust.setSex(EnumSex.FEMALE);
			
			custRepo.save(cust);
		};
	}

}
