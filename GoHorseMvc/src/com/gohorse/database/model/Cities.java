package com.gohorse.database.model;

import java.io.Serializable;

public class Cities implements Entity, Serializable{
	
	private static final long serialVersionUID = -5718169470825373489L;
	private int id;
	private String name;
	private String state;
	private String country;
	
	
	public Cities(String name, String state, String country) {
		this.name = name;
		this.state = state;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public Cities() {
	
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
