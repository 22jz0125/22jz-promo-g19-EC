package model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;

public class Delivery_All_Json implements Serializable {
	private JsonNode delivery_flg;
	private JsonNode payment_value;
	private JsonNode shipment_value;
	private String count;
	private String item_id;
	private String user_id;
	
	public Delivery_All_Json() {}

	public Delivery_All_Json(JsonNode delivery_flg, JsonNode payment_value, JsonNode shipment_value, String count, String item_id, String user_id) {
		super();
		this.delivery_flg = delivery_flg;
		this.payment_value = payment_value;
		this.shipment_value = shipment_value;
		this.count = count;
		this.item_id = item_id;
	}

	public JsonNode getDelivery_flg() {
		return delivery_flg;
	}

	public void setDelivery_flg(JsonNode delivery_flg) {
		this.delivery_flg = delivery_flg;
	}

	public JsonNode getPayment_value() {
		return payment_value;
	}

	public void setPayment_value(JsonNode payment_value) {
		this.payment_value = payment_value;
	}

	public JsonNode getShipment_value() {
		return shipment_value;
	}

	public void setShipment_value(JsonNode shipment_value) {
		this.shipment_value = shipment_value;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
