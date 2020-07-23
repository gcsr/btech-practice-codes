import gui.planettable;

import javax.swing.JFrame;


public class planettabletest
{
	public static void main(String[] gcs)
	{
		planettable table=new planettable();
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setSize(400,400);
		table.setVisible(true);
	}
}