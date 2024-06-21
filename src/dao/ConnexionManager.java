package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionManager {
	
	private static final String DB_HOST = "jdbc:mysql://localhost:3306/tp_jee";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	public static Connection getInstance() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASSWORD);
			
		} catch (Exception e) {
			System.out.println("la connexion ne passe pas ");
			e.printStackTrace();
			
		}
		
		return connection;
	}

}
