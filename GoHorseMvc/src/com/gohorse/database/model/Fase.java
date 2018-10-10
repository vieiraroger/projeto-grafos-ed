package com.gohorse.database.model;

import java.io.Serializable;

public class Fase implements Entity, Serializable{
	
	private static final long serialVersionUID = -2797531055697167812L;
	private int id;
	private String description;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
