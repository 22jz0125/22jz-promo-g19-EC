package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.OrderDAO;
import model.Order;

/**
 * Servlet implementation class OrderList
 */
@WebServlet("/OrderList")
public class OrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int item_id = -1;
		int user_id = -1;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String S_user_id = request.getParameter("user_id");
		user_id = Integer.parseInt(S_user_id);
		System.out.println("user_id : " + user_id);
		try {
			String S_item_id = request.getParameter("item_id");
			item_id = Integer.parseInt(S_item_id);
			System.out.println("item_id : " + item_id);
		}
		catch(Exception e) {
			System.out.println("購入一覧表示");
		}
		
		OrderDAO dao = new OrderDAO();

		if(item_id > -1) {
			List<Order> order = dao.find(user_id, item_id);
			makeJson(order, response);
		}
		else if(user_id > -1) {
			List<Order> order = dao.get(user_id);
			makeJson(order, response);
		}
		else {
			System.out.println("ユーザーIDが取れていない");
		}

		
	}
	
	/**
	 * 購入一覧、購入詳細のjsonデータ作成用メソッド
	 * @param list
	 * @param response
	 */
	private static void makeJson(List<Order> list, HttpServletResponse response) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
