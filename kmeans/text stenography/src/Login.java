import javax.swing.JFrame;


public class Login {

	public static void main(String[] gcs){
		LoginScreen screen=new LoginScreen();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setLocation(500, 300);
		screen.setSize(500,500);
		//screen.setBackground(new Color(35,134,235));
		screen.setVisible(true);
	}
	
	
}