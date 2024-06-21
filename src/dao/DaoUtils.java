package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUtils {
	
	public static  void libererRessources(Connection connection , Statement statement , ResultSet resultSet) {
		
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			libererRessources(connection, statement);
		} catch (Exception e) {
		}
	}
	
	public static void libererRessources(Connection connection , Statement statement) {
	
		try {
				if(statement != null) {
					statement.close();
				}
			} catch (Exception e) {}
		try {
			if(connection != null ) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
	public static PreparedStatement prepareStatement(Connection connection , String requete , Object...parameters) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(requete);
			for (int i = 0; i < parameters.length; i++) {
				statement.setObject(i+1, parameters[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statement;
	}
}
