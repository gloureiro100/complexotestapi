package com.gabriel.albuquerque.complexotest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDTO {
	private long id;
	private String name;
	private ProvincyDTO provincy;
}
