package com.gohorse.database.model;

public class Users {
	
	private long userID;
	
	private String user;
	
	private String password;
	
	private String perfil;

	public Users() {
		
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

	public Users(long userID, String user, String password, String perfil) {
		super();
		this.userID = userID;
		this.user = user;
		this.password = password;
		this.perfil = perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	
}
