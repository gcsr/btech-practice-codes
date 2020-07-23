 import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Sprite{
	public static final int XSTEP=5;
	public  static final int YSTEP=5;
	
	private ImageLoader imsLoader;
	

	
	private int pWidth,pHeight;
	
	protected int locx,locy;
	protected int dx,dy;
	private String imageName;
	private int width,height;
	
	private boolean isLooping;
	private BufferedImage image;
	private static final int SIZE=12;
	
	
	private boolean isActive=true;
	public Sprite()
	{
	}

	public Sprite(int x,int y,int w,int h,ImageLoader imsLd,String name)
	{
		locx=x;
		locy=y;
		pWidth=w;
		pHeight=h;
		imsLoader=imsLd;
		setImage(name);
	}
	public void setImage(String name)
	{
		imageName=name;
		
		try{
		
				image=imsLoader.loadimage(imageName);
		}
		catch(IOException e)
		{
			width=SIZE;
			height=SIZE;
		}	
		width=image.getWidth();
		height=image.getHeight();
		//isLooping=false;
	}
	public void setStep(int dx,int dy)
	{
		this.dx=dx;
		this.dy=dy;
	}
	public void setPosition(int locx,int locy)
	{
		this.locx=locx;
		this.locy=locy;
	}
	public int getPWidth()
	{
		return pWidth;
	}
	public int getPHeight()
	{
		return pHeight;
	}
	public void updateSprite()
	{
		if(isActive())
		{
			locx+=dx;
			locy+=dy;
		}
	}
	private boolean isActive()
	{
		return isActive;
	}
	public int getHeight()
	{
		return height;
	}
	public int getWidth()
	{
		return width;
	}
	public void drawSprite(Graphics gc)
	{
		if(isActive())
		{
			if(image==null){
				gc.setColor(Color.yellow);
				gc.fillOval(locx,locy,SIZE,SIZE);
				gc.setColor(Color.black);
			}
			else{
				gc.drawImage(image,locx,locy,null);
			}
		}
	}
	public Rectangle getMyRectangle()
	{
		return new Rectangle(locx,locy,width,height);
	}

}