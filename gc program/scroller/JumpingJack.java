import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class JumpingJack extends JFrame implements WindowListener
{
	int fps;
	JTextField box,time;
	JackPanel jp;
	
	public static void main(String[] gcs)
	{
		int fps=40;
		long period=(long)1000.0/fps;
		
	   JumpingJack app=new JumpingJack(period*1000000);
	   app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   app.setSize(1200,800);
	   app.setVisible(true);
	   
		System.out.println("fps "+fps+"  period  "+period);
		
	}
	public JumpingJack(long period)
	{
		
		super("JumpingJack");
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		add(panel,BorderLayout.NORTH);
	
		addWindowListener(this);
        jp=new JackPanel(this,period);
        add(jp);
		
	}
	public void setboxnumber(int no)
	{
		box.setText("boxes used:"+no);
	}
	
	public void windowActivated(WindowEvent e)
	{
		jp.resumeGame();
	}
	public void windowDeactivated(WindowEvent e)
	{
		jp.pauseGame();
	}
	public void windowIconified(WindowEvent e)
	{
		jp.pauseGame();
	}
	public void windowDeiconified(WindowEvent e)
	{
	   jp.resumeGame();
	}
	public void windowClosing(WindowEvent e)
	{
		jp.stopGame();
	}
	public void windowClosed(WindowEvent e)
	{
		
		jp.stopGame();
	}
	public void windowOpened(WindowEvent e)
	{
		jp.startgame();
	}
	
	public void setTimeSpent(long t)
	{
		time.setText("timespent "+t+"secs");
	}
	
}