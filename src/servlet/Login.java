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

import logic.AuthLogic;
import model.Login_Value;
import model.Users;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/claft-lether/not_found");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン
	
		Login_Value loginUserData = null;
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
	        loginUserData = objectMapper.readValue(jsonData, Login_Value.class);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("emali : " + loginUserData.getEmail());
		System.out.println("password : " + loginUserData.getPassword());
		
		AuthLogic logic = new AuthLogic();
		
		Users users = logic.login(loginUserData.getEmail(), loginUserData.getPassword());
		System.out.println("users : " + users);
		if (users != null) {
			int_id_makeJson(users.getId(), response);
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
