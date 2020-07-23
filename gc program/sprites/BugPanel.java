import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;


public class BugPanel extends JPanel implements Runnable,ActionListener
{
	private ImageIcon picture;
	private long framesskipped=0L;
	private long totalframesskipped=0L;
	private double avgups,avgfps;;
	private int framecount=0;
	private int statscount=0;
	private long statsinterval=0L;
	Graphics dbg;
	Image dbimage;
	int pwidth=1200,pheight=800;
	BugRunner bugTop;
	long period,gamestarttime,prevstatstime,excess,oversleeptime,timeSpentInGame;
	boolean isPaused,running,gameOver;
	private long totalelapsedtime=0L;
	BatSprite bat;
	BallSprite ball;
	Double[] fpsstore,upsstore;
	long score;
	private static long MAX_STATS_INTERVAL=16;
	private static final int NUM_FPS=10;
	private static final int NO_DELAYS_PER_YIELD=16;
	private static final int MAX_FRAME_SKIPS=5;
	public Font font;
	Thread animator;
	private BufferedImage[] images;
	BufferedImage bgImage;
	private int count=0;
	
	public BugPanel(BugRunner br,long period)
	{
		System.out.println("cons");
		bugTop=br;
		this.period=period;
		setDoubleBuffered(false);
		setBackground(Color.black);
		setPreferredSize(new Dimension(pwidth,pheight));
		
		setFocusable(true);
		requestFocus();
		images=new BufferedImage[22];
		ImageLoader imsLoader=new ImageLoader();
		try
		{
				for(int i=0;i<9;i++)
		{
			int s=i+2;
			
			String ss="ss ("+s+").jpg".toString();
			System.out.println(ss);
			images[i]=imsLoader.loadimage(ss);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				int keyCode=e.getKeyCode();
		
		if((keyCode==KeyEvent.VK_ESCAPE)||(keyCode==KeyEvent.VK_Q)||(keyCode==KeyEvent.VK_END)||(keyCode==KeyEvent.VK_C)||(e.isControlDown()))
			running=false;
			
			if(!isPaused&&!gameOver)
			{
				if(keyCode==KeyEvent.VK_LEFT)
					bat.moveLeft();
				else	
				if(keyCode==KeyEvent.VK_RIGHT)
					bat.moveRight();
				else
				if(keyCode==KeyEvent.VK_DOWN)
					bat.stayStill();		
					
			}
			}
		});
		
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				testPress(e.getX());
			}
		});
		
		
		
		
		bat=new BatSprite(pwidth,pheight,imsLoader,(int)(period/1000000L));
		ball=new BallSprite(pwidth,pheight,imsLoader,this,bat);
		new Timer(5000,this).start();
		startgame();
		
	}
	public void actionPerformed(ActionEvent e)
	{
		count=(count+1)%9;
	
	}
	
	public void startgame()
	{
		System.out.println("startgame");
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
			//top.setTimeSpent(timeSpentInGame);
			
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
		//System.out.println("boxesused:"+obs.getnumofobstacles());
		
	}
	public void run()
	{
		System.out.println("run");
		long beforetime,aftertime,timediff,sleeptime;
		long oversleep=0;
		int nodelays=0;
		long excess=0L;
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
		
		//storestatus();
	}
	printstatus();
	//System.exit(0);
	}
	private void gameupdate()
	{
		System.out.println("gameUpdate");
		if(!isPaused&&!gameOver)
		{
			
			bat.updateSprite();
			ball.updateSprite();

		}
	}
	public void gamerender()
	{
		System.out.println("gamerender");
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
		
		bgImage=images[count];
		
		dbg.drawImage(bgImage,0,0,this);

	
			
		bat.drawSprite(dbg);	
		ball.drawSprite(dbg);
			

				
		if(gameOver)
		gameovermessage(dbg);

		
	}
	private void paintscreen()
	{
		System.out.println("printscreen");
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
	private void testPress(int x)
	{
		if(!isPaused&&!gameOver)
		{
			bat.mouseMove(x);
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
	public void gameOver()
	{
		gameOver=true;
	}
	
	
}	