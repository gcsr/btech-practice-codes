import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;


public class ChatServerImpl extends UnicastRemoteObject implements ChatInterface{
	
	String chatMessage="";
	
	public ChatServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public void addMessage(String inMessage) throws RemoteException {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();		
		chatMessage+=inMessage;
		chatMessage+=currentTime+"\n";
	}

	@Override
	public String getMessages() throws RemoteException {
		// TODO Auto-generated method stub
		return chatMessage;
	}
}