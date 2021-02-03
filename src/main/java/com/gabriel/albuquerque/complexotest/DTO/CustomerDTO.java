package com.gabriel.albuquerque.complexotest.DTO;

import java.time.LocalDate;

import com.gabriel.albuquerque.complexotest.entities.EnumSex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
	private long id;
	private String name;
	private EnumSex sex;
	private LocalDate birthDate;
	private int age;
	private CityDTO city;
}
