package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Item implements Serializable {
	private int id;
	private String name;
	private int price;
	private String description;
	private String imageURL;
	private String material;
	private int weight;
	private String category_name;
	private int isdeleted;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int stock;
	private Size size;

	public Item() {}

	public Item(int id, String name, int price, String description, String imageURL, String material, int weight, String category_name,
				int isdeleted, Timestamp created_at, Timestamp updated_at, int stock, int height_size, int width_size, int depth) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageURL = imageURL;
		this.material = material;
		this.weight = weight;
		this.category_name = category_name;
		this.isdeleted = isdeleted;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.stock = stock;
		
        if (this.size == null) {
            this.size = new Size();
        }
		this.size.setHeight_size(height_size);
		this.size.setWidth_size(width_size);
		this.size.setDepth(depth);
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public String getMaterial() {
		return material;
	}

	public int getWeight() {
		return weight;
	}
	
	public int getHeight_size() {
		return size.getHeight_size();
	}
	
	public int getWidth_size() {
		return size.getWidth_size();
	}
	
	public int getDepth() {
		return size.getDepth();
	}
	
	public String getCategory_name() {
		return category_name;
	}

	public int isIsdeleted() {
		return isdeleted;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setHeight_size(int height_size) {
        if (this.size == null) {
            this.size = new Size();
        }
		this.size.setHeight_size(height_size);
	}
	
	public void setWidth_size(int width_size) {
        if (this.size == null) {
            this.size = new Size();
        }
		this.size.setWidth_size(width_size);
	}
	
	public void setDepth(int depth) {
        if (this.size == null) {
            this.size = new Size();
        }
		this.size.setDepth(depth);
	}

}