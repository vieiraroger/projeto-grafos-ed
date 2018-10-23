package com.gohorse.database.model;

import java.io.Serializable;

public class Teachers implements Entity, Serializable {
	
	private static final long serialVersionUID = -8982355266862975723L;
	private Integer id;
	private String code;
	private String name;
	private String graduation;
	
	public Teachers(String code, String name, String graduation) {
		super();
		this.code = code;
		this.name = name;
		this.graduation = graduation;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGraduation() {
		return graduation;
	}
	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}
	
	

}
