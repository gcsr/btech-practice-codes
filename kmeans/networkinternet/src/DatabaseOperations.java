import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DatabaseOperations {
	
	private static String database="networkinternet";
	private static String username="root";
	private static String password="";
	
	public static int insertValues(int id,int nodes,int hubs,int switches,String topology,String country,String status){
		try{
			Connection connection=DatabaseConnection.getConnection(database, username, password);
			String query="insert into network values("+id+","+nodes+","+hubs+","+switches+",'"+topology+"','"+country+"','"+status+"')";
			
			int returnValue=DatabaseConnection.updateQuery(connection, query);
			DatabaseConnection.closeConnection(connection);
			return returnValue;
		}catch(Exception ex){
			return 0;
		}
		
	}
	
	public static int updateValues(int id,int nodes,int hubs,int switches,String topology,String country,String status){
		try{
			Connection connection=DatabaseConnection.getConnection(database, username, password);
			String query="update network set nodes="+nodes+",hubs="+hubs+",switches="+switches+",topology='"+topology+"',country='"+country+"',status='"+status+"' where id="+id;
		
			int returnValue=DatabaseConnection.updateQuery(connection, query);
			DatabaseConnection.closeConnection(connection);
			return returnValue;
		}catch(Exception ex){
			return 0;
		}
	}
	
	public static int deleteValues(int id){
		try{
			Connection connection=DatabaseConnection.getConnection(database, username, password);
			String query="delete from network where id="+id;
			
			int returnValue=DatabaseConnection.updateQuery(connection, query);
			DatabaseConnection.closeConnection(connection);
			return returnValue;
		}catch(Exception ex){
			return 0;
		}
	}
	
	
	public static List<Record> getAllRecords(){
		Connection connection=DatabaseConnection.getConnection(database, username, password);
		String query="select *from network";
		
		ResultSet rs=DatabaseConnection.execute(connection, query);;
		List<Record> result=new ArrayList<Record>();
		try{
			while(rs.next()){
				Record record=new Record();
				record.setCountry(rs.getString("country"));
				record.setId(rs.getInt("id"));
				record.setHubs(rs.getInt("hubs"));
				record.setSwitches(rs.getInt("switches"));
				record.setTopology(rs.getString("topology"));
				record.setCountry(rs.getString("country"));
				record.setStatus(rs.getString("status"));
				result.add(record);
			}
		}catch(Exception ex){
			
		}
		
		try{
			if(rs!=null)
				rs.close();
			DatabaseConnection.closeConnection(connection);
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	public static List<Record> getAllRecordsByTopology(String topology){
		Connection connection=DatabaseConnection.getConnection(database, username, password);
		String query="select *from network where topology=?";
		List<Record> result=new ArrayList<Record>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			ps=connection.prepareStatement(query);
			ps.setString(1, topology);
		
			rs=ps.executeQuery();
		
			while(rs.next()){
				Record record=new Record();
				record.setCountry(rs.getString("country"));
				record.setId(rs.getInt("id"));
				record.setHubs(rs.getInt("hubs"));
				record.setSwitches(rs.getInt("switches"));
				record.setTopology(rs.getString("topology"));
				record.setCountry(rs.getString("country"));
				record.setStatus(rs.getString("status"));
				result.add(record);
			}
		}catch(Exception ex){
			
		}
		
		try{
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			DatabaseConnection.closeConnection(connection);
		}catch(Exception ex){
			
		}
		return result;
	}
	
	public static List<Record> getAllRecordsByNodes(int nodes){
		Connection connection=DatabaseConnection.getConnection(database, username, password);
		String query="select *from network where nodes!=?";
		List<Record> result=new ArrayList<Record>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			ps=connection.prepareStatement(query);
			ps.setInt(1, nodes);
		
			rs=ps.executeQuery();
		
			while(rs.next()){
				Record record=new Record();
				record.setCountry(rs.getString("country"));
				record.setId(rs.getInt("id"));
				record.setHubs(rs.getInt("hubs"));
				record.setSwitches(rs.getInt("switches"));
				record.setTopology(rs.getString("topology"));
				record.setCountry(rs.getString("country"));
				record.setStatus(rs.getString("status"));
				result.add(record);
			}
		}catch(Exception ex){
			
		}
		
		try{
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			DatabaseConnection.closeConnection(connection);
		}catch(Exception ex){
			
		}
		return result;
	}
	
	
	
	
	
}
