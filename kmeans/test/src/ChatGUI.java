import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.Naming;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class ChatGUI extends JFrame implements WindowListener{
	
	ChatInterface chat=null;
	GridBagFrame panel2;
	LoginPanel panel1;
	JLabel IPLabel=new JLabel("IP Address");
	JLabel userLabel=new JLabel("User Name");
	JButton startButton=new JButton("Start Time");
	JButton endButton=new JButton("End Time");
	JLabel startLabelInfo=new JLabel("Start Time Info");
	JLabel endLabelInfo=new JLabel("End Time Info");
	
	
	JTextField userField,IPField;
	
	public static void main(String[] gcs){
		ChatGUI app=new ChatGUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,400);
		app.setVisible(true);
	}
	
	public ChatGUI(){
		
		super("Chat Client");
		JTabbedPane tabpane=new JTabbedPane();		
		
		panel1=new LoginPanel();
		panel1.setBackground(Color.blue);
		panel2=new GridBagFrame();
		
		WrapUpPanel panel3=new WrapUpPanel();
		panel3.setBackground(Color.blue);
		
		tabpane.addTab("Log In",null,panel1,"first panel");
		tabpane.addTab("Chat",null,panel2,"second panel");
		tabpane.addTab("WrapUp",null,panel3,"third panel");
		add(tabpane);
		addWindowListener(this);
	}
	
	class LoginPanel extends JPanel{
		GridBagLayout layout;
		GridBagConstraints constraints;		
		JButton button;
		
		public LoginPanel(){			
			layout = new GridBagLayout();			
			setLayout( layout ); // set frame layout                          
			constraints = new GridBagConstraints(); // instantiate constraints
			
			Insets insets=new Insets(5,5,5,5);
			constraints.insets=insets;
			
			userField=new JTextField("user name",15);
			IPField=new JTextField("IP Address",10);
			
			//Border border=BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.cyan,Color.GRAY,Color.black,Color.GRAY );		
	        constraints.fill = GridBagConstraints.BOTH;
	        constraints.weightx=0;	        
	        constraints.weighty=0;
	        
	        constraints.gridx = 0; // set gridx                           
	        constraints.gridy = 0; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight	        
	        layout.setConstraints(IPLabel, constraints ); // set constraints	        
	        add(IPLabel);	        
	        
	        constraints.gridx = 0; // set gridx                           
	        constraints.gridy = 1; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(userLabel, constraints ); // set constraints	        
	        add(userLabel);			
	        
	        constraints.gridx = 1; // set gridx                           
	        constraints.gridy = 0; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(IPField, constraints ); // set constraints	        
	        add(IPField);			
	        
	        constraints.gridx = 1; // set gridx                           
	        constraints.gridy = 1; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(userField, constraints ); // set constraints	        
	        add(userField);			
	        
	        button=new JButton("start");
	        constraints.gridx = 0; // set gridx                           
	        constraints.gridy = 2; // set gridy                              
	        constraints.gridwidth = 2; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(button, constraints ); // set constraints	        
	        add(button);
	        
	        button.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent ev){
	        		createChatObject();
	        	}
	        });
		}
	}
	
	private void createChatObject(){
		if(chat!=null)
			return;
		try{
			String userName=userField.getText();
			if(userName==null){
				JOptionPane.showMessageDialog(this,"Please Enter some user name");
				return;
			}
			String address=IPField.getText();
			Object remChat=Naming.lookup("rmi://"+address+"/chatserver");		
			chat=(ChatInterface)remChat;
			panel2.setUserName(userName);
			panel2.setChatter(chat);
			panel2.addListener();
			panel2.sendLoginMessage();
		
		}catch(Exception ex){
			chat=null;
			JOptionPane.showMessageDialog(this,"Please Enter correct username and Ip Address");			
			ex.printStackTrace();
		}
			
	}
	
	class WrapUpPanel extends JPanel{
		GridBagLayout layout;
		GridBagConstraints constraints;		
		JButton button;
		
		public WrapUpPanel(){
			layout = new GridBagLayout();			
			setLayout( layout ); // set frame layout                          
			constraints = new GridBagConstraints(); // instantiate constraints
			
			Insets insets=new Insets(5,5,5,5);
			constraints.insets=insets;
		
			
			//Border border=BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.cyan,Color.GRAY,Color.black,Color.GRAY );		
	        constraints.fill = GridBagConstraints.BOTH;
	        constraints.weightx=0;	        
	        constraints.weighty=0;
	        
	        constraints.gridx = 0; // set gridx                           
	        constraints.gridy = 0; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight	        
	        layout.setConstraints(startButton, constraints ); // set constraints	        
	        add(startButton);
	        
	       
	        constraints.gridx = 0; // set gridx                           
	        constraints.gridy = 1; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(endButton, constraints ); // set constraints	        
	        add(endButton);
	        
	        
	        
	        constraints.gridx = 1; // set gridx                           
	        constraints.gridy = 0; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(startLabelInfo, constraints ); // set constraints	        
	        add(startLabelInfo);			
	        
	        constraints.gridx = 1; // set gridx                           
	        constraints.gridy = 1; // set gridy                              
	        constraints.gridwidth = 1; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(endLabelInfo, constraints ); // set constraints	        
	        add(endLabelInfo);			
	        
	        button=new JButton("logout");
	        constraints.gridx = 0; // set gridx                           
	        constraints.gridy = 2; // set gridy                              
	        constraints.gridwidth = 2; // set gridwidth                    
	        constraints.gridheight = 1; // set gridheight                 
	        layout.setConstraints(button, constraints ); // set constraints	        
	        add(button);
	        
	        startButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent ev){
	        		if(chat!=null)
	        		startLabelInfo.setText(panel2.getStartTime());
	        	}
	        });
	        
	        endButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent ev){
	        		if(chat!=null)
	        		endLabelInfo.setText(panel2.getEndTime());
	        	}
	        });
	        
	        button.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent ev){
	        		clearChatObject();
	        	}
	        });
	
		}
	}
	
	private void clearChatObject(){
		if(chat!=null){
		panel2.sendLogoutMessage();
		System.exit(1);
		}
		else{
			JOptionPane.showMessageDialog(this,"You are not logged in");
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window closing");
		clearChatObject();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window closing");
		clearChatObject();
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
