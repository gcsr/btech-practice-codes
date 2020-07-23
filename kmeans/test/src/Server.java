/*
 * 
 * owner:Gc Sekhar
 * This is a test program on Sockets
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Server {
	//startTime is for finding uptime
	long startTime=0;
	//startTime is for finding number of unique clients
	long uniqueClients=0;
	//startTime is for finding number of requests
	long requests=0;
	List uniqueClientIps=new LinkedList<String>();
	public Server(){
		
		startTime=System.currentTimeMillis();		
		try{
			ServerSocket serverSocket=new ServerSocket(26543);
			while(true){				
				Socket socket=serverSocket.accept();
				String ip=socket.getInetAddress().getHostAddress();
				
				 
				checkClient(ip);
				
				DataInputStream br=new DataInputStream(socket.getInputStream());				
				String s=br.readUTF();
				requests++;
				//code that handles uptime command
				if(s.toUpperCase().equals("UPTIME")){
					DataOutputStream bw=new DataOutputStream(socket.getOutputStream());
					long milliS=System.currentTimeMillis();
					milliS-=startTime;
					String output="System is running from "+milliS+" milli seconds \n";
					//System.out.println(output);						
					bw.writeUTF(output);
					bw.flush();
					bw.close();
					socket.close();
				}	
				
				//code that handles requests command
				else
				if(s.toUpperCase().equals("REQUESTS")){
					DataOutputStream bw=new DataOutputStream(socket.getOutputStream());					
					String output="System has served "+requests+" requests inclusive \n";
					bw.writeUTF(output);
					bw.flush();
					bw.close();
					socket.close();
				}	
				
				//code that handles clientss command
				else
					if(s.toUpperCase().equals("CLIENTS")){
						DataOutputStream bw=new DataOutputStream(socket.getOutputStream());					
						String output="System has served requests from "+uniqueClientIps.size()+" clients \n";
						bw.writeUTF(output);
						bw.flush();
						bw.close();
						socket.close();
					}	
				//code that handles stop command
				else
				if(s.toUpperCase().equals("STOP")){					
					DataOutputStream bw=new DataOutputStream(socket.getOutputStream());					
					String output="System is shutting down \r\n";
					bw.writeUTF(output);
					bw.flush();
					bw.close();
					br.close();
					socket.close();
					break;
				}
				
				//code that ignores other commands
				else{
					//System.out.println("hi");
					DataOutputStream bw=new DataOutputStream(socket.getOutputStream());					
					String output="invalid command \n";
					bw.writeUTF(output);
					bw.flush();
					bw.close();
					br.close();
					//socket.close();
				}
				
				br.close();
													
			}
			
			//cleanup work
			serverSocket.close();
		}catch(Exception ex){
			System.out.println("Exception "+ex.getMessage()+" raised");
			System.out.println("StackTrace is");
			ex.printStackTrace();
		}
	}
	
	
	/*
	 * This method is to check repeated clients
	 * if the client is from different system it will automatically
	 * update it into list(uniqueClientIps).
	 * uniqueClientIps is used to save client ip addresses 
	 */
	private void checkClient(String client){
		//checking if client is already in the list
		if(!uniqueClientIps.contains(client)){
			System.out.println(client);
			uniqueClientIps.add(client);
		}
	}
	
	public static void main(String[] gcs){
		new Server();
	}

}
