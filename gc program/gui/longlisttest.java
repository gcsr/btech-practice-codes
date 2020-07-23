import gui.longlist;
import javax.swing.JFrame;

public class longlisttest
{
	public static void main(String[] gcs)
	{
		longlist app=new longlist();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,400);
		app.setVisible(true);
	}
}