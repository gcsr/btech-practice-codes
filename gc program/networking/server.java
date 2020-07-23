import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.net.ServerSocket;
import  java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import javax.swing.SwingUtilities;
import java.awt.event.*;
public class server extends JFrame
{
	private JTextField enterField;
	private JTextArea displayArea;
	private ServerSocket server;
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private int counter=1;
	
	public server()
	{
		super("server");
		
		System.out.println("server cons");
		Container container=getContentPane();
		enterField=new JTextField();
		
		enterField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sendData(e.getActionCommand());
				enterField.setText("");
			}
		});
		enterField.setEditable(false);
		container.add(enterField,BorderLayout.NORTH);
		displayArea=new JTextArea();
		
		container.add(new JScrollPane(displayArea),BorderLayout.CENTER);
		
	}
	
	public void runServer()
	{
		System.out.println("runServer");
		try
		{
			System.out.println("yesraaaaaaaa");
			server=new ServerSocket(12345,100);
			System.out.println("yesraaaaaaaa");
			while(true)
			{
				try
				{
					waitForConnection();
					getStreams();
					processConnection();
					
					
				}
				catch(EOFException e)
				{
					System.out.println("Server terminated connection");
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
			counter++;
			
		}
		
		
	}
	
	public void waitForConnection()throws IOException
	{
		System.out.println("waitForConnection");
		displayMessage("waiting for connection\n");
		connection=server.accept();
		displayMessage("Connection "+counter+" received from: "+connection.getInetAddress().getHostName());
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
	public void getStreams()throws IOException
	{
		System.out.println("getStreams");
		output=new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		
		input=new ObjectInputStream(connection.getInputStream());	
		displayMessage("\n got i/o streams\n");	
		
	}
	public void processConnection()throws IOException
	{
		System.out.println("processConnection");
		String message="connection successful";
		
		sendData(message);
		
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
		}while(!message.equals("CLIENT>>>TERMINATE"));
		
	}
	public void sendData(String message)
	{
		System.out.println("sendData");
		try
		{
			output.writeObject("SERVER>>>"+message);
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
			connection.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}