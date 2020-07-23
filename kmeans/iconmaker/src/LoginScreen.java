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
		String tt="<html>It is evident that certain areas in an image<br>"
				+ " are more efficient for hiding data than the <br>"
				+ "other parts of the image. These are called Regions of<br>"
				+ " Interest or ROIs. Edge areas in an image are one of <br>"
				+ "the ROIs that can be used for steganography.<br>"
				+ " Here we propose an edge adaptive image <br>"
				+ "steganography mechanism which combines the benefits <br> "
				+ "of matrix encoding and LSBM to embed data and also <br>"
				+ "uses a chaotic mapping scheme to provide <br>"
				+ "enhanced security to the payload.</html>";
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
			
			JLabel label=new JLabel("Edge Steganography");
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
					
					
					JFrame irf=new CannyEdgeFrame();
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
