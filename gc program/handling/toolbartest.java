import gui.toolbarframe;

import javax.swing.JFrame;

public class toolbartest 
{
	public  static void main(String[] gcs)
	{
		toolbarframe frame=new toolbarframe();
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}