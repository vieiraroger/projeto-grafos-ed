package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Cities;
import com.gohorse.database.model.Courses;
import com.gohorse.database.repository.GenericRepository;

public class CitiesService implements CrudInterface<Cities>{
	
	GenericRepository<Cities> repository;
	
	public CitiesService() {
		this.repository = new GenericRepository<>(Cities.class);
	}
	
	@Override
	public Cities find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Cities save(Cities obj) {
		return repository.save(obj);
	}

	@Override
	public Cities update(Cities obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Cities> findAll() {
		return repository.findAll();
	}
}
