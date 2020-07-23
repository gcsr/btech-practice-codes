import gui.simpledialog;

import javax.swing.JFrame;

public class simpledialogtest
{
	public static void main(String[] gcs)
	{
		simpledialog frame=new simpledialog();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}