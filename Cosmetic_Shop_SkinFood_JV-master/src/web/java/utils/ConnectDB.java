package web.java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public Connection getDBConnection() {
		String connectUrl = "jdbc:mysql://localhost:3306/dangu?useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		String password = "tovy12345";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(connectUrl, username, password);
			return connect;
		} catch (Exception e) {
			System.err.println("connect fail!");
			e.printStackTrace();
		}
		return null;
	}
}
