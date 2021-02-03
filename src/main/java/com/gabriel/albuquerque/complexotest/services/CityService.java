package com.gabriel.albuquerque.complexotest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gabriel.albuquerque.complexotest.DTO.CityDTO;
import com.gabriel.albuquerque.complexotest.DTO.ProvincyDTO;
import com.gabriel.albuquerque.complexotest.entities.City;
import com.gabriel.albuquerque.complexotest.entities.Provincy;
import com.gabriel.albuquerque.complexotest.repositories.CityRepository;
import com.gabriel.albuquerque.complexotest.repositories.ProvincyRepository;

@Service
public class CityService {

	private CityRepository repo;
	private ProvincyRepository provRepo;

	public CityService(CityRepository repo, ProvincyRepository provRepo) {
		this.repo = repo;
		this.provRepo = provRepo;
	}

	public List<CityDTO> findAll() {
		List<City> result = this.repo.findAll();
		return entityListToDTO(result);
	}

	public List<CityDTO> findByName(String name) {
		List<City> result = this.repo.findByNameLike(name);
		return entityListToDTO(result);
	}

	public List<CityDTO> findByProvincyName(String name) {
		Provincy prov = this.provRepo.findByNameEquals(name);
		List<City> result = this.repo.minhaQuery(prov.getId());
		return entityListToDTO(result);
	}

	public CityDTO findById(long id) {
		return entityToDTO(this.repo.findById(id).orElse(null));
	}

	public CityDTO insertCity(CityDTO cityDTO) {
		CityDTO result = null;

		// validar estado
		Provincy provincyEntity = this.provRepo.findByNameEquals(cityDTO.getProvincy().getName());

		if (provincyEntity != null) {
			City entity = new City();
			entity.setName(cityDTO.getName());
			entity.setProvincy(provincyEntity);
			this.repo.save(entity);

			result = entityToDTO(entity);
		}

		return result;
	}

	static public CityDTO entityToDTO(City city) {
		CityDTO result = new CityDTO();
		result.setId(city.getId());
		result.setName(city.getName());

		ProvincyDTO p = new ProvincyDTO();
		if (city.getProvincy() != null) {
			p.setId(city.getProvincy().getId());
			p.setName(city.getProvincy().getName());
		}

		result.setProvincy(p);
		return result;
	}	

	private List<CityDTO> entityListToDTO(List<City> list) {
		return list.stream().map(c -> {
			return entityToDTO(c);
		}).collect(Collectors.toList());
	}
}
