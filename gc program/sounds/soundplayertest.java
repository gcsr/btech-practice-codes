import javax.swing.JFrame;

public class soundplayertest
{
	public static void main(String[] gcs)
	{
		soundplayer app=new soundplayer();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,400);
		app.setVisible(true);
	}
}