package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Size implements Serializable {
	private int id;
	private int item_id;
	private int height_size;
	private int width_size;
	private int depth;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Size() {}
	
	public Size(int id, int item_id, int height_size, int width_size, int depth, Timestamp created_at,
			Timestamp updated_at) {
		super();
		this.id = id;
		this.item_id = item_id;
		this.height_size = height_size;
		this.width_size = width_size;
		this.depth = depth;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getHeight_size() {
		return height_size;
	}

	public void setHeight_size(int height_size) {
		this.height_size = height_size;
	}

	public int getWidth_size() {
		return width_size;
	}

	public void setWidth_size(int width_size) {
		this.width_size = width_size;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
