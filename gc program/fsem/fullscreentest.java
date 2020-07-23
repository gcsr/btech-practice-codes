
import fsem.fullscreen;

import javax.swing.JFrame;






public class fullscreentest
{
	public static void main(String[] gcs)
	{
		fullscreen app=new fullscreen();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(300,300);
		app.setVisible(true);
	}
}