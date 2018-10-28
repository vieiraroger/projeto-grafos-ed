package com.gohorse.database.model;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class Courses implements Entity, Serializable{
	
	private static final long serialVersionUID = -4430720802186201076L;
	private int id;
	private int code;
	private String name;
	private LinkedHashSet<Phases> phases;

	public Courses() {
		
	}
	
	public Courses(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public LinkedHashSet<Phases> getPhases() {
		return phases;
	}


	public void setPhases(LinkedHashSet<Phases> phases) {
		this.phases = phases;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
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
