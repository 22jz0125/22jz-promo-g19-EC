package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Users implements Serializable{
	 private int id;      	
	 private String name;                                     
	 private String email;                                  
	 private String password;                                  
	 private int isdeleted;                               
	 private Timestamp created_at;
	 private Timestamp updated_at;    
	 
	 public Users(){};
	 
	 public Users(int id, String name, String email, String password, int isdeleted, Timestamp created_at,
			Timestamp updated_at) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isdeleted = isdeleted;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	 
	 
	 public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}

}
