package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Teacher;
import com.gohorse.database.repository.GenericRepository;


public class TeacherService implements CrudInterface<Teacher> {

	GenericRepository<Teacher> repository;
	
	public TeacherService() {
		this.repository = new GenericRepository<>(Teacher.class);
	}
	
	@Override
	public Teacher find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Teacher save(Teacher obj) {
		return repository.save(obj);
	}

	@Override
	public Teacher update(Teacher obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Teacher> findAll() {
		return repository.findAll();
	}

}
