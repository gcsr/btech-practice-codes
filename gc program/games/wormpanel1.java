import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
public class wormpanel1 extends JPanel implements Runnable
{
	private long framesskipped=0L;
	private long totalframesskipped=0L;
	private double avgups;
	Graphics dbg;
	Image dbimage;
	int pwidth=400,pheight=400;
	warmchase wctop;
	long period,gamestarttime,prevstatstime,excess,oversleeptime,timeSpentInGame;
	boolean isPaused,running,gameOver;
	obstacles obs;
	worm fred;
	Double[] fpsstore,upsstore;
	long score;
	private static long MAX_STATS_INTERVAL=16;
	private static final int NUM_FPS=10;
	private static final int NO_DELAYS_PER_YIELD=16;
	private static final int MAX_FRAME_SKIPS=5;
	public Font font;
	Thread animator;
	public wormpanel1(warmchase chase)
	{
		wctop=chase;
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(400,400));
		setFocusable(true);
		requestFocus(true);
		
		
		obs=new obstacles(wctop);
		fred=new worm(400,400,obs);
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				testpress(e.getX(),e.getY());
			}
		});
		font=new Font("SanSerif",Font.BOLD,24);
		FontMetrics metrics=this.getFontMetrics(font);
		
	
	}
	public void addNotify()
	{
		super.addNotify();
		startgame();
	}
	private void startgame()
	{
		if(animator==null||!running)
		animator=new Thread();
		animator.start();
	}

	public void run()
	{
		
		running=true
	;
	while(running)
	{
	//	gameupdate();
	//	gamerender();
	//	paintscreen();
	
	
	
	}

	
	}
	private void gameupdate()
	{
		if(!isPaused&&!gameOver)
		fred.move();
	}
	public void gamerender()
	{
		if(dbimage==null)
		{
			dbimage=createImage(pwidth,pheight);
			if(dbimage==null)
			{
				 System.out.println("dbimage is null");
				 return;
			}
			else
			dbg=dbimage.getGraphics();	 
		
		}
		dbg.setColor(Color.white);
		dbg.fillRect(0,0,pwidth,pheight);
		dbg.setColor(Color.blue);
		dbg.setFont(font);
		dbg.drawString("avg fps",20,25);
		dbg.setColor(Color.black);
		obs.draw(dbg);
		fred.draw(dbg);
		
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
		if(!isPaused&&!gameOver)
		{
			if(fred.nearhead(x,y))
			{
				gameOver=true;
				score=(40-timeSpentInGame)+40-obs.numofobstacles();
			}
			else
			{
				if(!fred.touchedat(x,y))
				obs.add(x,y);
			}
		}
	}
	
	
}	