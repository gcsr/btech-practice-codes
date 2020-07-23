import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;


public class RealComObject extends UnicastRemoteObject implements ComInterface{
	
	String group="";
	
	public RealComObject() throws RemoteException {
		super();
	}	
	
	public void putMessage(String inMessage) throws RemoteException {		
		group+=inMessage;		
	}
	public String getMessages() throws RemoteException {	
		return group;
	}
}