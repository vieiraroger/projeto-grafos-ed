package com.gohorse.database.model;

import java.io.Serializable;

public class Courses implements Entity, Serializable{
	
	private static final long serialVersionUID = -4430720802186201076L;
	private int id;
	private int code;
	
	public Courses() {
		
	}
	
	public Courses(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

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
