import java.io.File;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;


import javax.swing.JOptionPane;

public class GUIInputFile{
	
	
	String fileContentsText="";

	public static void main(String[] gcs){
		String fileContentsText="";
		try{
					
			/*if(!JavaServer.objectCreated){
				JavaServer seer=new JavaServer();
				seer.start();
				
			}*/
			
			if(gcs.length==2){
				fileContentsText=gcs[0]+" "+gcs[1];
				JOptionPane.showMessageDialog(null,fileContentsText);
				System.out.println(fileContentsText);
			}else if(gcs.length==1){
				fileContentsText=gcs[0];
				System.out.println(fileContentsText);
			}else{
				JOptionPane.showMessageDialog(null,"invalid parameters");
				return;
			}
			
			
			/*while(!JavaServer.sessionCreated){
				
			}*/
			
			try{
				File file =new File("MyCareNet/response.xml");
				
				//if file doesnt exists, then create it
				if(file.exists()){
					file.delete();
				}
			}catch(Exception ex){
				
			}
			System.out.println("hi there");
			
			Socket socket=null;            
            ObjectOutputStream output=null;

            int SERVERPORT=20000;
            try {
                //Toast.makeText(getApplicationContext(),"Trying to make connection",Toast.LENGTH_SHORT).show();
            	InetAddress IP=InetAddress.getLocalHost();
                //InetAddress serverAddr = InetAddress.getByName(ipAddress);
                //InetAddress serverAddr = InetAddress.getByName("192.168.0.102");
                socket = new Socket(IP, SERVERPORT);
                //Toast.makeText(getApplicationContext(),"Connection Established",Toast.LENGTH_SHORT).show();

                output = new ObjectOutputStream( socket.getOutputStream() );
                //output.writeObject(rssiValues);
                output.writeObject(fileContentsText);
                System.out.println("output flushed");

                output.flush();
               // socket.close();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }catch(Exception ex){
                ex.printStackTrace();
                //killApp();
            }finally{
            	socket.close();
            }

			//fileContentsText=fileContentsText.substring(2);
			
			
			

		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Invalid input or response");
			return;
		}		
		
		
	}
}