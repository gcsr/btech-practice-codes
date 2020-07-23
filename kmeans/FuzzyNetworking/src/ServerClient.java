import java.io.BufferedWriter;
import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;

import javax.swing.JTextArea;

public class ServerClient {
	
	int requestNo=0;
	Socket socket;
	InputStream in;
	DataInputStream din;
	Map pathBandwidths;
	int pathNo;
	Calculator calculator;
	//JTextArea textArea;
	public ServerClient(Socket socket,Map pathBandwidths,Calculator calculator){
		//System.out.println("creating client");
		this.socket=socket;
		//requests=new HashMap<Integer,Integer>();
		this.calculator=calculator;
		this.pathBandwidths=pathBandwidths;
		//this.textArea=textArea;
		createInputStream();
		
	}
	
	private void createInputStream()
	{
		try{
			
			in=socket.getInputStream();			
			din=new DataInputStream(in);
			//System.out.println(din);
			//DataOutputStream out=new DataOutputStream(socket.getOutputStream());			
			@SuppressWarnings("deprecation")
			String path=din.readLine();
			//System.out.println("path is "+path);
			pathNo=Integer.parseInt(path.substring(0,path.indexOf(' ')));
			//System.out.println("path no is "+pathNo);
			String remainingPath=path.substring(path.indexOf(' ')+1);
			String nextRouter=remainingPath.substring(0,remainingPath.indexOf(' '));	
			
			//System.out.println("nextRouter is"+nextRouter+"nextPath is"+nextPath);
			
			if(nextRouter.contains("size"))
			{
				System.out.println("can start sending with the size "+nextRouter.substring(nextRouter.indexOf("size")+4));
			}
			
			else
			if(nextRouter.contains("end")){
				//code to send to source
				//System.out.println("reached end");
				
				/*nextRouter=path.substring(3,path.indexOf(' '));
				String nextPath=path.substring(path.indexOf(' ')+1);
				*/
				
				nextRouter=remainingPath.substring(3,remainingPath.indexOf(' '));
				String nextPath=pathNo+" "+remainingPath.substring(remainingPath.indexOf(' ')+1);
				//System.out.println("final Source is"+nextRouter+"final path is"+nextPath);
				socket=new Socket(nextRouter,40606);
				BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				bout.write(nextPath);
				bout.close();
				socket.close();
				
			}
			
			else
			{
				
				String ff=remainingPath.substring(remainingPath.indexOf("size")+4,remainingPath.length()-1);
				//System.out.println("sizeis"+ff+"size");
				int size=Integer.parseInt(ff);				
				int sz=getBandwidth(size);
				
				
				/*path=path.substring(0,path.indexOf("size"))+"size"+sz+" ";
				System.out.println("path is "+path+".");
				String nextPath=path.substring(path.indexOf(' ')+1);*/
				
				remainingPath=remainingPath.substring(0,remainingPath.indexOf("size"))+"size"+sz+" ";
				//System.out.println("path is "+remainingPath+".");
				String nextPath=pathNo+" "+remainingPath.substring(remainingPath.indexOf(' ')+1);
				
				callRouter(nextRouter,nextPath);
			}
			close();
		}catch(IOException ex){
			
			ex.printStackTrace();
		}
	}
	
	private void callRouter(String nextRouter,String nextPath)
	{
		Socket socket;
		// TODO Auto-generated method stub
		try{
			
			
			socket=new Socket(nextRouter,40606);
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bout.write(nextPath);
			bout.close();
			socket.close();
		}catch(IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	
	private void close() throws IOException
	{
		try{
			din.close();
			in.close();
			socket.close();
		}catch(IOException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}
	
	private int getBandwidth(int reqRate)
	{
	   
	   Allocation all=(Allocation)pathBandwidths.get(pathNo);
	   if(all!=null){
		   //System.out.println("path no is "+pathNo);
		   all.startTime=System.currentTimeMillis();
		   routerPrint();
		   return all.bandwidth;
	   }
	   else{		   
		   //System.out.println("enter size this router supports");
		   //requestNo++;
		   //requests.put(requestNo,pathNo);
		   //Scanner scanner=new Scanner(System.in);
		   int sz=calculator.getAllowedSize(reqRate);//scanner.nextInt();
		   pathBandwidths.put(pathNo,new Allocation(reqRate,sz,System.currentTimeMillis(),requestNo));
		   //System.out.println(" inside else path no is "+pathNo+" size is "+sz);
		   routerPrint();
		   return sz;
	   }
		   
	}
	private void routerPrint(){
		synchronized(StartServer.textArea){
		StartServer.textArea.setText("Buffer Capacity "+Calculator.bufferCapacity+" \n minimum Queue "+Calculator.minimumQueue);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
