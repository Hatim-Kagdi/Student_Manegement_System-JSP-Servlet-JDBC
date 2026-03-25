package in.keen.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionExample {
	public static Connection getConnection() throws Exception{
		String url = "jdbc:mysql://localhost:3306/Your_DB_name";
		String username = "Your_Username";
		String password = "Your_Password";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}

}
