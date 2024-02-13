package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Delivery_All_Json;
import model.Delivery_Flg;
import model.Payment_Value;
import model.Shipment_Value;

public class PurchaseDAO {
	
//	public void userCreate() {
//		
//	}
	
	public int  getPaymentMethod(String payment_name) {
		int payment_id = 0;		//決済方法名を取得し、DBで照合後にIDを返す
		return payment_id;
	}
	
	public void cardCreate(Payment_Value payment) {
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			String sql = "INSERT INTO cards(user_id, card_number, card_holder, expiration_date) VALUES(?, ?, ?, ?)";
			PreparedStatement stmt = cn.prepareStatement(sql);
			//stmt.setInt(1, user_id);
			stmt.setString(2, payment.getCard_number());
			stmt.setString(3, payment.getCard_holder());
			stmt.setString(4, payment.getExpiration_date());
			int rs = stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addressCreate(Shipment_Value shipment) {
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			String sql = "INSERT INTO address_preset(user_id, card_number, card_holder, expiration_date) VALUES(?, ?, ?, ?)";
			PreparedStatement stmt = cn.prepareStatement(sql);
			//stmt.setInt(1, user_id);
//			stmt.setString(2, shipment.get());
//			stmt.setString(3, shipment.get());
//			stmt.setString(4, shipment.get());
			int rs = stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean historyCreate(Delivery_Flg delivery_flg, Payment_Value payment, Shipment_Value shipment, Delivery_All_Json delData) {
		int ret = -1;
		int pay_method = payment_Decision(delivery_flg);
		int ship_dec = (shipment_Decision(delivery_flg));
		String address = address_Create(shipment);
		String sql_names = "(item_id, user_id, payment_method_id, count, address, address_id)";
		String sql_count = "VALUES(?, ?, ?, ?, ?, ?)";
		if(ship_dec == -1) {
			sql_names = "(item_id, user_id, payment_method_id, count, address)";
			sql_count = "VALUES(?, ?, ?, ?, ?)";
		}

		
		DBManager manager = DBManager.getInstance();
		try(Connection cn = manager.getConnection()) {
			String sql = "INSERT INTO purchase_history"
					+ sql_names
					+ sql_count;
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(delData.getItem_id()));
			stmt.setInt(2, Integer.parseInt(delData.getUser_id()));
			stmt.setInt(3, pay_method);
//			stmt.setInt(4, -1); //クレジットカード登録できるようにしたら使う
			stmt.setInt(4, Integer.parseInt(delData.getCount()));
			stmt.setString(5,address);
			if(ship_dec != -1) {
				stmt.setInt(6, ship_dec);
			}
//			ResultSet rs = stmt.executeUpdate();
			int rs = stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 支払方法判定メソッド
	 * 
	 * @param delivery_flg
	 * @return	1:クレジットカード	2:代引き支払い
	 */
	public int payment_Decision(Delivery_Flg delivery_flg) {
		if(delivery_flg.isPayment()) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	/**
	 * アドレスプリセット使用可否判定メソッド
	 * 
	 * @param delivery_flg
	 * @return アドレスプリセットID  or  -1(null扱い)
	 */
	public int shipment_Decision(Delivery_Flg delivery_flg) {
		if(delivery_flg.isFshipment()) {
			return 999999;	//アドレスプリセット実装したら
		}
		else {
			return -1;
		}
	}
	
	/**
	 * 表示用住所生成メソッド
	 * 
	 * @param shipment
	 * @return 住所
	 */
	public String address_Create(Shipment_Value shipment) {
		return shipment.getPostnumber() + "   " + shipment.getAddress();
	}
	
}
