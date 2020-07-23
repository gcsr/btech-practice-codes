import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;


public class ChatServer {
	
	public static void main(String gcs[]){
		try{
			ChatServerImpl f=new ChatServerImpl();
			Naming.rebind("chatserver",f);
			System.out.println("I'm just a message broker");			
		}
		catch(RemoteException rex){
			System.out.println("Exception in fibonacciimpl: main"+rex);
		}
		catch(MalformedURLException ex){
			System.out.println("malforedurlexception: "+ex);
		}
	}


}
