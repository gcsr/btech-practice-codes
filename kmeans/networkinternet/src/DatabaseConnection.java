

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseConnection {
	
	/*
	 * This method open up a connection and returns coneections
	 * @param database database name
	 * @param username database user
	 * @param database user password
	 */
	public static Connection getConnection(String database,String username,String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
	 
		//System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	 
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/"+database,username, password);
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	 
		if (connection != null) {
			//System.out.println("");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		return connection;
	}
	
	/*
	 * This method is used to close connection
	 * @param connection connection to be closed
	 */
	public static void closeConnection(Connection connection){
		try{
			if(connection!=null){
				connection=null;
			}
		}catch(Exception ex){
			
		}
		
		finally{
			try{
				if(connection!=null)
					connection=null;
			}catch(Exception ex){
				
			}
		}
	}
	
	/*
	 * This method will execute a query and res=turns result
	 * @param connection database connection
	 * @param query database query
	 * @return resultset
	 */
	
	public static ResultSet execute(Connection connection,String query){
		try{
			//System.out.println(query);
			PreparedStatement pStmt=connection.prepareStatement(query);
		
			ResultSet rs=pStmt.executeQuery();
		
			return rs;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	
	}
	
	
	
	/*
	 * This method will execute a update and res=turns result
	 * @param connection database connection
	 * @param query database query
	 * @return resultset
	 */
	
	public static int updateQuery(Connection connection,String query){
		try{
			
			PreparedStatement pStmt=connection.prepareStatement(query);
			System.out.println(query);
			int rs=pStmt.executeUpdate();
		
			return rs;
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	
	}
	
	
	
}
