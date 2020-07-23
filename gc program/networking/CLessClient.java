import java.awt.Container;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.net.SocketException;
import java.net.DatagramSocket;

public class CLessClient extends JFrame {
	private JTextArea displayArea;
	private JTextField enterField;
	private DatagramSocket socket;
	

    public CLessClient() {
    	super("Client");
    	System.out.println("client cons");
		Container container=getContentPane();
		enterField=new JTextField("Type Message here");
		enterField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try{
					displayArea.append("\n Sending packet Containing: "+e.getActionCommand()+"\n");
					
					String message=e.getActionCommand();
					byte data[]=message.getBytes();
					
					DatagramPacket sendPacket=new DatagramPacket(data,data.length,
																InetAddress.getLocalHost(),5000);
					socket.send(sendPacket);
					displayArea.append("Packet Sent\n");
			
				}
				catch(IOException ex)
				{
					displayMessage(e.toString()+"\n");
					ex.printStackTrace();
					
				}
			
			}
		});
		container.add(enterField,BorderLayout.NORTH);
		displayArea=new JTextArea();
		
		container.add(new JScrollPane(displayArea),BorderLayout.CENTER);
		try{
			socket=new DatagramSocket();
		}
		catch(SocketException e)
		{
			e.printStackTrace();
			System.exit(1);
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
	public void waitForPackets()
	{
			while(true){
    		try{
    			byte data[]=new byte[100];
    			DatagramPacket receivePacket=new DatagramPacket(data,data.length);
    			socket.receive(receivePacket);
    			displayMessage("\nPacket received: "+"\nFrom host: "+receivePacket.getAddress()+
    				"\nHost Port: "+receivePacket.getPort()+
    					"\nLength: "+receivePacket.getLength()+
    						"\nContaining:\n\t"+new String(receivePacket.getData(),0,receivePacket.getLength()));
    						
    		}
    		catch(IOException e){
    			displayMessage(e.toString()+"\n");
    			e.printStackTrace();
    		}
    	}

	}
    
}