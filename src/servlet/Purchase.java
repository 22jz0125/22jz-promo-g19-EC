package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ItemDAO;
import dao.PurchaseDAO;
import model.Delivery_All_Json;
import model.Delivery_Flg;
import model.Item;
import model.Payment_Value;
import model.Shipment_Value;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/Purchase")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			String sid = request.getParameter("id");
			id = Integer.parseInt(sid);
			System.out.println("id = " + id);
		}
		catch(Exception e) {
			System.err.println("id取れてないよ");
		}
		
		ItemDAO dao = new ItemDAO();
		
		if(id != -1) {
			//一致するidのデータを取得
			 Item item = dao.find(id);
			 List<Item> list = new ArrayList<Item>();
			 list.add(item);
			 makeJson(list, response);
		}
		else {
			//すべてのデータを取得
			 List<Item> list = dao.get();
			 makeJson(list, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("postだよ");
			
			//取得したjsonを読めるように処理
			StringBuilder buffer = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        String jsonData = buffer.toString();	    
	        System.out.println(jsonData);
	        ObjectMapper objectMapper = new ObjectMapper();
	        Delivery_All_Json delData = objectMapper.readValue(jsonData, Delivery_All_Json.class);
	        
	        
	        
	        //json内のjsonをそれぞれ使用できるように処理
	        JsonNode delivery_flg_json = delData.getDelivery_flg();
	        Delivery_Flg delivery_flg = objectMapper.treeToValue(delivery_flg_json, Delivery_Flg.class);
	        JsonNode payment_value_json = delData.getPayment_value();
	        Payment_Value payment = objectMapper.treeToValue(payment_value_json, Payment_Value.class);
	        JsonNode shipment_value_json = delData.getShipment_value();
	        Shipment_Value shipment = objectMapper.treeToValue(shipment_value_json, Shipment_Value.class);
	        //各json表示
	        System.out.println("Delivery_Flg: " + delivery_flg_json);
	        System.out.println("Payment_value: " + payment_value_json);
	        System.out.println("Shipment_value: " + shipment_value_json);
	        System.out.println("count: " + delData.getCount());
	        System.out.println("item_id: " + delData.getItem_id());
	        System.out.println("user_id: " + delData.getUser_id());
	        
	        
			//購入履歴に商品を登録
			System.out.println("支払方法 : " + delivery_flg.isPayment());

			PurchaseDAO dao = new PurchaseDAO();
			boolean purchase_decision = dao.historyCreate(delivery_flg, payment, shipment, delData);
			
			if(purchase_decision) {
				System.out.println("ヨシ！！！");
			}
			else {
				System.out.println("いややややややややややややややや！！！");
			}
			
			boolean_makeJson(purchase_decision, response);
		}
		catch(Exception e) {
			System.err.println("id取れないか一覧だよ(post)" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * データベースからの情報からJsonを生成するメソッド
	 * @param decision
	 * @param response
	 */
	private static void boolean_makeJson(boolean decision, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String decisionJson = mapper.writeValueAsString(decision);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(decisionJson);
			System.out.println("Json : " + decisionJson);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (JsonProcessingException e) {
			System.err.println("JsonProcessingException のエラーだよ");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.err.println("Exception エラーだよ");
			e.printStackTrace();
		}
	}
	
	private static void makeJson(List<Item> list, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String listJson = mapper.writeValueAsString(list);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(listJson);
			System.out.println("Json : " + listJson);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (JsonProcessingException e) {
			System.err.println("JsonProcessingException のエラーだよ");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.err.println("Exception エラーだよ");
			e.printStackTrace();
		}
	}

}
