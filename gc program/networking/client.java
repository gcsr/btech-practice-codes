import java.io.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;


public class client extends JFrame
{
	private JTextField enterField;
	private JTextArea displayArea;
	private Socket client;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String chatServer;
	String message="";
	
	public client(String host)
	{
		super("client");
		System.out.println("client cons");
		chatServer=host;
		Container container=getContentPane();
		enterField=new JTextField();
		
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sendData(e.getActionCommand());
				enterField.setText("");
			}
		});
		container.add(enterField,BorderLayout.NORTH);
		displayArea=new JTextArea();
		
		container.add(new JScrollPane(displayArea),BorderLayout.CENTER);
		
	}
	
	public void runClient()
	{
		System.out.println("runClient");
		try
		{
		
				try
				{
					connectToServer();
					getStreams();
					processConnection();
					
					
				}
				catch(EOFException e)
				{
					System.out.println("Server terminated connection");
				}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
			
			
		}
		
		
	}
	
	public void connectToServer()throws IOException
	{
		System.out.println("connectToServer");
		displayMessage("attempting connection\n");
		client=new Socket(InetAddress.getByName(chatServer),12345);
		displayMessage("Connected to "+client.getInetAddress().getHostName());
	}
	public void displayMessage(final String mToDisplay)
	{
		System.out.println("displayMessage");
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
	public void getStreams()throws IOException
	{
		System.out.println("getStreams");
		output=new ObjectOutputStream(client.getOutputStream());
		output.flush();
		
		input=new ObjectInputStream(client.getInputStream());	
		displayMessage("\n got i/o streams\n");	
		
	}
	public void processConnection()throws IOException
	{
		System.out.println("processConnection");
		setTextFieldEditable(true);

		do
		{
			try
			{
				message=(String)input.readObject();
				displayMessage("\n"+message);
			}
			catch(ClassNotFoundException e)
			{
				displayMessage("\n unknown object received");
			}
		}while(!message.equals("SERVER>>>TERMINATE"));
		
	}
	public void sendData(String message)
	{
		System.out.println("sendData");
		try
		{
			output.writeObject("CLIENT>>>"+message);
			output.flush();
			
		}
		catch(IOException e)
		{
			displayArea.append("\n error writing object");
		}
	}
	public void setTextFieldEditable(final boolean editable)
	{
		System.out.println("setTextFieldEditable");
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				enterField.setEditable(true);
			}
		});
	}
	public void closeConnection()
	{
		System.out.println("closeConnection");
		displayMessage("\n terminating connection\n");
		setTextFieldEditable(false);
		try
		{
			output.close();
			input.close();
			client.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}