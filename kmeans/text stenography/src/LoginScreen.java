import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JFrame{
	
	JLabel userLabel=null;
	JLabel passLabel=null;
	JTextField userText=null;
	JPasswordField passText=null;
	JButton submit=null;
	
	//JPasswordField passText=null;
	
	
	
	
	public LoginScreen(){
		super("Login");
		setLayout(null);
		getContentPane().setBackground(Color.BLUE);
		setResizable(false);
		userLabel=new JLabel("UserName");
		passLabel=new JLabel("Password");
		
		userText=new JTextField("username");
		passText=new JPasswordField("password");
		
		
		//userLabel.setBackground(Color.YELLOW);
		userLabel.setBounds(100,160,100,30);
		//userLabel.setOpaque(true);
		add(userLabel);
		
		
		userText.setBackground(Color.YELLOW);
		userText.setBounds(220,160,100,30);
		userText.setOpaque(true);
		add(userText);
		
		
		passLabel.setBackground(Color.YELLOW);		
		passLabel.setBounds(100,200,100,30);
	//	passLabel.setOpaque(true);
		add(passLabel);
		
		passText.setBackground(Color.YELLOW);		
		passText.setBounds(220,200,100,30);
		passText.setOpaque(true);
		add(passText);
		
		submit=new JButton("submit");
		submit.setBackground(Color.RED);
		submit.setOpaque(true);
		submit.setBounds(150,300,100, 30);
		add(submit);
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				if(!userText.getText().equals("admin")){
					JOptionPane.showMessageDialog(LoginScreen.this,"Username is wrong");
					return;
				}
				
				if(!passText.getText().equals("admin")){
					JOptionPane.showMessageDialog(LoginScreen.this,"Password is wrong");
					return;
				}
				
				
				JFrame irf=new IndianRootFrame();
				irf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				irf.setSize(800,800);
				irf.setVisible(true);
				
				
			}
		});
		
		
	}
	
	
	

}
