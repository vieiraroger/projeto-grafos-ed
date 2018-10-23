package com.gohorse.database.model;

import java.io.Serializable;

public class Subjects implements Entity,Serializable{
	
	private static final long serialVersionUID = -3221559531431991317L;
	private int id;
	private int code;
	private String name;
	private String week_day; 
	private String teacher_amount;
	
	public Subjects(int code, String name, String week_day, String teacher_amount) {
		super();
		this.code = code;
		this.name = name;
		this.week_day = week_day;
		this.teacher_amount = teacher_amount;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;	
	}

	public String getDescrition() {
		return teacher_amount;
	}

	public void setDescrition(String teacher_amount) {
		this.teacher_amount = teacher_amount;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getweek_day() {
		return week_day;
	}

	public void setweek_day(String week_day) {
		this.week_day = week_day;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
