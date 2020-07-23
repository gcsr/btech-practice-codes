import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class MainGUI extends JFrame{
	
	JButton loginButton;
	JLabel userLabel,passLabel;
	JTextField userField;;
	JPasswordField passField;
	
	
	
	public MainGUI(){
		super("Power allocation prediction");
		setLayout(new BorderLayout());
		add(new LoginPanel(),BorderLayout.NORTH);
		add(new ImagePanel(),BorderLayout.CENTER);
	}
	
	private class LoginPanel extends JPanel{
		LoginPanel(){
			loginButton=new JButton("login");
			userLabel=new JLabel("Username: ");
			passLabel=new JLabel("Password: ");
			userField=new JTextField("username");
			passField=new JPasswordField("password");
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.add(Box.createHorizontalStrut(30));
			horizontalBox.add(userLabel);
			
			horizontalBox.add(Box.createHorizontalStrut(5));
			horizontalBox.add(userField);
			horizontalBox.add(Box.createHorizontalStrut(20));
			horizontalBox.add(passLabel);
			horizontalBox.add(Box.createHorizontalStrut(5));
			horizontalBox.add(passField);
			horizontalBox.add(Box.createHorizontalStrut(20));
			horizontalBox.add(loginButton);
			add(horizontalBox);
			loginButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					String username=userField.getText();
					String password=passField.getText();
					
					if(username.equals("kavya") && password.equals("kavya")){
						new WorkGUI();
						MainGUI.this.show(false);
					}
				}
			});
			
		}
	}
	
	private class ImagePanel extends JPanel{
		ImagePanel(){
			try{
				JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("back.PNG"))));
				add(background);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] gcs){
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	try{
		    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    	}catch(Exception ex){
		    		ex.printStackTrace();
		    	}
		    	MainGUI mainGUI=new MainGUI();
		    	mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	mainGUI.setSize(500, 400);
		    	mainGUI.setLocation(300, 150);
		    	mainGUI.setVisible(true);
		    	
		    }
		});
		
	}
	
	
}
