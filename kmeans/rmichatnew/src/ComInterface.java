import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComInterface extends Remote{	
	public void putMessage(String inMessage) throws RemoteException;	
	public String getMessages()throws RemoteException;
}