import javax.swing.JFrame;
import javax.swing.JTextArea;
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


import javax.swing.SwingUtilities;

public class NetworkingGUIFrame extends JFrame {
	JTextArea tArea;
	final JTextField tField;
	ObjectInputStream input=null;
	ObjectOutputStream output=null;
	Socket client;
	String s="";
	
	public NetworkingGUIFrame()
	{
		setLayout(new BorderLayout());
		tArea=new JTextArea();
		add(tArea,BorderLayout.CENTER);
		//add(new JLabel("available files are"+s),BorderLayout.SOUTH);
		tField=new JTextField("enter ur command here");
		add(tField,BorderLayout.NORTH);
		setSize(400, 400);
		setVisible(true);
		//setDefaultCloseOPeration(JFrame.EXIT_ON_CLOSE);
		
		
		try
		{
			client=new Socket("127.0.0.1",5000);
			output=new ObjectOutputStream(client.getOutputStream());
			input=new ObjectInputStream(client.getInputStream());
			//s=(String)(input.readObject());
				
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
		
	
		tField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					
					public void run()
					{
						
						writeMessage();
						getResult();
					}
				});	
		
			}
		});
	
	}
	
	
	public void writeMessage()
	{
		try
		{
			System.out.println("writingMessage");
			output.writeObject(tField.getText());
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
			System.out.println("red message as "+obj.no);
			if(obj.no==10)
			{
				//this.show(false);
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
					tArea.setText(obj.s);
			
					System.out.println("set text");
				}
				else if((obj.filename!=null)&&(obj.s!=null))
				{
					tArea.setText("no of blocks in file "+obj.filename+" is "+obj.s);
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
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	

}