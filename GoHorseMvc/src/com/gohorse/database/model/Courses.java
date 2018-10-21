package com.gohorse.database.model;

import java.io.Serializable;

public class Courses implements Entity, Serializable{
	
	private static final long serialVersionUID = -4430720802186201076L;
	private int id;
	private String name;
	
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
