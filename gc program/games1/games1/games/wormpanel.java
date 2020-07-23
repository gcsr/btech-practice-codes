import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.image.ImageProducer;
import java.net.URL;

public class wormpanel extends JPanel implements Runnable
{
	private Image image;
	private long framesskipped=0L;
	private long totalframesskipped=0L;
	private double avgups,avgfps;;
	private int framecount=0;
	private int statscount=0;
	private long statsinterval=0L;
	
	Graphics dbg;
	Image dbimage;
	Point p;
	int pwidth=1200,pheight=800;
	warmchase wctop;
	long period,gamestarttime,prevstatstime,excess,oversleeptime,timeSpentInGame;
	boolean isPaused,running,gameOver;
	private long totalelapsedtime=0L;
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
	
	public wormpanel(warmchase chase,long period)
	{
		MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i.GIF");
try
{
image=createImage((ImageProducer)url.getContent());
mt.addImage(image,0);
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}

		wctop=chase;
		this.period=period;
		setBackground(Color.white);
		setPreferredSize(new Dimension(1200,800));
		obs=new obstacles(wctop);
		fred=new worm(1200,800,obs);
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				testpress(e.getX(),e.getY());
			}
		});
		font=new Font("SanSerif",Font.BOLD,24);
		FontMetrics metrics=this.getFontMetrics(font);
		
		fpsstore=new Double[NUM_FPS];
		upsstore=new Double[NUM_FPS];
		for(int i=0;i<NUM_FPS;i++)
		{
			fpsstore[i]=0.0;
			upsstore[i]=0.0;
		}
		
	}
	public void addNotify()
	{
		super.addNotify();
		startgame();
	}
	
	
	public void readyfortermination()
	{
		addKeyListener(new KeyAdapter()
		{
		    public void keyPressed(KeyEvent e)
			   {
			    int ss=e.getKeyCode();
			    if(ss==KeyEvent.VK_ESCAPE)
			    {
			    	System.out.println("mondi dairyam");
			       running=false;
				}
				}
		
		});
	}
	public void startgame()
	{
		if(animator==null||!running)
		animator=new Thread(this);
		animator.start();
	}
	private void storestatus()
	{
		framecount++;
		statsinterval+=period;
		if(statsinterval>=MAX_STATS_INTERVAL)
		{
			long timenow=System.nanoTime();
			timeSpentInGame=(int)((timenow-gamestarttime)/1000000000L);
			wctop.setTimeSpent(timeSpentInGame);
			
			long realelapsedtime=timenow-prevstatstime;
			totalelapsedtime+=realelapsedtime;
			
			double timingerror=(double)((realelapsedtime-statsinterval)/statsinterval)*100.0;
		    totalframesskipped+=framesskipped;
		    double actualfps=0;
		    double actualups=0;
		    if(totalelapsedtime>0)
		    {   actualfps=(((double)framecount/totalelapsedtime)*1000000000L);
		    	actualups=(((double)(framecount+totalframesskipped)/totalelapsedtime)*1000000000L);
            }
            fpsstore[(int)statscount%NUM_FPS]=actualfps;
            upsstore[(int)statscount%NUM_FPS]=actualups;
            statscount+=1;
            
            double totalfps=0;
            double totalups=0.0;
            for(int i=0;i<NUM_FPS;i++)
            {
            	totalfps+=fpsstore[i];
            	
            	totalups=upsstore[i];
            }   
            if(statscount<NUM_FPS)
            {
            	avgfps=totalfps/statscount;
            	avgups=totalups/statscount;
            	
            }
            else
            {
            	avgfps=totalfps/NUM_FPS;
            	avgups=totalups/NUM_FPS;
            }
            framesskipped=0;
            prevstatstime=timenow;
            statsinterval=0L;
            
		}
	}
	
	private void printstatus()
	{
		System.out.println("framecount/loss"+framecount+"/"+totalframesskipped);
		System.out.println("avgfps:"+(avgfps));
		System.out.println("avgups:"+(avgups));
		System.out.println("timespent:"+timeSpentInGame+"secs");
		System.out.println("boxesused:"+obs.getnumofobstacles());
		
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
		running=true
	;
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
		
		storestatus();
	}
	printstatus();
	//System.exit(0);
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
		p=fred.draw(dbg);
		if(gameOver)
		gameovermessage(dbg);
		
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
				gc.drawImage(image,p.x,p.y,50,50,this);
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
				score=(40-timeSpentInGame)+40-obs.getnumofobstacles();
			}
			else
			{
				if(!fred.touchedat(x,y))
				obs.add(x,y);
			}
		}
	}
	private void gameovermessage(Graphics gc)
	{
		gc.drawString("over"+score,200,200);
	
		
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
	
	
}	