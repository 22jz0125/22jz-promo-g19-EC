package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	//xampp用
	private static final String CN_STRING = "jdbc:mysql://localhost:3306/craft-leather";
	private static final String USER =       "root";
	private static final String PASS =       "root";
	
	private static DBManager self; // 自分を管理する変数
	/**
	 * コンストラクタ
	 */
	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードに失敗しました : " + e);
			return;
		}
	}
	/**
	 * インスタンスを取得するメソッド
	 */
	public static DBManager getInstance() {
		if (self == null) {		// まだ一度もインスタンス化してなければ
			self = new DBManager();
		}
		return self;
	}

	/**
	 * コネクションを取得
	 */
	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CN_STRING, USER, PASS);
	}
}
