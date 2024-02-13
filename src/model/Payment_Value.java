package model;

import java.io.Serializable;

public class Payment_Value implements Serializable {
	private String card_holder;
	private String card_number;
	private String security_code;
	private String expiration_date;
	
	public Payment_Value() {}
	
	public Payment_Value(String card_holder, String card_number, String security_code, String expiration_date) {
		super();
		this.card_holder = card_holder;
		this.card_number = card_number;
		this.security_code = security_code;
		this.expiration_date = expiration_date;
	}

	public String getCard_holder() {
		return card_holder;
	}

	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getExpiration_date() {
		return expiration_date;
	}
	
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	
	
}
