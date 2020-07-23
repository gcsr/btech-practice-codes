import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Insets;

public class Brick
{
	int row,column;
	ImageLoader il;
	BufferedImage image;
	public Brick(int r)
	{
		try{
		il=new ImageLoader();
		image=il.loadimage("br.jpg");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		row=r;
		
	}
	public Brick(int r,int c)
	{
		try{
		il=new ImageLoader();
		image=il.loadsingleimage("br.jpg");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		row=r;
		column=c;		
	}
	public int getLocY()
	{
		return (756-(row+1)*22);
	}
	public int getMapY()
	{
		return 8-row;
	}
	public void display(Graphics gc,int xScr)
	{
		
		gc.drawImage(image,xScr,(756-(row+1)*22),null);
	}
	
}