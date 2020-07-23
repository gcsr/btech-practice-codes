import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class MainServer {
	ServerSocket serverSocket=null;
	ObjectInputStream input;
	ObjectOutputStream output;
	Socket connection;
	public static boolean serverStarted;
	
	public MainServer(){
		String ip="";
		try {
			
			InetAddress IP=InetAddress.getLocalHost();
			ip=IP.getHostAddress();
			System.out.println("IP of my system is := "+ip);
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
		try{
			ServerSocket serverSocket = new ServerSocket(30000);
			int id = 0;
			while (true) {				
				Socket clientSocket = serverSocket.accept();
				ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
				cliThread.start();	    	
			}
		}catch(Exception ex){
    		
    	}
		
	}
	
	public static void main(String[] gcs){
		MainServer server=new MainServer();
		
		/*getIPAddresses();
		//createSession();
		JavaServer server=new JavaServer();
		server.makeConnection();*/
	}
	
	
	
	private static void getIPAddresses(){
		
		
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
	
	
	private void closeConnection()
	{
	      try
	      {
	         input.close(); // close input stream  
	         connection.close(); // close socket   
	      }  // end try
	      catch ( IOException ioException )
	      {
	         ioException.printStackTrace();
	      } // end catch
	} // end method closeConnection
	
	public synchronized String parseMessage(String s){
		//GC-PCnnnnnn224separator1linelinelineDell Wireless 365 Bluetooth Modulennnnnn171separator14linelinelineRoomNO1linelinelineGC-PCnnnnnn224separator1linelinelineDell Wireless 365 Bluetooth Modulennnnnn171separator14linelineline
		String[] lines=s.split("linelineline");
		ArrayList<Double> list=new ArrayList<Double>();
		int i=0;
		for(i=0;i<lines.length;i++){
			System.out.println(lines[i]);
			if(lines[i].contains("RoomNO"))
			break;
			String ss[]=lines[i].split("separator");
			String[] nmn=ss[0].split("nnnnnn");
			System.out.print(nmn[1]+"\t");
			list.add(Double.parseDouble(nmn[1]));
			System.out.print(ss[1]+"\t");
			list.add(Double.parseDouble(ss[1]));
			
			System.out.println();
		}
		
		String pp=lines[i];
		pp=pp.substring(6);
		System.out.println("RommNO is "+pp);	
		return "";
	}
	
	class ClientServiceThread extends Thread {
		  Socket clientSocket;
		  int clientID = -1;
		  boolean running = true;

		  ClientServiceThread(Socket s, int i) {
		    clientSocket = s;
		    clientID = i;
		  }

		  public void run() {
		    System.out.println("Accepted Client : ID - " + clientID + " : Address - "
		        + clientSocket.getInetAddress().getHostName());
		    try {
		    	
		    	ObjectOutputStream output = new ObjectOutputStream( clientSocket.getOutputStream() ); 
			     //output.flush(); // flush output buffer to send header information
			
		    	ObjectInputStream input = new ObjectInputStream( clientSocket.getInputStream() );
		    	//BufferedReader   in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    	//PrintWriter   out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		    	while (running) {
		    		String message;
		    		try {
		    	            message = ( String ) input.readObject(); // read new message
		    	            if(message.equals("insert")){
		    	            	Record record=(Record)input.readObject();
		    	            	int result=insertRow(record);
		    	            	if(result>0){
		    	            		output.writeObject("success");
		    	            	}else{
		    	            		output.writeObject("failure");
		    	            	}
		    	            }else if(message.equals("update")){
		    	            	Record record=(Record)input.readObject();
		    	            	int result=updateRow(record);
		    	            	if(result>0){
		    	            		output.writeObject("success");
		    	            	}else{
		    	            		output.writeObject("failure");
		    	            	}
		    	            }else if(message.equals("delete")){
		    	            	Record record=(Record)input.readObject();
		    	            	int result=deleteRow(record);
		    	            	if(result>0){
		    	            		output.writeObject("success");
		    	            	}else{
		    	            		output.writeObject("failure");
		    	            	}
		    	            }else if(message.equals("all")){
		    	            	List<Record> records=getAll();
		    	            	output.writeObject(records);
		    	            }else if(message.equals("topology")){
		    	            	String topology=(String)input.readObject();
		    	            	List<Record> records=getTopology(topology);
		    	            	output.writeObject(records);
		    	            }else if(message.equals("nodes")){
		    	            	String nodes=(String)input.readObject();
		    	            	int nodesInt=Integer.parseInt(nodes);
		    	            	List<Record> records=getTopology(nodes);
		    	            	output.writeObject(records);
		    	            }else{
		    	            	try{
		    	            		System.out.println("closing connection");
		    	            		output.close();
		    	            		clientSocket.close();
		    	            		return;
		    	            	}catch(Exception ex){
		    	            		
		    	            	}
		    	            }
		    	          
		    	    }catch ( ClassNotFoundException classNotFoundException ){
		    	      
		    	    } //
		    		/*String clientCommand = in.readLine();
		    		System.out.println("Client Says :" + clientCommand);
		    		if (clientCommand.equalsIgnoreCase("quit")) {
		    			running = false;
		    			System.out.print("Stopping client thread for client : " + clientID);
		    		} 	else {
		        	out.println(clientCommand);
		        	out.flush();*/
		        }
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }	
		  
		  private int insertRow(Record record){
			  return DatabaseOperations.insertValues(record.getId(), record.getNodes(),record.getHubs(), record.getSwitches(), record.getTopology(), record.getCountry(), record.getStatus());
		  }
		  
		  private int updateRow(Record record){
			  return DatabaseOperations.updateValues(record.getId(), record.getNodes(),record.getHubs(), record.getSwitches(), record.getTopology(), record.getCountry(), record.getStatus());
		  }
		  
		  private int deleteRow(Record record){
			  return DatabaseOperations.deleteValues(record.getId());
		  }
		  
		  private List<Record> getAll(){
			  return DatabaseOperations.getAllRecords();
		  }
		  
		  private List<Record> getTopology(String topology){
			  	return DatabaseOperations.getAllRecordsByTopology(topology);
		  }
		  
		  private List<Record> getNodes(int nodes){
			  	return DatabaseOperations.getAllRecordsByNodes(nodes);
		  }
		  
	}
}
