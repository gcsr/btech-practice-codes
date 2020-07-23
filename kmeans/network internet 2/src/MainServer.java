/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleco_client_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import teleco_client_server.TransferObject;

/**
 *
 * @author gc
 */
public class MainServer {
	ServerSocket serverSocket=null;
	ObjectInputStream input;
	ObjectOutputStream output;
	Socket connection;
	public static boolean serverStarted;
	
	public MainServer(){
		
    		
    	}
		
	
	
	public static void main(String[] gcs){
		MainServer server=new MainServer();		
		
                server.createServer();
	}
        
        private void createServer(){        
                System.out.println("in create server");
		try{
			ServerSocket serverSocket = new ServerSocket(55555);
			int id = 0;
			while (true) {				
				Socket clientSocket = serverSocket.accept();
				Client cliThread = new Client(clientSocket, id++);
                                System.out.println("client connected");
				cliThread.start();	    	
			}
		}catch(Exception ex){
                    
                }
        }
	
	
	
	
	
	private void closeConnection()
	{
	      try
	      {
	         input.close(); 
	         connection.close(); 
	      } 
	      catch ( IOException ioException )
	      {
	         ioException.printStackTrace();
	      } 
	} 
	
		
	class Client extends Thread {
		  Socket clientSocket;
		  
		  boolean running = true;

		  Client(Socket s, int i) {
		    clientSocket = s;
		    
		  }

		  public void run() {
		      try {
		    	
		    	ObjectOutputStream output = new ObjectOutputStream( clientSocket.getOutputStream() ); 
			     //output.flush(); // flush output buffer to send header information
			
		    	ObjectInputStream input = new ObjectInputStream( clientSocket.getInputStream() );
		    	//BufferedReader   in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    	//PrintWriter   out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		    	while (running) {
                            String message="";
		    		try {
		    	            TransferObject object=( TransferObject ) input.readObject();
                                    message = object.getOperation();
                                    System.out.println(message);
                                    System.out.println(message);
		    	            if(message.equals("create")){		    	            	
		    	            	int result=insertRecord(object);
		    	            	if(result>0){
		    	            		output.writeObject("success");
		    	            	}else{
		    	            		output.writeObject("failure");
		    	            	}
		    	            }else if(message.equals("update")){
                                        
		    	            	int result=updateRecord(object);
		    	            	if(result>0){
		    	            		output.writeObject("success");
		    	            	}else{
		    	            		output.writeObject("failure");
		    	            	}
		    	            }else if(message.equals("delete")){
		    	            	int result=deleteRecord(object);
		    	            	if(result>0){
		    	            		output.writeObject("success");
		    	            	}else{
		    	            		output.writeObject("failure");
		    	            	}
		    	            }else if(message.equals("all")){
		    	            	List<TransferObject> records=getAll();
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
		  
		  private int insertRecord(TransferObject record){
			  return DatabaseOperations.insertValues(record.getId(), record.getNumberOfNodes(),record.getNumberOfHubs(), record.getNumberOfSwitches(), record.getTopologySructure(), record.getCountry(), record.getStatus(),record.getMtbf(),record.getNetworkLatency());
		  }
		  
		  private int updateRecord(TransferObject record){
                        System.out.println("update operation being called main server");
			  return DatabaseOperations.updateValues(record.getId(), record.getNumberOfNodes(),record.getNumberOfHubs(), record.getNumberOfSwitches(), record.getTopologySructure(), record.getCountry(), record.getStatus(),record.getMtbf(),record.getNetworkLatency());
		  }
		  
		  private int deleteRecord(TransferObject record){
			  return DatabaseOperations.deleteValues(record.getId());
		  }
		  
		  private List<TransferObject> getAll(){
			  return DatabaseOperations.getAllTransferObjects();
		  }
	}
}
