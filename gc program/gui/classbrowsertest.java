import gui.classbrowser;

import javax.swing.JFrame;


public class classbrowsertest
{
	public static void main(String[] gcs)
	{
		classbrowser frame=new classbrowser();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}