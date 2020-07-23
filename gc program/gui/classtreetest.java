import gui.classtree;

import javax.swing.JFrame;

public class classtreetest 
{
	public static void main(String[] gcs)
	{
		classtree app=new classtree();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
		app.setSize(800,400);
	}
}