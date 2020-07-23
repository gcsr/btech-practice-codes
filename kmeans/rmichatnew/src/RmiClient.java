import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.Naming;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class RmiClient extends JFrame implements WindowListener{
	
	boolean loginScreen=true;	
	JTextField user,ip;
	JTextArea group;
	JTextField message;
	JButton loginButton=null,logoutButton=null;
	ComInterface comObject=null;
	String name=null,ipAddress=null;
	
	Timer timer=null;
	public RmiClient(){		
		super("Client Screen");
		setLayout(new BorderLayout());
		addWindowListener(this);
		refreshPage();
	}	
	
	class LeftPanel extends JPanel{
		public LeftPanel(){
			setLayout(new BorderLayout());
			logoutButton=new JButton("Logout");
			add(logoutButton,BorderLayout.NORTH);
			logoutButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					try{
						String f="It's me Signing off"+"\n"+"\t\t"+name+"\n";						
						comObject.putMessage(f);
						timer.stop();
						timer=null;
						comObject=null;
						loginScreen=true;						
						refreshPage();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			});
			group=new JTextArea("Group");
			JScrollPane sPane=new JScrollPane(group);
			add(sPane,BorderLayout.CENTER);
			message=new JTextField("message");
			add(message,BorderLayout.SOUTH);
			
			message.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					try{						
						String f=message.getText()+"\n"+"\t\t"+name+"\n";						
						comObject.putMessage(f);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
				}
			});
			
			
		}
	}
	
	class RightPanel extends JPanel{
		public RightPanel(){
			user=new JTextField("",20);
			ip=new JTextField("",20);
			Box vertical1 = Box.createVerticalBox();
			vertical1.add( Box.createVerticalStrut( 100 ) );
			vertical1.add(new JLabel("User Name"));
			//vertical1.add( Box.createVerticalStrut( 25) );
			vertical1.add(user);
			vertical1.add( Box.createVerticalStrut( 25) );
			vertical1.add(new JLabel("Server Ip"));			
			vertical1.add(ip);
			vertical1.add( Box.createVerticalStrut( 25) );
			loginButton=new JButton("Login");
			loginButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					createCommunication();
				}
			});
			vertical1.add(loginButton);
			add(vertical1);
		}
	}
	
	
	
	class TopRightPanel extends JPanel{
		public TopRightPanel(){
			setLayout(new BorderLayout());
			String tt="<html><center><h1>Help</h1></center><ul><li>Login<br/><ul><li>Server Name For Connecting</li><li>User Name For Representing User</li></ul></li><li>Chatting<br/><ul><li>Type Message at the bottom</li><li>Message are updated at 1/5th of a second</li></ul></li><li>Logout</br></li></ul></html>";
			JLabel label=new JLabel(tt);
			add(label,BorderLayout.NORTH);
		}
	}
	
	private void createCommunication(){		
		if(comObject!=null)
			return;
		try{
			name=user.getText();
			if(name==null || name.equals("")){
				JOptionPane.showMessageDialog(this,"Name is Null");
				return;
			}
			ipAddress=ip.getText();
			if(ipAddress==null || ipAddress.equals("")){
				JOptionPane.showMessageDialog(this,"Address is Null");
				return;
			}
			Object rem=Naming.lookup("rmi://"+ipAddress+"/group");		
			comObject=(ComInterface)rem;
			loginScreen=false;
		
		}catch(Exception ex){
			comObject=null;
			JOptionPane.showMessageDialog(this,"Please Enter correct Ip Address");			
			ex.printStackTrace();
		}
		
		timer=new Timer(200,new Messenger());
		timer.start();
		refreshPage();
	}
	
	public void refreshPage(){
		System.out.println("creating object");	
		getContentPane().removeAll();
		add(new TopRightPanel(),BorderLayout.EAST);
		
		if(loginScreen){
			add(new RightPanel(),BorderLayout.CENTER);
			System.out.println("login");
		}
		
		else{
			add(new LeftPanel(),BorderLayout.CENTER);
			System.out.println("message");
		}		
		
		show();
	}
	
	class Messenger implements ActionListener{
	  	public void actionPerformed(ActionEvent event){
	  		try{
	  			group.setText(comObject.getMessages());
	  		}catch(Exception ex){
	  			ex.printStackTrace();
	  		}
	  	}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try{
			String f="It's me Signing off"+"\n"+"\t\t"+name+"\n";
			
			if(timer!=null){
				timer.stop();
				timer=null;
			}
			if(comObject!=null){
				comObject.putMessage(f);
				comObject=null;
			}	
			loginScreen=true;						
			//refreshPage();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		try{
			String f="It's me Signing off"+"\n"+"\t\t"+name+"\n";
			
			if(timer!=null){
				timer.stop();
				timer=null;
			}
			if(comObject!=null){
				comObject.putMessage(f);
				comObject=null;
			}	
			loginScreen=true;						
			//refreshPage();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
