package com.gohorse.database.model;

import java.io.Serializable;

public class Courses implements Entity, Serializable{
	
	private static final long serialVersionUID = -4430720802186201076L;
	private int id;
	private String description;
	private int fase;
	
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

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

}
