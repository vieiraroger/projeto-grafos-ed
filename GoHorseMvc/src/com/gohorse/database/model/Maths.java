package com.gohorse.database.model;

import java.io.Serializable;

public class Maths implements Entity,Serializable{
	
	private static final long serialVersionUID = -3221559531431991317L;
	private int id;
	private int fase;
	private int course; 
	private String description;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;	
	}

	public String getDescrition() {
		return description;
	}

	public void setDescrition(String description) {
		this.description = description;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}
}
