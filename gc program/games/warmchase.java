
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class warmchase extends JFrame implements WindowListener
{
	int fps;
	JTextField box,time;
	wormpanel wp;
	
	public static void main(String[] gcs)
	{
		int fps=50;
		long period=(long)1000.0/fps;
		
	   warmchase app=new warmchase(period*1000000);
	   app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   app.setSize(1200,800);
	   app.setVisible(true);
	   
		System.out.println("fps "+fps+"  period  "+period);
		
	}
	public warmchase(long period)
	{
		
		super("warmchase");
		setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		add(panel,BorderLayout.NORTH);
		box=new JTextField("emra");
		time=new JTextField("kjdkjdkj");
		panel.add(box);
		panel.add(time);
		addWindowListener(this);
        wp=new wormpanel(this,period);
        add(wp);
		
	}
	public void setboxnumber(int no)
	{
		box.setText("boxes used:"+no);
	}
	
	public void windowActivated(WindowEvent e)
	{
		wp.resumeGame();
	}
	public void windowDeactivated(WindowEvent e)
	{
		wp.pauseGame();
	}
	public void windowIconified(WindowEvent e)
	{
		wp.pauseGame();
	}
	public void windowDeiconified(WindowEvent e)
	{
	   wp.resumeGame();
	}
	public void windowClosing(WindowEvent e)
	{
		wp.stopGame();
	}
	public void windowClosed(WindowEvent e)
	{
		
		wp.stopGame();
	}
	public void windowOpened(WindowEvent e)
	{
		wp.startgame();
	}
	
	public void setTimeSpent(long t)
	{
		time.setText("timespent "+t+"secs");
	}
	
}