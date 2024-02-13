package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Delivery_Flg implements Serializable {
	@JsonProperty("Fdelivery")
	private boolean Fdelivery;
	
	@JsonProperty("Fshipment")
	private boolean Fshipment;
	
	@JsonProperty("payment")
	private boolean payment;
	
	public Delivery_Flg() {}
	
	public Delivery_Flg(boolean fdelivery, boolean fshipment, boolean payment) {
		super();
		Fdelivery = fdelivery;
		Fshipment = fshipment;
		this.payment = payment;
	}

	public boolean isFdelivery() {
		return Fdelivery;
	}

	public void setFdelivery(boolean fdelivery) {
		Fdelivery = fdelivery;
	}

	public boolean isFshipment() {
		return Fshipment;
	}

	public void setFshipment(boolean fshipment) {
		Fshipment = fshipment;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}
}
