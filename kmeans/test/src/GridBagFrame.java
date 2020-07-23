 // Fig. 22.23: GridBagFrame2.java
 // Demonstrating GridBagLayout constants.
 import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class GridBagFrame extends JPanel{
	private GridBagLayout layout; // layout of this frame
	private GridBagConstraints constraints; // constraints of this layout
	private JTextArea chatArea;
	JTextField clientArea;
	ChatInterface chat;
	public GridBagFrame(){
		layout = new GridBagLayout();                                     
		setLayout( layout ); // set frame layout                          
		constraints = new GridBagConstraints(); // instantiate constraints	
		
		chatArea=new JTextArea("Chat Area",5,30);
		 JScrollPane sp = new JScrollPane(chatArea);
		clientArea=new JTextField("Type Here",30);		
		Border border=BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.cyan,Color.GRAY,Color.black,Color.GRAY );		
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;        
        constraints.weighty=1;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 0; // set gridy                              
        constraints.gridwidth = 5; // set gridwidth                    
        constraints.gridheight = 10; // set gridheight                 
        layout.setConstraints(sp, constraints ); // set constraints       
        sp.setBorder(border);
        add(sp);
        
        JLabel label=new JLabel(" ");
        label.setBackground(Color.DARK_GRAY);
        constraints.weighty=0;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 10; // set gridy                              
        constraints.gridwidth = 5; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(label, constraints ); // set constraints        
		add(label);      
        
        constraints.weighty=0;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 12; // set gridy                              
        constraints.gridwidth = 5; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(clientArea, constraints ); // set constraints
        border=BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        clientArea.setBorder(border);
		add(clientArea);	
		
		//setReminderBeep(3);
	}
	
	public void setChatter(ChatInterface chat){
		this.chat=chat;
	}
	
	public void sendLoginMessage(){
		try{
			String message=userName+": I'm into chat"+"\n";
			chat.addMessage(message);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void sendLogoutMessage(){
		
		try{
			String message=userName+": I'm out from chat"+"\n";
			chat.addMessage(message);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public String getStartTime(){
		if(chat==null)
			return "Error";
		else{
			Calendar cal = Calendar.getInstance();
			Date currentTime = cal.getTime();
			return ""+currentTime;
		}		
	}
	
	public String getEndTime(){
		if(chat==null)
			return "Error";
		else{
			String[] split=chatArea.getText().split("\n");
			for(int i=split.length-1;i>=0;i--){
				if(split[i].startsWith(userName)){
					return split[i+1];
				}
			}
		}
		
		return "Error";
	}
	
	public void addListener(){
		clientArea.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
					String message=userName+": "+clientArea.getText()+"\n";
					Calendar cal = Calendar.getInstance();					
					chat.addMessage(userName+": "+clientArea.getText()+"\n");
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
		});
		
		new Timer(1000,new Reaper()).start();
		
	}
	
	class Reaper implements ActionListener{
	  	//private static final long sessionTime=10000;	  	
	  	public Reaper(){
	  		
	  	}
	  	
	  	public void actionPerformed(ActionEvent event){
	  		try{
	  			chatArea.setText(chat.getMessages());
	  		}catch(Exception ex){
	  			ex.printStackTrace();
	  		}
	  	}

	}
	
	
	String userName;
	public void setUserName(String userName){
		this.userName=userName;
	}
	
} 





