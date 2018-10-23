package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Subjects;
import com.gohorse.database.model.Students;
import com.gohorse.database.repository.GenericRepository;

public class SubjectsService implements CrudInterface<Subjects>{
	
	GenericRepository<Subjects> repository;
	
	public SubjectsService() {
		this.repository = new GenericRepository<>(Subjects.class);
	}
	
	@Override
	public Subjects find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Subjects save(Subjects obj) {
		return repository.save(obj);
	}

	@Override
	public Subjects update(Subjects obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Subjects> findAll() {
		return repository.findAll();
	}

}
