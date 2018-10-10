package com.gohorse.database.model;

public class Teacher implements Entity {
	
	private Integer id;
	private String Code;
	private String Name;
	private String Graduation;
	
	public Teacher(String code, String name, String graduation) {
		super();
		Code = code;
		Name = name;
		Graduation = graduation;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGraduation() {
		return Graduation;
	}

	public void setGraduation(String graduation) {
		Graduation = graduation;
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
