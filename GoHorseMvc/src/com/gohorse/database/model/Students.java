package com.gohorse.database.model;

import java.io.Serializable;

public class Students implements Entity, Serializable {
	
	private static final long serialVersionUID = 7940068363349065718L;
	private Integer student_id;
	private String student;
	private String birthdate;
	private char sex;
	private String phone;
	private String cellphone;
	private String email;
	private String note;
	private String address;
	private String number;
	private String complement;
	private String suburb;
	private String city;
	private String estate;
	private String cep;
	
	
	public Students(String student, String birthdate, char sex, String phone, String cellphone,
			String email, String note, String address, String number, String complement, String suburb,
			String city, String estate, String cep) {
		super();
		this.student = student;
		this.birthdate = birthdate;
		this.sex = sex;
		this.phone = phone;
		this.cellphone = cellphone;
		this.email = email;
		this.note = note;
		this.address = address;
		this.number = number;
		this.complement = complement;
		this.suburb = suburb;
		this.city = city;
		this.estate = estate;
		this.cep = cep;
	}
	
	
	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer id) {
		this.student_id = id;
	}
	
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	
	
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public String getEstate() {
		return estate;
	}
	public void setEstate(String estate) {
		this.estate = estate;
	}
	
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}


	@Override
	public Integer getId() {
		return student_id;
	}


	@Override
	public void setId(Integer id) {
		this.student_id = id;
	}
}
















































