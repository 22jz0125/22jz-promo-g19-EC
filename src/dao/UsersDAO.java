package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import model.Users;

public class UsersDAO {
 public List<Users> get(){ 
	 List<Users> list = new ArrayList<>(); //Itemオブジェクトを格納するための新しいArrayListを作成
	 DBManager manager = DBManager.getInstance();
	 try(Connection cn = manager.getConnection()){
		 String sql = "SELECT * FROM users";
		 PreparedStatement stmt = cn.prepareStatement(sql);
		 ResultSet rs = stmt.executeQuery();
//		 System.out.println(rs);
		 //リストに格納
		 while(rs.next()) {
			 Users users = rs2model(rs);
			 list.add(users);
		 }	 
	 }catch(SQLException e) {
		 e.printStackTrace();
	 }		System.out.println(list);
	 
	 return list;
	 
 }
 public Users find (int id) {
	 Users users = null;
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			// プレースホルダで変数部分を定義
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			// データをリストに格納
			if (rs.next()) {
				users = rs2model(rs);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return  users;
 }
 
 /**
  * テーブルの中から、指定されたメールアドレスを持つレコードを返すメソッド
  * @param email メールアドレス
  * @return 発見したデータ。なければnull
  */
 public Users findByEmail(String email) {
    Users user = null;
    DBManager manager = DBManager.getInstance();
    try(Connection cn = manager.getConnection()) {
        // プレースホルダで変数部分を定義
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = cn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            user = rs2model(rs);
        }
    } catch(SQLException e) {
        e.printStackTrace();
    }
    
    return  user;
 }

 /**
  * DBにデータを追加する
  * @return 成功時は追加したデータ、失敗時はnull
  */
 public boolean create(String name, String email, String password) {
    int ret = -1;
    
    // 重複確認
    if (findByEmail(email) != null) {
        System.out.println("該当ユーザは既に存在しています");
        return false;
    }
    
    // DBにデータを追加
    DBManager manager = DBManager.getInstance();
    try(Connection cn = manager.getConnection()) {
        // パスワードをハッシュ化
 	   String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        
        // プレースホルダで変数部分を定義
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        PreparedStatement stmt = cn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, hashed);
        ret = stmt.executeUpdate();
        
    } catch(SQLException e) {
        e.printStackTrace();
    }
    
    if (ret >= 0) {
        return true;
    }
    return false;
 }
 
 
	/**
	 * ResultSetの行データをモデルの形に変換するメソッド
	 * @param rs 変換前のデータ
	 * @return 変換後のデータ
	 */
private Users rs2model(ResultSet rs)throws SQLException {
	/* rsの値を取得し、それぞれの変数に代入 */
	
	 int id = rs.getInt("id");
	 String name = rs.getString("name");
	 String email = rs.getString("email");
	 String password = rs.getString("password");
	 int isdeleted = rs.getInt("is_deleted");
		Timestamp created_at = rs.getTimestamp("created_at");
		Timestamp updated_at = rs.getTimestamp("updated_at");
	return new Users(id, name, email, password, isdeleted, created_at, updated_at);
}


/**
 * DBにデータを追加する
 * @return 成功時は追加したデータ、失敗時はnull
 */
public Users create(String email, String password) {
   int ret = -1;
   
   // 重複確認
   if (findByEmail(email) != null) {
       System.out.println("該当ユーザは既に存在しています");
       return null;
   }
   
   // DBにデータを追加
   DBManager manager = DBManager.getInstance();
   try(Connection cn = manager.getConnection()) {
       // パスワードをハッシュ化
	   String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
       
       // プレースホルダで変数部分を定義
       String sql = "INSERT INTO users (email, passward) VALUES (?, ?)";
       PreparedStatement stmt = cn.prepareStatement(sql);
       stmt.setString(1, email);
       stmt.setString(2, hashed);
       
       ret = stmt.executeUpdate();
       
   } catch(SQLException e) {
       e.printStackTrace();
   }
   
   if (ret >= 0) {
       return findByEmail(email);
   }
   return null;
}
}