import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;


public class JackPanel extends JPanel implements Runnable,ActionListener
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
	JumpingJack jackTop;
	long period,gamestarttime,prevstatstime,excess,oversleeptime,timeSpentInGame;
	boolean isPaused,running,gameOver;
	private long totalelapsedtime=0L;
	FireBallSprite fireball;

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
	BricksManager bricksMan;
	int brickMoveSize;
	ImageLoader imLoader;
	JumperSprite jack;
	
	public JackPanel(JumpingJack br,long period)
	{
		jackTop=br;
		this.period=period;
		setDoubleBuffered(false);
		setBackground(Color.black);
		setPreferredSize(new Dimension(pwidth,pheight));
		
		setFocusable(true);
		requestFocus();
		
	
		imLoader=new ImageLoader();
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
				{
					jack.moveLeft();
					bricksMan.moveRight();
				}
				else	
				if(keyCode==KeyEvent.VK_RIGHT)
				{
					jack.moveRight();
					bricksMan.moveLeft();
				}
				else
				if(keyCode==KeyEvent.VK_UP)
					jack.jump();
					
					
				else
				if(keyCode==KeyEvent.VK_DOWN)	
				{
					jack.stayStill();
					bricksMan.stayStill();
				}
					
					
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
		
		
		bricksMan=new BricksManager(pwidth,pheight,imLoader);
		brickMoveSize=bricksMan.getMoveSize();
		
		jack=new JumperSprite(pwidth,pheight,brickMoveSize,bricksMan,imLoader,(int)(period/1000000L));
		fireball=new FireBallSprite(pwidth,pheight,this,jack);

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
	
	public void run()
	{
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
		
	}
	}
	private void gameupdate()
	{
		if(!isPaused&&!gameOver)
		{
			if(jack.willHitBrick())
			{
				jack.stayStill();
				bricksMan.stayStill();
			}
			bricksMan.update();
			jack.updateSprite();
;

		}
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
		
		bricksMan.display(dbg);
		jack.drawSprite(dbg);
				
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