package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import beans.Utilisateur;

public class UtilisateurDao
{
	private static int							lastId			= 0;
	private static final String SELECT_UTILISATEUR_REQ_STRING = "SELECT * FROM utilisateurs";
	private static final String SELECT_UTILISATEUR_BY_ID_REQ_STRING = "SELECT * FROM utilisateurs where id = ? ";
	private static final String SELECT_UTILISATEUR_LOGIN_REQ_STRING = "SELECT * FROM utilisateurs where login = ? ";
	private static final String INSERT_UTILISATEUR_REQ_STRING = "INSERT INTO utilisateurs value(0,?,?,?,?)";
	private static final String UPDATE_UTILISATEUR_REQ_STRING = "UPDATE  utilisateurs set nom=?,prenom=?,login=?,password=? where id=?";
	private static final String DELETE_UTILISATEUR_REQ_STRING = "DELETE FROM utilisateurs where id=?";
//	private static ArrayList<Utilisateur>	utilisateurs	= new ArrayList<Utilisateur>();
	
	
	
	public static boolean ajouter(Utilisateur user) 
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement preparedStatement= null;
		try {
			preparedStatement = DaoUtils.prepareStatement(connection,INSERT_UTILISATEUR_REQ_STRING, user.getNom(),user.getPrenom(),user.getLogin(),user.getPassword());
			int resultSet = preparedStatement.executeUpdate();
			if(resultSet != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DaoUtils.libererRessources(connection, preparedStatement);
		}
		return true;
	}
	
	public static ArrayList<Utilisateur> lister() throws SQLException
	{
		Connection connection = ConnexionManager.getInstance();
		ArrayList<Utilisateur> userArrayList = new ArrayList<Utilisateur>();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SELECT_UTILISATEUR_REQ_STRING);
		while(resultSet.next()) {
			int idString = Integer.valueOf(resultSet.getString("id"));
			String nomString = resultSet.getString("nom");
			String prenomString = resultSet.getString("prenom");
			String loginString = resultSet.getString("login");
			String passwordString = resultSet.getString("password");
			userArrayList.add(new Utilisateur(idString,nomString,prenomString,loginString,passwordString));
		}
		return userArrayList;
	}

	public static boolean modifier(Utilisateur user) 
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_UTILISATEUR_REQ_STRING);
			preparedStatement.setString(1, user.getNom());
			preparedStatement.setString(2, user.getPrenom());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getId());
			int resultSet = preparedStatement.executeUpdate();
			if(resultSet != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DaoUtils.libererRessources(connection, preparedStatement );
		}
		return false;
	}

	public static boolean supprimer(int id)
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(DELETE_UTILISATEUR_REQ_STRING);
			preparedStatement.setInt(1, id);
			int id1 = preparedStatement.executeUpdate();
			if(id1>0)
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DaoUtils.libererRessources(connection, preparedStatement);
		}

		return false;
	}

	public static Utilisateur get(int id) throws SQLException
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UTILISATEUR_BY_ID_REQ_STRING);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) 
		{
			String nomString = resultSet.getString("nom");
			String prenomString = resultSet.getString("prenom");
			String loginString = resultSet.getString("login");
			String passwordString = resultSet.getString("password");
			return new Utilisateur(id,nomString,prenomString,loginString,passwordString);
		}
		return null;
	}
	public static Utilisateur get(String login) throws SQLException   
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UTILISATEUR_LOGIN_REQ_STRING);
		preparedStatement.setString(1, login);
		ResultSet resultSet = preparedStatement.executeQuery();
		try {
			if(resultSet.next()) 
			{
				int id = resultSet.getInt("id");
				String nomString = resultSet.getString("nom");
				String prenomString = resultSet.getString("prenom");
				String passwordString = resultSet.getString("password");
				return new Utilisateur(id,nomString,prenomString,login,passwordString);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DaoUtils.libererRessources(connection, preparedStatement, resultSet);
			}
		
		return null;
	}
	
}
