package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Phases;
import com.gohorse.database.model.Subjects;
import com.gohorse.database.repository.GenericRepository;

public class PhasesService implements CrudInterface<Phases> {
	
	GenericRepository<Phases> repository;
	
	public PhasesService() {
		this.repository = new GenericRepository<>(Phases.class);
	}
	
	@Override
	public Phases find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Phases save(Phases obj) {
		return repository.save(obj);
	}

	@Override
	public Phases update(Phases obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Phases> findAll() {
		return repository.findAll();
	}
	

}
