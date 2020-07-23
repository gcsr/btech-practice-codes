import gui.simplemodel;


import javax.swing.JFrame;


public class simplemodeltest
{
	public static void main(String[] gcs)
	{
		simplemodel app=new simplemodel();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,400);
		app.setVisible(true);
	}
}