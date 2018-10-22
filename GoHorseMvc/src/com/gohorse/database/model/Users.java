package com.gohorse.database.model;

import java.io.Serializable;

public class Users implements Entity, Serializable {

	private static final long serialVersionUID = -6710793234179078915L;
	private int id;
	private String user;
	private String password;
	private String perfil;

	
	public Users(String user, String password, String perfil) {
		this.user = user;
		this.password = password;
		this.perfil = perfil;
	}
	
	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
