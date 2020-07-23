import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.InetAddress;

public class MailTestFrame extends JFrame
{
	JTextField from;
	JTextField to;
	JTextField smtpServer;
	JTextArea message;
	JTextArea comm;
	Scanner in;
	PrintWriter out;
	
	public MailTestFrame()
	{
		setTitle("MailTest");
		
		setLayout(new GridBagLayout());
		add(new JLabel("From:    "),new gbc(0,0).setFill(gbc.HORIZONTAL));
		
		from=new JTextField(20);
		add(from,new gbc(1,0).setFill((gbc.HORIZONTAL)).setWeight(100,0));
		
		add(new JLabel("To:       "),new gbc(0,1).setFill(gbc.HORIZONTAL));
		
		to=new JTextField(20);
		add(to,new gbc(1,1).setFill((gbc.HORIZONTAL)).setWeight(100,0));
		
		add(new JLabel("SMTP server:    "),new gbc(0,2).setFill(gbc.HORIZONTAL));
		
		smtpServer=new JTextField(20);
		add(smtpServer,new gbc(1,2).setFill((gbc.HORIZONTAL)).setWeight(100,0));
		
		message=new JTextArea();
		add(new JScrollPane(message),new gbc(0,3,2,1).setFill(gbc.BOTH).setWeight(100,100));
		
		comm=new JTextArea();
		add(new JScrollPane(comm),new gbc(0,4,2,1).setFill(gbc.BOTH).setWeight(100,100));
		
		JPanel panel=new JPanel();
		add(panel,new gbc(0,5,2,1));
		
		
		JButton button=new JButton("send");
		
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						comm.setText("");
						sendMail();
					}
				}).start();
			}
		});
		panel.add(button);
		
	}	
		
		private void sendMail()
		{
			try
			{
				Socket s=new Socket(smtpServer.getText(),25);
				InputStream inStream=s.getInputStream();
				OutputStream outStream=s.getOutputStream();
				
				in=new Scanner(inStream);
				out=new PrintWriter(outStream,true);
				
				String hostname=InetAddress.getLocalHost().getHostName();
				
				
				
				
				
				
			}
			catch(IOException e)
			{
				comm.append("Error:");
			}
			
		}
		
		private void send(String s)throws IOException
		{
			comm.append(s);
			comm.append("\n");
			
			out.print(s.replaceAll("\n","\r\n"));
			out.print("\r");
			out.flush();
		}
		
		private void receive()throws IOException
		{
			if(in.hasNextLine())
			{
				String line=in.nextLine();
				comm.append(line);
				comm.append("\n");
			}
		}
		
		
		
}