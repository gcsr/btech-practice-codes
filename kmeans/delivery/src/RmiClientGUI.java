import javax.swing.JFrame;


public class RmiClientGUI {
	
	public static void main(String[] gcs){
		RmiClient app=new RmiClient();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		app.setLocation(100, 300);
		app.setSize(600,400);
		app.setResizable(false);
		app.setVisible(true);
	}


}
