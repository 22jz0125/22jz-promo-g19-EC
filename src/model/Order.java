package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Order implements Serializable {
	private int id;
	private Item item;
	private int user_id;
	private int payment_method_id;
	private int count;
	private String address;
	private int address_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int delivery_status;
	
	public Order() {}
	
	public Order(int id, Item item, int user_id, int payment_method_id, int count, String address, int address_id,
			Timestamp created_at, Timestamp updated_at, int delivery_status) {
		super();
		this.id = id;
		this.item = item;
		this.user_id = user_id;
		this.payment_method_id = payment_method_id;
		this.count = count;
		this.address = address;
		this.address_id = address_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.delivery_status = delivery_status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPayment_method_id() {
		return payment_method_id;
	}

	public void setPayment_method_id(int payment_method_id) {
		this.payment_method_id = payment_method_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
//
//	public Timestamp getCreated_at() {
//		return created_at;
//	}
	
    public String getCreated_at() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return dateFormat.format(new Date(created_at.getTime()));
    }

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

//	public Timestamp getUpdated_at() {
//		return updated_at;
//	}
	
	public String getUpdated_at() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
	    return dateFormat.format(new Date(updated_at.getTime()));
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	public int getDelivery_status() {
		return delivery_status;
	}
	
	public void setDelivery_status(int delivery_status) {
		this.delivery_status = delivery_status;
	}
}
