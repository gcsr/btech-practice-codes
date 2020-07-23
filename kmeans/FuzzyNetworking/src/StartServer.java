import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;



public class StartServer extends JFrame{

	ServerSocket server;
	Calculator calculator;
	static JTextArea textArea;
	
	Map pathBandwidths=new LinkedHashMap<Integer,Allocation>();;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StartServer(args);
	}
	
	public StartServer(String[] gcs){
		super("ROUTER");
		setSize(400,400);
		textArea=new JTextArea("BufferCapacity is 3000 \n minimumQueue is 300");
		add(textArea);		
		setVisible(true);
		if(gcs.length!=1){
			System.out.println("execution: java ServerSocket port");
			System.exit(1);
		}
		
		int port=Integer.parseInt(gcs[0]);		
		try{
			 server=new ServerSocket(port);
		}catch(IOException ex){
			
			System.out.println("Error creating Server");
			System.exit(0);
		
		}
		
		calculator=new Calculator(3000,300,100);
		new Timer(20000,new Reaper(pathBandwidths)).start();
		
		while(true){
			//System.out.println("in while path");
			
				try{
					Socket socket=server.accept();
					ServerClient client=new ServerClient(socket,pathBandwidths,calculator);
					//System.out.println("no error here");
					//server.close();
				}catch(IOException ex){
					System.out.println("error creating socket");	
				}
		}
	}

}
