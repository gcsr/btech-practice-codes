/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleco_client_server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author gc
 */
public class ConnectionObject {
        Socket socket=null;
	ObjectInputStream input;
	ObjectOutputStream output;
		
        public ConnectionObject(){
            String ip="";
            try {
			
			InetAddress IP=InetAddress.getLocalHost();
			
			ip=IP.getHostAddress();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		createConnection(ip,55555);
		
        }
	public void createConnection(String serverName,int port){
		
		try{
			socket = new Socket(serverName, port);
			output = new ObjectOutputStream( socket.getOutputStream() ); 
		
                        input = new ObjectInputStream( socket.getInputStream() );
	    
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	public String updateObject(TransferObject object){
		String result="";
		try{
			output.writeObject(object);			
			result=(String)input.readObject();			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	
	public List<TransferObject> listObjects(){
		List<TransferObject> result=null;
		try{
			TransferObject obj=new TransferObject();
                        obj.setOperation("all");
			output.writeObject(obj);
			result=(List<TransferObject>)input.readObject();
			
			
		}catch(Exception ex){
			
		}
		
		return result;
	}
	
	
	
	
	
	public void close(){
		try{
			output.writeObject("close");
			Thread.currentThread().sleep(3000);
			socket.close();
		}catch(Exception ex){
			
		}
		
	}

}
