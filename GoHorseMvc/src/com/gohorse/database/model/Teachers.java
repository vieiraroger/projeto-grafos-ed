package com.gohorse.database.model;

import java.io.Serializable;

public class Teachers implements Entity, Serializable {
	
	private static final long serialVersionUID = -8982355266862975723L;
	private int id;
	private String name;
	private Integer graduation;
	
	public Teachers () {
		
	}
	
	public Teachers(String name, Integer graduation) {
		super();
		this.name = name;
		this.graduation = graduation;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGraduation() {
		return graduation;
	}
	public void setGraduation(Integer graduation) {
		this.graduation = graduation;
	}
	
	

}
