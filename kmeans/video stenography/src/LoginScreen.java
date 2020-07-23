import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JFrame{
			
	MiddlePanel middlePanel;
	JPanel topPanel;
	RightPanel rightPanel;
	JTextField userField;
	JPasswordField passField;
	JButton submitButton;
	public LoginScreen(){
		super("login");
		topPanel=new JPanel();	
		
		middlePanel=new MiddlePanel();
		
		rightPanel=new RightPanel();	
		setLayout(new BorderLayout());
		add(topPanel,BorderLayout.NORTH);				
		//middlePanel.add(label);
		add(middlePanel,BorderLayout.CENTER);
		add(rightPanel,BorderLayout.EAST);
	}
	
	class MiddlePanel extends JPanel{
		
		BufferedImage[] images;
		
		public MiddlePanel(){
			String dir="D:/ca";
			File f=new File(dir);
			String[] names=f.list();
			
			images=new BufferedImage[names.length];			
			
			try{
				for(int i=0;i<images.length;i++){
					    images[i] = ImageIO.read(new File(dir+"/"+names[i]));
				}
				
			}catch(Exception ex){
					
			}
		}
		public void paintComponent(Graphics gc){
			super.paintComponent(gc);
			
			//System.out.println("on paint");
			for(int counter=0;counter<images.length;counter++)
			gc.drawImage(images[counter],((counter%4)*160),((counter/4)*80),160,80,this);
			
		}
	}
	class RightPanel extends JPanel{
		public RightPanel(){
			//setBackground(Color.BLUE);
			setLayout(new GridLayout(10,2));
			JLabel label=new JLabel("             ");
			JLabel label1=new JLabel("username");
			JLabel label2=new JLabel("password");
			userField=new JTextField("username",15);
			passField=new JPasswordField("password",15);
			
			add(label);
			add(label);
			add(label);
			add(label);
			add(label);
			add(label);
			add(label);
			add(label);
			
			add(label1);			
			add(userField);
			add(label2);
			add(passField);
			
			add(label);
			add(label);
			add(label);
			add(label);
			add(label);
			add(label);
			add(label);
			//add(label);
			submitButton=new JButton("Login");
			add(submitButton);
			submitButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					if(!userField.getText().equals("ksrm")){
						JOptionPane.showMessageDialog(LoginScreen.this,"Username is wrong");
						return;
					}
					
					if(!passField.getText().equals("ksrm")){
						JOptionPane.showMessageDialog(LoginScreen.this,"Password is wrong");
						return;
					}
					
					
					JFrame irf=new MainFrame();
					irf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
					irf.setVisible(true);
					
					
				}
			});
			
			
			
		}
	}
	
	

}
