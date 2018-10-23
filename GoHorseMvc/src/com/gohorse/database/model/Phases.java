package com.gohorse.database.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Phases implements Entity, Serializable{
	
	private static final long serialVersionUID = -2797531055697167812L;
	private int id;
	private int code;
	private String name;
	private LinkedHashSet<Subjects> subjects;
	
	public LinkedHashSet<Subjects> getSubjects() {
		return subjects;
	}

	public void setSubjects(LinkedHashSet<Subjects> subjects) {
		this.subjects = subjects;
	}

	public Phases(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	


}
