import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ChatInterface extends Remote{	
	public void addMessage(String inMessage) throws RemoteException;	
	public String getMessages()throws RemoteException;
}
