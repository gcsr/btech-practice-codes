import gui.tablecellrenderer;

import javax.swing.JFrame;


public class tablecellrenderertest
{
	public static void main(String[] gcs)
	{
		tablecellrenderer table=new tablecellrenderer();
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setSize(400,400);
		table.setVisible(true);
	}
}