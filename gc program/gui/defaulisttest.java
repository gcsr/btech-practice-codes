import gui.defaulist;
import javax.swing.JFrame;

public class defaulisttest
{
	public static void main(String[] gcs)
	{
		defaulist app=new defaulist();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,400);
		app.setVisible(true);
	}
}