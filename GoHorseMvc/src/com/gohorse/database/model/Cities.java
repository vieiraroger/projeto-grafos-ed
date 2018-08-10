package com.gohorse.database.model;

public class Cities {
	
	private static long cityID;
	
	private String city;
	
	private String state;
	
	private String country;

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		this.city = city;
	}

	public String getstate() {
		return state;
	}

	public void setstate(String state) {
		this.state = state;
	}

	public String getcountry() {
		return country;
	}

	public void setcountry(String country) {
		this.country = country;
	}

	public Cities() {
	
	}

}
