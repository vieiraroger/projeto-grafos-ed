package com.gohorse.database.model;

public class Cities {
	
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

}
