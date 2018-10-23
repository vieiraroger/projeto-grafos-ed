package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Teachers;
import com.gohorse.database.repository.GenericRepository;


public class TeachersService implements CrudInterface<Teachers> {

	GenericRepository<Teachers> repository;
	
	public TeachersService() {
		this.repository = new GenericRepository<>(Teachers.class);
	}
	
	@Override
	public Teachers find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Teachers save(Teachers obj) {
		return repository.save(obj);
	}

	@Override
	public Teachers update(Teachers obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Teachers> findAll() {
		return repository.findAll();
	}

}
