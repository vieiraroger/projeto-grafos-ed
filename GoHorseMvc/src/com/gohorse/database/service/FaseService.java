package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Fase;
import com.gohorse.database.model.Maths;
import com.gohorse.database.repository.GenericRepository;

public class FaseService implements CrudInterface<Fase> {
	
	GenericRepository<Fase> repository;
	
	public FaseService() {
		this.repository = new GenericRepository<>(Fase.class);
	}
	
	@Override
	public Fase find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Fase save(Fase obj) {
		return repository.save(obj);
	}

	@Override
	public Fase update(Fase obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Fase> findAll() {
		return repository.findAll();
	}
	

}
