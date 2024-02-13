package model;

import java.io.Serializable;

public class Login_Value implements Serializable {
	private String email;
	private String password;

	public Login_Value() {}
	
	public Login_Value(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
