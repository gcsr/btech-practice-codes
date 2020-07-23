import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JFrame{
			
	TopPanel topPanel;
	JPanel bottomPanel;
	JPanel middlePanel;
	JTextField userField;
	JPasswordField passField;
	JButton submitButton;
	public LoginScreen(){
		super("login");
		topPanel=new TopPanel();
		bottomPanel=new JPanel();
		bottomPanel.setBackground(new Color(106,173,227));
		middlePanel=new JPanel();
		middlePanel.setBackground(new Color(106,173,227));
		setLayout(new BorderLayout());
		add(topPanel,BorderLayout.NORTH);
		String tt="<html><h2>Welcome to Handwritten Signature Recognition</h2></html>";
		JLabel label=new JLabel(tt);
		middlePanel.add(label);
		add(middlePanel,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
	}
	
	JPanel leftPanel=new JPanel();
	JPanel rightPanel=new JPanel();
	
	class TopPanel extends JPanel{
		public TopPanel(){
			setBackground(Color.BLUE);
			setLayout(new GridLayout(1,2));
			
			JLabel label=new JLabel("Handwritten Signature Recognition");
			leftPanel.add(label);
			userField=new JTextField("username",15);
			passField=new JPasswordField("password",15);
			
			submitButton=new JButton("Login");
			
			submitButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					if(!userField.getText().equals("admin")){
						JOptionPane.showMessageDialog(LoginScreen.this,"Username is wrong");
						return;
					}
					
					if(!passField.getText().equals("admin")){
						JOptionPane.showMessageDialog(LoginScreen.this,"Password is wrong");
						return;
					}
					
					
					JFrame irf=new Kmeans();
					irf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					irf.setSize(800,800);
					irf.setVisible(true);
					
					
				}
			});
			
			rightPanel.add(userField);
			rightPanel.add(passField);
			rightPanel.add(submitButton);
			
			add(leftPanel);
			add(rightPanel);
		}
	}

}
