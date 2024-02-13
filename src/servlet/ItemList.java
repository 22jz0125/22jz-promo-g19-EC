package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ItemDAO;
import model.Item;

/**
 * Servlet implementation class VideoList
 */
@WebServlet("/ItemList")
public class ItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		int category_id = -1;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//itemsテーブルのカテゴリーidを取得
		try {
			String S_category_id = request.getParameter("category_id");
			category_id = Integer.parseInt(S_category_id);
			System.out.println("category_id = " + category_id);
		}
		catch(Exception e) {
			System.out.println("詳細だよ");
		}
		
		//itemsテーブルの検索用idを取得
		try {
			String S_id = request.getParameter("id");
			id = Integer.parseInt(S_id);
			System.out.println("id = " + id);
		}
		catch(Exception e) {
			System.err.println("id取れないか一覧だよ");
		}
		
		ItemDAO dao = new ItemDAO();
		
		if(id != -1) {
			//一致するidのデータを取得
			 Item item = dao.find(id);
			 List<Item> list = new ArrayList<Item>();
			 list.add(item);
			 System.out.println(list);
			 makeJson(list, response);
		}
		else {
			//すべてのデータを取得
				//カテゴリーごとのデータ取得
			 List<Item> list = dao.getCategory(category_id);
			 makeJson(list, response);
		}
		
		
//		System.out.println("htmlに送るよ");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/index.html");
//		dispatcher.forward(request, response);
	}


	/**
	 * 商品一覧、商品詳細のjsonデータ作成用メソッド
	 * @param list
	 * @param response
	 */
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
