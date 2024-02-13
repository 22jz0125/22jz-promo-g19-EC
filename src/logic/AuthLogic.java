package logic;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsersDAO;
import model.Users;

/**
 * ログイン・ログアウト処理を行うクラス
 * @author d.sugawara
 *
 */
public class AuthLogic {
	/**
	 * ログイン処理を行う
	 * @param email
	 * @param password
	 * @return 成功時はログインしたユーザ、失敗時はnull
	 */

	public Users login(String email, String password) {
		UsersDAO dao = new UsersDAO();
		Users user = dao.findByEmail(email);
		
		if ((user != null) && (BCrypt.checkpw(password, user.getPassword()))) {
			return user;
		}
		
		return null;
	}
	
	/**
	 * ログアウト処理を行う
	 * @return なし
	 */
	public void logout(HttpSession session) {
		if (isLoggedIn(session)) {
			session.removeAttribute("loginUser");
		}
	}
	
	/**
	 * ログイン状態を確認する
	 * @param session
	 * @return ログインしていれば true、していなければ false
	 */
	public boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("loginUser") != null;
	}
}
