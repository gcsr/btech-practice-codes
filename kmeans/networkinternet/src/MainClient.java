import javax.swing.JFrame;


public class MainClient {
		
	public static void main(String[] gcs){
		MainClientGui client=new MainClientGui();
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setSize(400,400);
		client.setVisible(true);
		client.setResizable(false);
		client.setLocation(450, 150);
		
	}

}
