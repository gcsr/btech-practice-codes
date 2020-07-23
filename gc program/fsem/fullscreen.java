package fsem;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;



public class fullscreen extends JFrame
{
	int width,height,bitdepth;
	GraphicsDevice gd;
	public fullscreen()
	{
		setFocusable(true);
		requestFocus();

		addKeyListener(new KeyAdapter()
		{
		    public void keyPressed(KeyEvent e)
			   {
			    int ss=e.getKeyCode();
			    if(ss==KeyEvent.VK_ESCAPE)
			    {
			    			    System.out.println("porare gc");
				System.exit(0);
				}
				}

		});

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				System.out.println("era");
			}
		});


		initfullscreen();
	}


	private void initfullscreen()
	{
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		System.out.println(ge);
		gd=ge.getDefaultScreenDevice();

		System.out.println(gd);
		setUndecorated(true);
		setResizable(true);
		setIgnoreRepaint(true);
		gd.setFullScreenWindow(this);
		showcurrentmode();
		setdisplaymode();


		if(!gd.isFullScreenSupported())
		{

		    System.out.println("fullscreen exclusive mode not supported");
		    System.exit(0);
		}
		//setDisplayMode(800,600,8);
		//setDisplayMode(1280,1024,32);

		pwidth=getBoundds().width;
		pheight=getBounds().height;

		//setbufferstrategy();


	}

	private void showcurrentmode()
	{
		DisplayMode dm=gd.getDisplayMode();
		System.out.println("current displaymode:("+dm.getWidth()+","+dm.getHeight()+
		","+dm.getBitDepth()+","+dm.getRefreshRate()+")");
	}
	private void setdisplaymode()
	{
		if(!gd.isDisplayChangeSupported())
		{
			System.out.println("idisplay change mode is not supported");
			return;

		}
		if(!isDisplayModeAvailable(width,height,bitdepth))
		{
			System.out.println("displaymode  not available");
			return;
		}

	}
	private boolean isDisplayModeAvailable(int width,int height,int bitdepth)
	{

		DisplayMode[] modes=gd.getDisplayModes();
		for(DisplayMode ss:modes)
		{
			if(width==ss.getWIdth()&&height==ss.getHeight()&&bitdepth=ss.getBitDepth())
		    return true;
		}
		return false;
	}
	public static void main(String ar[])
	{
		new fullscreen();

	}
}
