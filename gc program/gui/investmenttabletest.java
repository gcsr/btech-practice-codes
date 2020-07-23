import gui.investmenttable;

import javax.swing.JFrame;


public class investmenttabletest
{
	public static void main(String[] gcs)
	{
		investmenttable table=new investmenttable();
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setSize(400,400);
		table.setVisible(true);
	}
}