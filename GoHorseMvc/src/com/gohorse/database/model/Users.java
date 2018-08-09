package com.gohorse.database.model;

public class Users {
	
	private static long userID;
	
	private String user;
	
	private String password;
	
	private String perfil;

	public Users() {
		
	}
	
	public String getuser() {
		return user;
	}

	public void setuser(String user) {
		this.user = user;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getperfil() {
		return perfil;
	}

	public void setperfil(String perfil) {
		this.perfil = perfil;
	}
	
}
