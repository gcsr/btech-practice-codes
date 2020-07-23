/**
 * @(#)CLessServer.java
 *
 *
 * @author 
 * @version 1.00 2010/7/9
 */
import javax.swing.JTextArea;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class CLessServer extends JFrame {
	private JTextArea displayArea;
	private DatagramSocket socket;

    public CLessServer() {
    	super("Server");
    	displayArea=new JTextArea();
    	getContentPane().add(new JScrollPane(displayArea),BorderLayout.CENTER);
    	
    	try{
    		socket=new DatagramSocket(5000);
    		    		
    	}
    	catch(SocketException e)
    	{
    		e.printStackTrace();
    		System.exit(1);
    	}
    }
    public void waitForPackets(){
    	while(true){
    		try{
    			byte data[]=new byte[100];
    			DatagramPacket receivePacket=new DatagramPacket(data,data.length);
    			socket.receive(receivePacket);
    			displayMessage("\nPacket received: "+"\nFrom host: "+receivePacket.getAddress()+
    				"\nHost Port: "+receivePacket.getPort()+
    					"\nLength: "+receivePacket.getLength()+
    						"\nContaining:\n\t"+new String(receivePacket.getData(),0,receivePacket.getLength()));
    						
    			sendPacketToClient(receivePacket);			
    		}
    		catch(IOException e){
    			displayMessage(e.toString()+"\n");
    			e.printStackTrace();
    		}
    	}
    		
    }
    public void displayMessage(final String mToDisplay)
	{
		System.out.println("displayMessge");
		SwingUtilities.invokeLater(
			new Runnable()
			{
				public void run()
				{
					displayArea.append(mToDisplay);
					displayArea.setCaretPosition(displayArea.getText().length());
				}
			});
	}
	private void sendPacketToClient(DatagramPacket receivePacket)
	throws IOException
	{
		displayMessage("\n\n Echo data to client...");
		
		DatagramPacket sendPacket=new DatagramPacket(receivePacket.getData(),receivePacket.getLength(),
			receivePacket.getAddress(),receivePacket.getPort());
			
			socket.send(sendPacket);
			displayMessage("Packet sent\n");
		
	}
    
    
}