import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.Naming;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class RmiClient extends JFrame implements WindowListener{
	
	boolean loginScreen=true;	
	JTextField user,ip;
	JTextArea group;
	JTextField message;
	JButton loginButton=null,logoutButton=null,sendButton=null;
	ComInterface comObject=null;
	String name=null,ipAddress=null;
	
	Timer timer=null;
	
	private JPanel topPanel,middlePanel,bottomPanel;
	JPanel mainPanel;
	public RmiClient(){
		super("Rmi Client");
		
		//mainPanel=new MainPanel();
		//setLayout(new BorderLayout());
		//add(mainPanel,BorderLayout.CENTER);*/
		addWindowListener(this);
		topPanel=new TopPanel();
		//topPanel.setBorder(arg0);
		middlePanel=new MiddlePanel();
		bottomPanel=new BottomPanel();
		
		//add(topPanel,BorderLayout.NORTH);
		//add(middlePanel,BorderLayout.CENTER);
		//add(bottomPanel,BorderLayout.SOUTH);
		
		Box vertical1 = Box.createVerticalBox();
		//vertical1.add( Box.createVerticalStrut( 100 ) );			
		vertical1.add(topPanel);
		vertical1.add( Box.createVerticalStrut( 25) );
		vertical1.add(middlePanel);
		vertical1.add( Box.createVerticalStrut( 25) );
		vertical1.add(bottomPanel);
		add(vertical1);
			
	}
	
	
	class MainPanel extends JPanel{
		MainPanel(){
			
		
			topPanel=new TopPanel();
			//topPanel.setBorder(new TitledBorder(border,"UserName"));
			middlePanel=new MiddlePanel();
			//middlePanel.setBorder(new TitledBorder(border,"Enter User Message"));
			bottomPanel=new BottomPanel();
		
			
			add(topPanel);
			add(middlePanel);
			add(bottomPanel);
			/*Box vertical1 = Box.createVerticalBox();
			vertical1.add( Box.createVerticalStrut( 100 ) );			
			vertical1.add(topPanel);
			vertical1.add( Box.createVerticalStrut( 25) );
			vertical1.add(middlePanel);
			vertical1.add( Box.createVerticalStrut( 25) );
			vertical1.add(bottomPanel);
				*/		
		}
	}
	
	class TopPanel extends JPanel{
		TopPanel(){
			
			Border border=BorderFactory.createLineBorder(Color.BLACK);
			this.setBorder(new TitledBorder(border,"User Name"));
			user=new JTextField("",20);
			//ip=new JTextField("",20);
			Box vertical1 = Box.createHorizontalBox();
			vertical1.add( Box.createHorizontalStrut( 20 ) );
			
			vertical1.add(user);
			vertical1.add( Box.createHorizontalStrut( 25) );
			
			loginButton=new JButton("Enter");
			loginButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					createCommunication();
					try{						
						String f="Hi I\'m in"+"\n"+"\t\t"+name+"\n";						
						comObject.putMessage(f);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
				}
				
			});
			vertical1.add(loginButton);
			add(vertical1);
		}
	}
	
	class MiddlePanel extends JPanel{
		MiddlePanel(){
			Border border=BorderFactory.createLineBorder(Color.BLACK);
			this.setBorder(new TitledBorder(border,"Text Messages"));
			Box vertical1 = Box.createHorizontalBox();
			vertical1.add( Box.createHorizontalStrut( 30 ) );
			
			message=new JTextField("message",30);
			vertical1.add(message);
			
			sendButton=new JButton("send");
			vertical1.add( Box.createHorizontalStrut( 20 ) );
			vertical1.add(sendButton);
			
			sendButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					try{						
						String f=message.getText()+"\n"+"\t\t"+name+"\n";						
						comObject.putMessage(f);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
				}
			});
			add(vertical1);
			
		}
	}
	
	class BottomPanel extends JPanel{
		BottomPanel(){
			Border border=BorderFactory.createLineBorder(Color.BLACK);
			this.setBorder(new TitledBorder(border,"Group Messages"));
			Box vertical1 = Box.createHorizontalBox();
			vertical1.add( Box.createHorizontalStrut( 20 ) );
						
			group=new JTextArea("Group",10,40);
			JScrollPane sPane=new JScrollPane(group);
			vertical1.add(sPane);
			add(vertical1);
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
			ipAddress="localhost";//ip.getText();
			/*if(ipAddress==null || ipAddress.equals("")){
				JOptionPane.showMessageDialog(this,"Address is Null");
				return;
			}*/
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
		//refreshPage();
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
