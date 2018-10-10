package com.gohorse.database.model;

import java.io.Serializable;

public class Cities implements Entity, Serializable{
	
	private static final long serialVersionUID = -5718169470825373489L;
	private int id;
	private String city;
	private String state;
	private String country;
	
	public Cities(String city, String state, String country) {
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getCity() {
		return city;
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
