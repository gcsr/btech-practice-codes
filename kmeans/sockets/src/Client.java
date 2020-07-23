import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;



public class Client extends JFrame{
		
	private GridBagLayout layout; // layout of this frame
	private GridBagConstraints constraints; // constraints of this layout
	private JTextArea chatArea;
	JTextArea clientArea;
	
	
	public Client(){
		super("Multithreaded client");
		
		layout = new GridBagLayout();                                     
		setLayout( layout ); // set frame layout                          
		constraints = new GridBagConstraints(); // instantiate constraints	
		
		chatArea=new JTextArea("Chat Area",5,30);
		 JScrollPane sp = new JScrollPane(chatArea);
		clientArea=new JTextArea("Client Area");		
		Border border=BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.cyan,Color.GRAY,Color.black,Color.GRAY );		
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;        
        constraints.weighty=1;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 0; // set gridy                              
        constraints.gridwidth = 5; // set gridwidth                    
        constraints.gridheight = 5; // set gridheight                 
        layout.setConstraints(sp, constraints ); // set constraints       
        sp.setBorder(border);
        add(sp);
        
        JLabel label=new JLabel(" ");
        label.setBackground(Color.DARK_GRAY);
        constraints.weighty=0;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 5; // set gridy                              
        constraints.gridwidth = 5; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(label, constraints ); // set constraints        
		add(label);      
        
		constraints.fill = GridBagConstraints.BOTH;
	    constraints.weightx=0.5;        
	    constraints.weighty=1;	       
	    
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 7; // set gridy                              
        constraints.gridwidth = 5; // set gridwidth                    
        constraints.gridheight = 5; // set gridheight                 
         
		clientArea=new JTextArea("Client Area",5,30);		
		JScrollPane sp1 = new JScrollPane(clientArea);
        layout.setConstraints(clientArea, constraints ); // set constraints
        border=BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        layout.setConstraints(sp1, constraints ); // set constraints       
        sp1.setBorder(border);
        add(sp1);
      	
		
	
		
	}
	
	
	public static void main(String[] gcs){
		Client cl=new Client();
		cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cl.setVisible(true);
		cl.setSize(300,400);
	}
}
