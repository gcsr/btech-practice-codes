import gui.tablesort;

import javax.swing.JFrame;


public class tablesorttest
{
	public static void main(String[] gcs)
	{
		tablesort table=new tablesort();
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setSize(400,400);
		table.setVisible(true);
	}
}