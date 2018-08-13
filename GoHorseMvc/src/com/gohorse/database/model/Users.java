package com.gohorse.database.model;

public class Users {
	
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

	public void setUser(String user) {
		this.user = user;
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
	
	
}
