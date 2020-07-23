import java.awt.Dimension;

import javax.swing.JFrame;


public class MainClass {
	public static void main(String[] gcs){
		GUIClass app=new GUIClass();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(new Dimension(500,500));
		app.setResizable(false);
		app.setVisible(true);
	}
}
