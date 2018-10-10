package com.gohorse.database.service;

import java.util.Collection;

public interface CrudInterface<T> {
	public T find(Integer id);
	public T save(T obj);
	public T update(T obj);
	public void delete(Integer id);
	public Collection<T> findAll();
}
