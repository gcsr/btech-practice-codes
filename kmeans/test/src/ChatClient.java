import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;

public class ChatClient{
	public static void main(String gcs[]){
		
		try{
			String[] gcfs=Naming.list("rmi://localhost/");
			for(String g:gcfs){
				System.out.println(g);
			}		
			Object remChat=Naming.lookup("rmi://localhost/chatserver");		
			ChatInterface chat=(ChatInterface)remChat;
			//	cal.addMessage("Hi there");
			//System.out.println("Messages are "+cal.getMessages());	
		
			GridBagFrame gridBagFrame = new GridBagFrame(chat);
			gridBagFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			gridBagFrame.setSize( 400, 600 ); // set frame size
			gridBagFrame.setVisible( true ); // display fra
		}
		catch(NotBoundException e){
			e.printStackTrace();
		}
		catch(RemoteException e){
			e.printStackTrace();
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}	
	}
	
}