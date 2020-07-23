import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 
		
		        try {
		            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		            Connection con = DriverManager.getConnection("jdbc:odbc:simple","system","gc");
		            Statement stmt = con.createStatement();
		            System.out.println("Creating table in given database...");
		            stmt = con.createStatement();
		            
		            String sql = "CREATE TABLE VideoLink( " 
		                          +  " contentType VARCHAR(255), " + 
		                         " videoType VARCHAR(20))"; 

		            stmt.executeUpdate(sql);
		            System.out.println("Created table in given database...");
		            stmt.close();
		            con.close();
		 
		        } catch (Exception ex) {
		           ex.printStackTrace();
		        }
		 
		// TODO Auto-generated method stub

	}

}
