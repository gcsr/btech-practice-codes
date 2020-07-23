import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.SwingUtilities;

public class NetworkingGUIFrame extends JFrame {
	
	final JTextField tField;
	ObjectInputStream input=null;
	ObjectOutputStream output=null;
	Socket client;
	String s="";
	JButton dir,get,block,bye;
	JPanel panel;

	private class Listener implements ActionListener
	{
		String s;
		
		public void actionPerformed(ActionEvent ev)
		{
			if(ev.getActionCommand()=="dir")
			writeMessage("dir");
			else if(ev.getActionCommand()=="bye")
				writeMessage("bye");
			else
				writeMessage(ev.getActionCommand()+tField.getText());
			getResult();
			//System.out.println(ev.getActionCommand());
		}
	}
	
	public NetworkingGUIFrame()
	{
		setLayout(new BorderLayout());
		
		
		tField=new JTextField("enter ur command here");
		
		panel=new JPanel();
		
		dir=new JButton("dir");
		get=new JButton("get ");
		block=new JButton("blocks ");
		bye=new JButton("bye");
		panel.add(bye);
		panel.add(dir);
		Listener listener=new Listener();
		dir.addActionListener(listener);
		get.addActionListener(listener);
		block.addActionListener(listener);
		bye.addActionListener(listener);
		panel.add(get);
		panel.add(block);
		add(panel,BorderLayout.SOUTH);
		add(tField,BorderLayout.NORTH);
		setSize(400, 400);
		setVisible(true);
		//setDefaultCloseOPeration(JFrame.EXIT_ON_CLOSE);
		try
		{
			client=new Socket("127.0.0.1",20000);
			output=new ObjectOutputStream(client.getOutputStream());
			input=new ObjectInputStream(client.getInputStream());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void writeMessage(String gh)
	{
		try
		{
			System.out.println("writingMessage");
			output.writeObject(gh);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void getResult()
	{
		try
		{
			System.out.println("inside getResult");
			MyClass obj=(MyClass)input.readObject();
			System.out.println("red message as ");
			if(obj.no==10)
			{
				System.out.println("red obj.no "+obj.no);
				input.close();
				output.close();
				client.close();
			}
			else if(obj.no==0)
			{
				System.out.println("red obj.no "+obj.no);
				System.out.println("red obj.name "+obj.filename);
				if((obj.filename!=null)&&(obj.data!=null))
					
				{
					FileOutputStream fout=new FileOutputStream(obj.filename);
					fout.write(obj.data);
					fout.close();
					
				}
				else if((obj.filename==null)&&(obj.s!=null))
				{
					System.out.println("setting text");
					JOptionPane.showMessageDialog(null,obj.s,"message",JOptionPane.INFORMATION_MESSAGE);
					//tArea.setText(obj.s);
			
					System.out.println("set text");
				}
				else if((obj.filename!=null)&&(obj.s!=null))
				{
					String pp="no of blocks in file "+obj.filename+" is "+obj.s;
					JOptionPane.showMessageDialog(null,pp,"message",JOptionPane.INFORMATION_MESSAGE);
					//tArea.setText("no of blocks in file "+obj.filename+" is "+obj.s);
				}
			}
			else if(obj.no==1)
			{
				JOptionPane.showMessageDialog(null, "invalid filename ", "error", JOptionPane.ERROR_MESSAGE);
			}
			else if(obj.no==2)
			{
				JOptionPane.showMessageDialog(null, "invalid command ", "error", JOptionPane.ERROR_MESSAGE);
			}
			else if(obj.no==3)
			{
				JOptionPane.showMessageDialog(null, "invalid block no", "error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	

}
