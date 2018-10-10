package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Maths;
import com.gohorse.database.model.Students;
import com.gohorse.database.repository.GenericRepository;

public class MathsService implements CrudInterface<Maths>{
	
	GenericRepository<Maths> repository;
	
	public MathsService() {
		this.repository = new GenericRepository<>(Maths.class);
	}
	
	@Override
	public Maths find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Maths save(Maths obj) {
		return repository.save(obj);
	}

	@Override
	public Maths update(Maths obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Maths> findAll() {
		return repository.findAll();
	}

}
