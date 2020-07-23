/*
 * owner:Gc Sekhar
 * This is a simple probing client 
 * Its purpose is to call server and send commands to server
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Socket socket = new Socket("127.0.0.1", 26543);
			DataOutputStream bw=new DataOutputStream(socket.getOutputStream());
			DataInputStream br=new DataInputStream(socket.getInputStream());
			Scanner scanner=new Scanner(System.in);			
			System.out.println("Enter Command");
			//read command from command line
			String line=scanner.next();
			//write command to server
			bw.writeUTF(line);			
			bw.flush();			
			
			//read result from server
			String s=br.readUTF();			
			System.out.println(s);
			
			//clean up work
			br.close();
			bw.close();			
			socket.close();
			
		}catch(Exception ex){
			System.out.println("Exception "+ex.getMessage()+" raised");
			System.out.println("StackTrace is");
			ex.printStackTrace();
		}		

	}

}
