import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class BugRunner extends JFrame implements WindowListener
{
	int fps;
	JTextField box,time;
	BugPanel bp;
	
	public static void main(String[] gcs)
	{
		int fps=80;
		long period=(long)1000.0/fps;
		
	   BugRunner app=new BugRunner(period*1000000);
	   app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   app.setSize(1200,800);
	   app.setVisible(true);
	   
		System.out.println("fps "+fps+"  period  "+period);
		
	}
	public BugRunner(long period)
	{
		
		super("warmchase");
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		add(panel,BorderLayout.NORTH);
		box=new JTextField("emra");
		time=new JTextField("kjdkjdkj");
		//panel.add(box);
		//panel.add(time);
		addWindowListener(this);
        bp=new BugPanel(this,period);
        add(bp);
		
	}
	public void setboxnumber(int no)
	{
		box.setText("boxes used:"+no);
	}
	
	public void windowActivated(WindowEvent e)
	{
		bp.resumeGame();
	}
	public void windowDeactivated(WindowEvent e)
	{
		bp.pauseGame();
	}
	public void windowIconified(WindowEvent e)
	{
		bp.pauseGame();
	}
	public void windowDeiconified(WindowEvent e)
	{
	   bp.resumeGame();
	}
	public void windowClosing(WindowEvent e)
	{
		bp.stopGame();
	}
	public void windowClosed(WindowEvent e)
	{
		
		bp.stopGame();
	}
	public void windowOpened(WindowEvent e)
	{
		bp.startgame();
	}
	
	public void setTimeSpent(long t)
	{
		time.setText("timespent "+t+"secs");
	}
	
}