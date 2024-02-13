package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsersDAO;
import model.CreateUserModel;
import model.Users;

@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		System.out.println("getいやだ");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		// ユーザを新規登録
		CreateUserModel newUserData = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			
			StringBuilder buffer = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        String jsonData = buffer.toString();	    
	        ObjectMapper objectMapper = new ObjectMapper();
	        newUserData = objectMapper.readValue(jsonData, CreateUserModel.class);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		boolean user_decision = false;
		int user_id = -1;
		Users user = null;
		
		try {
			UsersDAO dao = new UsersDAO();
			System.out.println("name : " + newUserData.getName());
			System.out.println("email : " + newUserData.getEmail());
			System.out.println("password : " + newUserData.getPassword());
			user_decision = dao.create(newUserData.getName(), newUserData.getEmail(), newUserData.getPassword());
			user = dao.findByEmail(newUserData.getEmail());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(user_decision) {
			System.out.println("ユーザーの追加に成功しました");
		}
		else {
			System.out.println("ユーザーの追加に失敗しました");
		}
		
		int_id_makeJson(user.getId(), response);
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
	
	/**
	 * jsでuser_idをセッションに保存する用Json
	 * @param user_id
	 * @param response
	 */
	private static void int_id_makeJson(int user_id, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String user_id_Json = mapper.writeValueAsString(user_id);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(user_id_Json);
			System.out.println("Json : " + user_id_Json);
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
