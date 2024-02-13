package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Order;

public class OrderDAO {
	
	public List<Order> get(int user_id) {
		List<Order> list = new ArrayList<>();
		
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			String sql = 
					//"SELECT * FROM Orders";
					"SELECT * FROM purchase_history Where user_id = ? ORDER BY created_at DESC";
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			
			// データをリストに格納
			while(rs.next()) {
				Order order = rs2model(rs);
				list.add(order);				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Order> find(int user_id, int item_id) {
		List<Order> list = new ArrayList<>();
		
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			String sql = 
					//"SELECT * FROM Orders";
					"SELECT * FROM purchase_history Where user_id = ? and item_id = ?";
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, user_id);
			stmt.setInt(2, item_id);
			ResultSet rs = stmt.executeQuery();
			
			// データをリストに格納
			while(rs.next()) {
				Order order = rs2model(rs);
				list.add(order);				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private Item getItemFromPurchase(int item_id) {
		DBManager manager = DBManager.getInstance();
		Item item = null;
		try(Connection cn = manager.getConnection()) {
			String sql = 
					"SELECT * FROM items i JOIN sizes s ON i.id = s.item_id WHERE i.id = ?";
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, item_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				item = rs2modelForItem(rs);				
			}	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	public String getCategoryName(int category_id) {
		String name = null;
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			// プレースホルダで変数部分を定義
			String sql = 
			//		"SELECT * FROM items WHERE id = ?";
					"SELECT name FROM categories WHERE id = ?";

//			String sql = "SELECT * FROM ((items LEFT OUTER JOIN colors ON items.id = colors.item_id) LEFT OUTER JOIN sizes ON item.id = sizes.item_id) WHERE id = ?";
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, category_id);
			ResultSet rs = stmt.executeQuery();
			
			// データをリストに格納
			if (rs.next()) {
				name = rs2modelForCategory(rs);
				//System.out.println(item);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return  name;
	}
	
	/**
	 * ResultSetの行データをモデルの形に変換するメソッド
	 * @param rs 変換前のデータ
	 * @return 変換後のデータ
	 */
	private Order rs2model(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		int item_id = rs.getInt("item_id");
		int user_id = rs.getInt("user_id");
		int payment_method_id = rs.getInt("payment_method_id");
		int count = rs.getInt("count");
		String address = rs.getString("address");
		int address_id = rs.getInt("address_id");
		Timestamp created_at = rs.getTimestamp("created_at");
		Timestamp updated_at = rs.getTimestamp("updated_at");
		int delivery_status = rs.getInt("delivery_status");
		
		System.out.println("item_id : " + item_id);
		Item item = getItemFromPurchase(item_id);

		return new Order(id, item, user_id, payment_method_id, count, address, address_id, created_at, updated_at, delivery_status);
	}
	
	/**
	 * 
	 * @param rs
	 * @return Item型　アクセサ
	 * @throws SQLException
	 */
	private Item rs2modelForItem(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		int price = rs.getInt("price");
		String description = rs.getString("description");
		String imageURL = rs.getString("imageURL");
		String material = rs.getString("material");
		int weight = rs.getInt("weight");
		int category_id = rs.getInt("category_id");
		int isdeleted = rs.getInt("is_deleted");
		Timestamp created_at = rs.getTimestamp("created_at");
		Timestamp updated_at = rs.getTimestamp("updated_at");
		int stock = rs.getInt("stock");
		int height_size = rs.getInt("height_size");
		int width_size = rs.getInt("width_size");
		int depth = rs.getInt("depth");

		String category_name = getCategoryName(category_id);

		return new Item(id, name, price, description, imageURL, material, weight, category_name, isdeleted, created_at, updated_at, stock, height_size, width_size, depth);
	}
	
	private String rs2modelForCategory(ResultSet rs) throws SQLException {
		String name = rs.getString("name");
		return name;
	}
}
