
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class AlienTiles extends JFrame implements WindowListener
{
	int fps;
	JTextField box,time;
	AlienTilesPanel ap;;
	
	public static void main(String[] gcs)
	{
		int fps=50;
		long period=(long)1000.0/fps;
		
	   AlienTiles app=new AlienTiles(period*1000000);
	   app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   app.setSize(1200,800);
	   app.setVisible(true);
	   
		System.out.println("fps "+fps+"  period  "+period);
		
	}
	public AlienTiles(long period)
	{
		
		super("AlienTiles");
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		add(panel,BorderLayout.NORTH);
		addWindowListener(this);
        ap=new AlienTilesPanel(this,period);
        add(ap);
		
	}
	
	public void windowActivated(WindowEvent e)
	{
		ap.resumeGame();
	}
	public void windowDeactivated(WindowEvent e)
	{
		ap.pauseGame();
	}
	public void windowIconified(WindowEvent e)
	{
		ap.pauseGame();
	}
	public void windowDeiconified(WindowEvent e)
	{
	   ap.resumeGame();
	}
	public void windowClosing(WindowEvent e)
	{
		ap.stopGame();
	}
	public void windowClosed(WindowEvent e)
	{
		
		ap.stopGame();
	}
	public void windowOpened(WindowEvent e)
	{
		ap.startgame();
	}
	
	
}