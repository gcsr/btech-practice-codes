import gui.gridbagframe;

import javax.swing.JFrame;

public class gridbagtest
{
	public static void main(String[] gcs)
	{
		gridbagframe frame=new gridbagframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}