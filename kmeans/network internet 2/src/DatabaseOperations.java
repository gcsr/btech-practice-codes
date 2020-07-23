package teleco_client_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import teleco_client_server.TransferObject;


public class DatabaseOperations {
	
	private static String database="networkinternet";
	private static String username="root";
	private static String password="";
	
	public static int insertValues(int id,int nodes,int hubs,int switches,String topology,String country,String status,double mtbf,double latency){
		try{
			Connection connection=DatabaseConnection.getConnection(database, username, password);
			String query="INSERT INTO `network`"
                                + "(`nodes`, `hubs`, `switches`, `topology`, `country`, `status`, `mtbf`, `latency`) VALUES"
                                + "("+nodes+","+hubs+","+switches+",'"+topology+"','"+country+"','"+status+"',"+mtbf+","+latency+")";
			
			int returnValue=DatabaseConnection.updateQuery(connection, query);
			DatabaseConnection.closeConnection(connection);
			return returnValue;
		}catch(Exception ex){
			return 0;
		}
		
	}
	
	public static int updateValues(int id,int nodes,int hubs,int switches,String topology,String country,String status,double mtbf,double latency){
            System.out.println("update in server called");
		try{
			Connection connection=DatabaseConnection.getConnection(database, username, password);
			String query="update network"
                                + " set nodes="+nodes+","
                                + "hubs="+hubs+","
                                + "switches="+switches+","
                                + "topology='"+topology+"'"
                                + ",country='"+country+"',"
                                + "status='"+status+"',"
                                + "mtbf="+mtbf+","
                                + "latency="+latency+" "
                                + "where id="+id;
                        
                        System.out.println(query);
		
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
	
	
	public static List<TransferObject> getAllTransferObjects(){
		Connection connection=DatabaseConnection.getConnection(database, username, password);
		String query="select *from network";
		
		ResultSet rs=DatabaseConnection.execute(connection, query);;
		List<TransferObject> result=new ArrayList<TransferObject>();
		try{
			while(rs.next()){
				TransferObject record=new TransferObject();
				record.setCountry(rs.getString("country"));
				record.setId(rs.getInt("id"));
				record.setNumberOfHubs(rs.getInt("hubs"));
				record.setNumberOfSwitches(rs.getInt("switches"));
				record.setTopologySructure(rs.getString("topology"));
				record.setCountry(rs.getString("country"));
				record.setStatus(rs.getString("status"));
                                record.setNumberOfNodes(rs.getInt("nodes"));
                                record.setMtbf(rs.getDouble("mtbf"));
                                record.setNetworkLatency(rs.getDouble("latency"));
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
	
	
	
	
	
}
