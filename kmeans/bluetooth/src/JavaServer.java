import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;


public class JavaServer {
	ServerSocket serverSocket=null;
	ObjectInputStream input;
	ObjectOutputStream output;
	Socket connection;
	public static boolean serverStarted;
	
	public static void main(String[] gcs){
		ServerSocket m_ServerSocket = new ServerSocket(12111);
	    int id = 0;
	    while (true) {
	      Socket clientSocket = m_ServerSocket.accept();
	      ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
	      cliThread.start();
	    }
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
	
	public void makeConnection(){
		try // set up server to receive connections; process connections
		{
		    serverSocket = new ServerSocket( 20000, 100 ); // create ServerSocket
		    while ( true )
		    {
		        try
		        {
		            waitForConnection(); // wait for a connection
		            getStreams(); // get input & output streams
		            processConnection(); // process connection
		            //closeConnection();
		         } // end try
		         catch ( EOFException eofException )
		         {
		             System.out.println("Exception while making connections");
		             eofException.printStackTrace();
		         } // end catch
		         finally
		         {
		            //closeConnection(); // close connection
		         } // end finally
		     } // end while
		 } // end try
		 catch ( IOException ioException )
		 {
		     ioException.printStackTrace();
		 } // end catch		
	}
	
	private void waitForConnection() throws IOException
	{
	     System.out.println("Server Waiting on Port "+20000);
	     connection = serverSocket.accept(); // allow server to accept connection
	} // end method waitForConnection
	
	private void getStreams() throws IOException
	{
	     // set up output stream for objects
	     //output = new ObjectOutputStream( connection.getOutputStream() ); 
	     //output.flush(); // flush output buffer to send header information
	
	     input = new ObjectInputStream( connection.getInputStream() );
	     
	} 
	
	private void processConnection() throws IOException{
		String message;
		try // read message and display it
	        {
	            message = ( String ) input.readObject(); // read new message
	            System.out.println(message);
	            parseMessage(message);
	           // WriteToFile.writeToFile(message);
	            closeConnection();
	    
	        } // end try
	        catch ( ClassNotFoundException classNotFoundException )
	        {
	      
	        } // end catch
	    
	 } // end method processConnection
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
		    	
		    	ObjectOutputStream output = new ObjectOutputStream( connection.getOutputStream() ); 
			     //output.flush(); // flush output buffer to send header information
			
		    	ObjectInputStream input = new ObjectInputStream( connection.getInputStream() );
		    	//BufferedReader   in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    	//PrintWriter   out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		    	while (running) {
		    		String message;
		    		try {
		    	            message = ( String ) input.readObject(); // read new message
		    	            System.out.println(message);
		    	            String result=parseMessage(message);
		    	           // WriteToFile.writeToFile(message);
		    	            //closeConnection();
		    	    
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
	}
}
