package model;

import java.io.Serializable;

public class Shipment_Value implements Serializable{
	private String address;
	private String country;
	private String firstname;
	private String lastname;
	private String postnumber;
	
	public Shipment_Value() {}
	
	public Shipment_Value(String address, String country, String firstname, String lastname, String postnumber) {
		super();
		this.address = address;
		this.country = country;
		this.firstname = firstname;
		this.lastname = lastname;
		this.postnumber = postnumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPostnumber() {
		return postnumber;
	}

	public void setPostnumber(String postnumber) {
		this.postnumber = postnumber;
	}
	
	
}
