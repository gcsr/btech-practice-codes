import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		//getContentPane().setBackground(Color.BLUE);
		//setResizable(false);
		userLabel=new JLabel("UserName");
		passLabel=new JLabel("Password");
		
		userText=new JTextField("username");
		passText=new JPasswordField("password");
		
		
		//userLabel.setBackground(Color.YELLOW);
		userLabel.setBounds(100,160,100,30);
		//userLabel.setOpaque(true);
		add(userLabel);
		
		
		//userText.setBackground(Color.YELLOW);
		userText.setBounds(220,160,100,30);
		//userText.setOpaque(true);
		add(userText);
		
		
		//passLabel.setBackground(Color.YELLOW);		
		passLabel.setBounds(100,200,100,30);
		//passLabel.setOpaque(true);
		add(passLabel);
		
		//passText.setBackground(Color.YELLOW);
		
		passText.setBounds(220,200,100,30);
		//passText.setOpaque(true);
		add(passText);
		
		submit=new JButton("submit");
		
		submit.setBounds(150,300,70, 30);
		add(submit);
		
		
	}
	
	
	

}
