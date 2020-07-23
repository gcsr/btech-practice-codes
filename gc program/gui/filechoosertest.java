import gui.filechooserframe;

import javax.swing.JFrame;



public class filechoosertest
{
	public static void main(String[] gcs)
	{
		filechooserframe frame=new filechooserframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}