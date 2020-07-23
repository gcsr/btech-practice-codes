import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.RemoteException;


public class Server {
	
	public static void main(String gcs[]){
		try{
			RealComObject f=new RealComObject();
			Naming.rebind("group",f);
			System.out.println("group server initiated");			
		}
		catch(Exception ex){
			System.out.println("error creating object");
			ex.printStackTrace();
		}		
	}


}
