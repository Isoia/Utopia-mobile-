package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private Connection con = null;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/utopia?useUnicode=true&characterEncoding=utf-8&serverTimeZone=UTC";
			String id = "root";
			String pw = "1234";
			
			con = DriverManager.getConnection(url,id,pw);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println("접속 실패");
		}
		return con;
	}

}
