import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AlienTilesPanel extends JPanel implements Runnable
{
	private long framesskipped=0L;
	private long totalframesskipped=0L;
	private double avgups,avgfps;;
	private int framecount=0;
	private int statscount=0;
	private long statsinterval=0L;
	
	Graphics dbg;
	Image dbimage;
	int pwidth=1200,pheight=800;
	AlienTiles at;
	long period,gamestarttime,prevstatstime,excess,oversleeptime,timeSpentInGame;
	boolean isPaused,running,gameOver;
	private long totalelapsedtime=0L;
	Double[] fpsstore,upsstore;
	private static long MAX_STATS_INTERVAL=16;
	private static final int NUM_FPS=10;
	private static final int NO_DELAYS_PER_YIELD=16;
	private static final int MAX_FRAME_SKIPS=5;
	public Font font;
	Thread animator;
	
	public AlienTilesPanel(AlienTiles chase,long period)
	{
		at=chase;
		this.period=period;
		setBackground(Color.white);
		setPreferredSize(new Dimension(1200,800));
		setFocusable(true);
		requestFocus(true);
		
		//createWorld(Ims);

		
	
		
	}
	
	public void addNotify()
	{
		super.addNotify();
		startgame();
	}
	public void startgame()
	{
		if(animator==null||!running)
		animator=new Thread(this);
		animator.start();
	}

	public void run()
	{
		long beforetime,aftertime,timediff,sleeptime;
		long oversleep=0;
		int nodelays=0;
		long excess=0;
		gamestarttime=System.nanoTime();
		prevstatstime=gamestarttime;
		beforetime=gamestarttime;
		running=true;
	
	while(running)
	{
     	gameupdate();
		gamerender();
		paintscreen();
		aftertime=System.nanoTime();
		timediff=aftertime-beforetime;
		sleeptime=(period-timediff)-oversleeptime;
		if(sleeptime>0)
		{
			try
			{
				Thread.sleep(sleeptime/1000000);
			}
			catch(InterruptedException e)
			{
				oversleeptime=(System.nanoTime()-aftertime)-sleeptime;
			}
		}
		else
		{
			excess-=sleeptime;
			oversleeptime=0L;
			if(++nodelays>=NO_DELAYS_PER_YIELD)
			{
				Thread.yield();
				nodelays=0;
			}
		}
		beforetime=System.nanoTime();
		int skips=0;
		while((excess>period)&&(skips<MAX_FRAME_SKIPS))
		{
			excess-=period;
			gameupdate();
			skips++;
		}
		framesskipped+=skips;
		
	}
	//System.exit(0);
	}
	private void gameupdate()
	{
		
	}
	public void gamerender()
	{
		
	}
	private void paintscreen()
	{
		Graphics gc;
		try
		{
			gc=this.getGraphics();
			if(gc!=null&&dbimage!=null)
			{
				gc.drawImage(dbimage,0,0,null);
			}
			Toolkit.getDefaultToolkit().sync();
			gc.dispose();
		}
		catch(Exception e)
		{
			System.out.println("graphics error");
		}
	}
	private void testpress(int x,int y)
	{
	}
	private void gameovermessage(Graphics gc)
	{
	
		
	}
	public void resumeGame()
	{
		isPaused=false;
	}
	public void pauseGame()
	{
		isPaused=true;
	}
	public void stopGame()
	{
		running=false;
	}
	
	private void createWorld(ImageLoader imsLoader)
	{
		
	}
	
	
}	