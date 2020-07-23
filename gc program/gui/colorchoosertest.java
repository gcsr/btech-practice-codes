import gui.colorchooserframe;

import javax.swing.JFrame;

public class colorchoosertest
{
	public static void main(String[] gcs)
	{
		colorchooserframe frame=new colorchooserframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}