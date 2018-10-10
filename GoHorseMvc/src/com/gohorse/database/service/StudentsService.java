package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Students;
import com.gohorse.database.model.Users;
import com.gohorse.database.repository.GenericRepository;

public class StudentsService implements CrudInterface<Students>{
	
	GenericRepository<Students> repository;
	
	public StudentsService() {
		this.repository = new GenericRepository<>(Students.class);
	}
	
	@Override
	public Students find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Students save(Students obj) {
		return repository.save(obj);
	}

	@Override
	public Students update(Students obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Students> findAll() {
		return repository.findAll();
	}

}
