import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.List;


public class ClientConnection {
	Socket socket=null;
	ObjectInputStream input;
	ObjectOutputStream output;
	public static void main(String[] gcs){
		
	}
	
	private void getIPAddresses(){
		
		
		Enumeration e;
		
		try {
			
			InetAddress IP=InetAddress.getLocalHost();
			System.out.println("IP of my system is := "+IP.getHostAddress());
			/*e = NetworkInterface.getNetworkInterfaces();
		
			while(e.hasMoreElements())
			{
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration ee = n.getInetAddresses();
				while (ee.hasMoreElements())
				{
					InetAddress i = (InetAddress) ee.nextElement();
					System.out.println(i.getHostAddress());
				}
			}*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public ClientConnection(String serverName,int port){
		
		try{
			socket = new Socket(serverName, port);
			output = new ObjectOutputStream( socket.getOutputStream() ); 
		
	    	input = new ObjectInputStream( socket.getInputStream() );
	    
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	public String insertRow(Record record){//int id,int nodes,int hubs,int switches,String topology,String country,String status){
		String result="";
		try{
			/*Record record=new Record();
			record.setId(id);
			record.setHubs(hubs);
			record.setNodes(nodes);
			record.setTopology(topology);
			record.setCountry(country);
			record.setStatus(status);*/
			output.writeObject("insert");
			output.writeObject(record);
			
			result=(String)input.readObject();
			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	

	public String updateRow(int id,int nodes,int hubs,int switches,String topology,String country,String status){
		String result="";
		try{
			Record record=new Record();
			record.setId(id);
			record.setHubs(hubs);
			record.setNodes(nodes);
			record.setTopology(topology);
			record.setCountry(country);
			record.setStatus(status);
			output.writeObject("update");
			output.writeObject(record);
			
			result=(String)input.readObject();
			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	public String deleteRow(Record record){
		String result="";
		try{
			
			output.writeObject("delete");
			output.writeObject(record);
			
			result=(String)input.readObject();
			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	public List<Record> getAllRecords(){
		List<Record> result=null;
		try{
			
			output.writeObject("all");
			result=(List<Record>)input.readObject();
			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	public List<Record> getRecordsByTopology(String topology){
		List<Record> result=null;
		try{
			
			output.writeObject("topology");
			output.writeObject(topology);
			result=(List<Record>)input.readObject();
			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	public List<Record> getRecordsByNode(int nodes){
		List<Record> result=null;
		try{
			
			output.writeObject("nodes");
			output.writeObject(""+nodes);
			result=(List<Record>)input.readObject();
			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	public void close(){
		try{
			output.writeObject("close");
			Thread.currentThread().sleep(2000);
			socket.close();
		}catch(Exception ex){
			
		}
		
	}
	
}
