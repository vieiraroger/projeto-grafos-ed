package com.gohorse.database.service;

import java.util.Collection;

import com.gohorse.database.model.Users;
import com.gohorse.database.repository.GenericRepository;

public class UsersService implements CrudInterface<Users>{
	
	GenericRepository<Users> repository;
	
	public UsersService() {
		this.repository = new GenericRepository<>(Users.class);
	}
	
	@Override
	public Users find(Integer id) {
		return repository.find(id);
	}

	@Override
	public Users save(Users obj) {
		return repository.save(obj);
	}

	@Override
	public Users update(Users obj) {
		return repository.update(obj);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Collection<Users> findAll() {
		return repository.findAll();
	}

}
