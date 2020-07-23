package databaseconnection;
import java.sql.*;

public class databasecon
{
	static Connection con;
	public static Connection getconnection()
	{
 		
 			
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Class.forName("com.mysql.jdbc.Driver");	
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpa","root","root");
con = DriverManager.getConnection("jdbc:odbc:simple","system","gc");
		}
		catch(Exception e)
		{
			System.out.println("class error");
		}
		return con;
	}
	
}
