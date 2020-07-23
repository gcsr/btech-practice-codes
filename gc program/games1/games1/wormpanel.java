import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
public class wormpanel extends JPanel implements Runnable
{

	Graphics dbg;
	Image dbimage;
	int pwidth=400,pheight=400;
	warmchase wctop;
	worm fred;
	obstacles obs;
	Thread animator;
	Font font;
	boolean running;
	public wormpanel(warmchase chase)
	{
		wctop=chase;

		setBackground(Color.white);
		setPreferredSize(new Dimension(1200,800));
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
		animator=new Thread(this);
		animator.start();
	}

	public void run()
	{

		running=true
	;
	while(running)
	{
		System.out.println("dddddddd");
		gameupdate();
		gamerender();
		paintscreen();



	}


	}
	private void gameupdate()
	{

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

	}
	public static void main(String ar[])
	{
		new wormpanel();
	}

}