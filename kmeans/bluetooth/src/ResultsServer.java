import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class ResultsServer {
	ServerSocket serverSocket=null;
	ObjectInputStream input;
	ObjectOutputStream output;
	Socket connection;
	Map<String,double[]> constants=new HashMap<String,double[]>();
	double[] rssiValues;
	String[] deviceNames;
	double[] distances;
	double rssi1m=187;
	
	String finalResult="";
	
	public ResultsServer(Map constants){
		this.constants=constants;
		
		//System.out.println("constructor");
		//double[] c1c2Values=(double[])constants.get("1");
		//System.out.println(c1c2Values[0]+" "+c1c2Values[1]);
		
		
		getIPAddresses();		
		makeConnection();
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
		    serverSocket = new ServerSocket( 30000, 100 ); // create ServerSocket
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
	
	private void waitForConnection() throws IOException{
	     System.out.println("Server Waiting on Port "+30000);
	     connection = serverSocket.accept(); // allow server to accept connection
	} // end method waitForConnection
	
	private void getStreams() throws IOException{
	   
	     input = new ObjectInputStream( connection.getInputStream() );
	     output=new ObjectOutputStream( connection.getOutputStream() );
	     
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
	private void closeConnection(){
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
	
	public void parseMessage(String s){
		
		
		//GC-PCnnnnnn224separator1linelinelineDell Wireless 365 Bluetooth Modulennnnnn171separator14linelinelineRoomNO1linelinelineGC-PCnnnnnn224separator1linelinelineDell Wireless 365 Bluetooth Modulennnnnn171separator14linelineline
		String[] lines=s.split("linelineline");
		ArrayList<Double> list=new ArrayList<Double>();
		int i=0;
		int j=0;
		rssiValues=new double[lines.length-1];
		deviceNames=new String[lines.length-1];
		System.out.println(deviceNames.length);
		for(i=0;i<lines.length;i++){		
			
			System.out.println(lines[i]);
			if(lines[i].startsWith("rssi1m")){				
				rssi1m=Double.parseDouble(lines[i].substring(6));
				continue;
			}
			if(lines[i].contains("RoomNo"))
			break;
			String ss[]=lines[i].split("nnnnnn");
			
			System.out.println(j);
			deviceNames[j]=ss[0];
			
			rssiValues[j]=(Double.parseDouble(ss[1]));
			System.out.print(ss[0]+"\n");
			System.out.print(ss[1]+"\n");			
			System.out.println();
			j++;
		}
		
		String pp=lines[i];
		pp=pp.substring(6);
		System.out.println("pp is"+pp+"jj");	
		
		double[] c1c2Values=constants.get(pp);
		System.out.println(c1c2Values);
		System.out.println(c1c2Values[0]+" "+c1c2Values[1]);
		rssi1m=c1c2Values[2];
		distances=new double[deviceNames.length];
		for(i=0;i<deviceNames.length;i++){
			System.out.println(deviceNames[i]);
			distances[i]=getDistance(rssiValues[i],c1c2Values[0],c1c2Values[1]);
			finalResult+=deviceNames[i]+"\n";
			finalResult+="rssi Value"+rssiValues[i]+"\n";
			finalResult+="distance"+distances[i]+"\n";
		}		
		
		try{
			System.out.println(finalResult);
			output.writeObject(finalResult);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private double getDistance(double rssiValue,double c1,double c2){

        double result;
        double x=0;
        double finalRssi=0;
        double min=600;
        int minIndex=0;
        int i=0;
        double distance=0;

        for(distance=0.01;distance<5;distance+=.1){
            finalRssi=0;
            x=0;
            x=c1*Math.sin(45*distance);
            x*=x;
            x*=Math.log10(distance);           

            finalRssi+=rssi1m+x+10*c2*Math.log10(distance);
            

            if(Math.abs(rssiValue-finalRssi)<min){
                min=Math.abs(rssiValue-finalRssi);
                minIndex=i;
            }
            i++;
        }
        return minIndex*.1;
    }
	
	
	public static double testDistance(double rssiValue,double c1,double c2,double rssi1m){

        double result;
        double x=0;
        double finalRssi=0;
        double min=600;
        int minIndex=0;
        int i=0;
        double distance=0;

        for(distance=0.01;distance<5;distance+=.1){
            finalRssi=0;
            x=0;
            x=c1*Math.sin(45*distance);
            x*=x;
            x*=Math.log10(distance);           

            finalRssi+=rssi1m+x+10*c2*Math.log10(distance);
            
            System.out.println(finalRssi);
            if(Math.abs(rssiValue-finalRssi)<min){
                min=Math.abs(rssiValue-finalRssi);
                minIndex=i;
            }
            i++;
        }
        return minIndex*.1;
    }
	

	


	
}
