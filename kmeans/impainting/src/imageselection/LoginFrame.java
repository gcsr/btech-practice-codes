package imageselection;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;



//class for login frame
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	static int count=0;

	/**
	 * a method for starting login frame
	 */
	public static void main(String[] args) {
		
		if(count==0)
			count++;
		else
			return;
		
		//change the look and feel of frame
		 try {
	            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception fail) {
	        }
		 //threading
		EventQueue.invokeLater(new Runnable() {
			
			//opens a frame with sizes
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
					frame.setLocation(400, 200);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// creates label
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(93, 81, 71, 14);
		contentPane.add(lblUserName);
		
		// for username field
		textField = new JTextField();
		textField.setBounds(212, 78, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//for password label
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(93, 141, 46, 14);
		contentPane.add(lblPassword);
		
		//for password filed
		passwordField = new JPasswordField();
		passwordField.setBounds(212, 138, 86, 20);
		contentPane.add(passwordField);
		
		//for login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(248, 208, 89, 23);
		contentPane.add(btnLogin);
		
		//listener for login button
		btnLogin.addActionListener(new ActionListener(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 * 
			 * checks for username and password and displays tabular display
			 */
			public void actionPerformed(ActionEvent event){
				
				String userName=textField.getText();
				String password=passwordField.getText();		
				
				if((userName.equals("admin") && password.equals("admin"))){
					dispose();
					 try
					    {
					    Main g=new Main();
					    g.show();
					    }
					    catch(Exception e)
					    {}

				}
				else{
					JOptionPane.showMessageDialog(LoginFrame.this,"username or password wrong");
				}
				
			}
		});
	}
}
