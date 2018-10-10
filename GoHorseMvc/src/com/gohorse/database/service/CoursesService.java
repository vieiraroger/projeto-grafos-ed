package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Courses;
import com.gohorse.database.model.Fase;
import com.gohorse.database.repository.GenericRepository;

public class CoursesService implements CrudInterface<Courses>{
	
	GenericRepository<Courses> repository;
	
	public CoursesService() {
		this.repository = new GenericRepository<>(Courses.class);
	}
	
	@Override
	public Courses find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Courses save(Courses obj) {
		return repository.save(obj);
	}

	@Override
	public Courses update(Courses obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Courses> findAll() {
		return repository.findAll();
	}

}
