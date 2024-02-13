package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDAO {
	
	public List<Item> get() {
		List<Item> list = new ArrayList<>();
		
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			String sql = 
					//"SELECT * FROM items";
					"SELECT * FROM items i JOIN sizes s ON i.id = s.item_id";
			PreparedStatement stmt = cn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			// データをリストに格納
			while(rs.next()) {
				Item item = rs2model(rs);
				list.add(item);				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Item> getCategory(int category_id) {
		List<Item> list = new ArrayList<>();
		
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			String sql = 
					//"SELECT * FROM items";
					"SELECT * FROM items i JOIN sizes s ON i.id = s.item_id WHERE i.category_id = ?";
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, category_id);
			ResultSet rs = stmt.executeQuery();
			
			// データをリストに格納
			while(rs.next()) {
				Item item = rs2model(rs);
				list.add(item);				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public Item find(int id) {
		Item item = null;
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			// プレースホルダで変数部分を定義
			String sql = 
			//		"SELECT * FROM items WHERE id = ?";
					"SELECT * FROM items i JOIN sizes s ON i.id = s.item_id WHERE i.id = ?";

//			String sql = "SELECT * FROM ((items LEFT OUTER JOIN colors ON items.id = colors.item_id) LEFT OUTER JOIN sizes ON item.id = sizes.item_id) WHERE id = ?";
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			// データをリストに格納
			if (rs.next()) {
				item = rs2model(rs);
				//System.out.println(item);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return  item;
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
	private Item rs2model(ResultSet rs) throws SQLException {
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


