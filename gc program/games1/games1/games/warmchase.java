
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class warmchase extends JFrame implements WindowListener
{
	GraphicsDevice gd;
	int fps;
	static long period;
	JTextField box,time;
	wormpanel wp;
	private BufferStrategy bufferstrategy;
	
	public static void main(String[] gcs)
	{
		int fps=80;
		if(gcs.length!=0)
		fps=Integer.parseInt(gcs[0]);
		
		period=(long)1000.0/fps;
		
	    warmchase app=new warmchase(period);
	   app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   app.setSize(1200,800);
	   app.setVisible(true);
	   
		System.out.println("fps "+fps+"  period  "+period);
		
	}
	public warmchase(long period)
	{
		
		super("warmchase");
		initfullscreen();
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
	private void initfullscreen()
	{
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		System.out.println(ge);
		gd=ge.getDefaultScreenDevice();
		
		System.out.println(gd);
		setUndecorated(true);		
		setIgnoreRepaint(true);
		setResizable(true);
		
		
		if(!gd.isFullScreenSupported())
		{
					
		    System.out.println("fullscreen exclusive mode not supported");
		    System.exit(0);
		}
		gd.setFullScreenWindow(this);
		showcurrentmode();
		//setDisplayMode(800,600,8);
		//setDisplayMode(1280,1024,32);
		
	
		
		//setbufferstrategy();
		
		
	}
	private void setBufferStrategy()
	{
		try
		{
			EventQueue.invokeAndWait(new Runnable()
			{
				public void run()
				{
					createBufferStrategy(2);
				}
			});
		}
		catch(Exception e)
		{
			System.out.println("errorwhilecreating bufferstrategy");
			System.exit(0);
		}
		try
		{
			Thread.sleep(500);
		}
		catch(InterruptedException e)
		{
			bufferstrategy=getBufferStrategy();
		}
		
	}
	private void showcurrentmode()
	{
		DisplayMode dm=gd.getDisplayMode();
		System.out.println("current displaymode:("+dm.getWidth()+","+dm.getHeight()+
		","+dm.getBitDepth()+","+dm.getRefreshRate()+")");
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