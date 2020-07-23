import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;

public class BricksManager
{
	int pwidth;
	int pheight;
	int brickMoveSize=8;
	ImageLoader imLoader;
		private int height=9*22;
	private int width=38*65;
	int xMapHead=0;
	private boolean isMovingRight,isMovingLeft;
	public int[][] bricksList={{1,1},{1,1},{1},{1,1},{1,1},{1,1},{1,1,1},{1,1,1},{1,1,1},{1,1,1,1},{1,1,1,1},
	{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1},{1,1,1},{1,1,1,0,0,0,1},{1,1,1},{1,1,1},{1,1,1},{1,1},{1,1},{1,1},
	{1,1},{1,1},{1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,0,1,1},{1,1,1,0,1,1},{1,1,0,0,1,1},{1,1,0,0,1,1},
	{1,1,1,0,0,0,1},{1,1,0,0,0,0,1},{1,1,0,0,0,0,1},{1,0,0,0,0,0,1,1,1},{1,0,0,0,0,0,1,1,1}};
	public BricksManager(int w,int h,ImageLoader il)
	{
		pwidth=w;
		pheight=h;
		imLoader=il;
		
	}
	
	public int getMoveSize()
	{
		return brickMoveSize;
	}
	
	public int findFloor(int xSprite)
	{
		System.out.println("findfloor");
		
		int xMap=(int)(xSprite/65);
		
		int locY=pheight;
		
		int[] column=bricksList[xMap];
		
		Brick b;
		
		for(int i=0;i<column.length;i++)
		{
			b=new Brick(i,column[i]);
			if(b.getLocY()<locY)
				locY=b.getLocY();
		}
		return locY;
	}
	
	public int getBrickHeight()
	{
		return 22;
	}
	public boolean insideBrick(int xWorld,int yWorld)
	{
		System.out.println("insidebrick");
		//System.out.println(xWorld+"  "+yWorld);
		Point mapCoord=worldToMap(xWorld,yWorld);
		int[] column=bricksList[mapCoord.x];
		Brick b;
		//System.out.println(mapCoord);
		for(int i=0;i<column.length;i++)
		{
			if(column[i]!=0)
			{
			b=new Brick(i,column[i]);
			if(mapCoord.y==b.getMapY())
				return true;
			}	
		}
		return false;
	}
	private Point worldToMap(int xWorld,int yWorld)
	{
		xWorld=xWorld%width;
		if(xWorld<0)
			xWorld+=width;
			
			int mapX=(int)(xWorld/65);
			yWorld=yWorld-(pheight-height)+34;
			int mapY=(int)(yWorld/22);
			
			if(yWorld<0)
				mapY=mapY-1;
				
			return new Point(mapX,mapY);	
					
	}
	public void display(Graphics gc)
	{
		System.out.println("display");
		int bCoord=(int)(xMapHead/65)*65;
		int offset;
		if(bCoord>=0)
			offset=xMapHead-bCoord;
			else
			offset=bCoord-xMapHead;	
				
		if((bCoord>=0)&&(bCoord<pwidth))
		{
		drawBricks(gc,0-(65-offset),xMapHead,width-bCoord-65);
		drawBricks(gc,xMapHead,pwidth,0);
		}
		
		else if(bCoord>=pwidth)
			drawBricks(gc,0-(65-offset),pwidth,width-bCoord-65);
			
		else if((bCoord<0)&&(bCoord>=pwidth-width+65))
			drawBricks(gc,0-offset,pwidth,-bCoord);
			
		else if(bCoord<pwidth-width+65)
		{
			drawBricks(gc,0-offset,width+xMapHead,-bCoord);
			drawBricks(gc,width+xMapHead,pwidth,0);
		}		
		
		
	}
	private void drawBricks(Graphics gc,int xStart,int xEnd,int xBrick)
	{
		int xMap=xBrick/65;
		Brick b;
		int[] column;
		for(int x=xStart;x<xEnd;x+=65)
		{
			column=bricksList[xMap];
			for(int i=0;i<column.length;i++)
			{
				if(column[i]!=0)
				{
				b=new Brick(i);
				b.display(gc,x);
				}
			}
					xMap++;
		}
	}
	public void stayStill()
	{
		isMovingLeft=false;
		isMovingRight=false;	
	}
	public void update()
	{
		if(isMovingRight)
			xMapHead=(xMapHead+brickMoveSize)%width;
		else if(isMovingLeft)
			xMapHead=(xMapHead-brickMoveSize)%width;	
	}
	public void moveLeft()
	{
		System.out.println("moveleft");
		isMovingLeft=true;
		isMovingRight=false;
	}
	public void moveRight()
	{
		System.out.println("moveright");
		isMovingLeft=false;
		isMovingRight=true;
	}
	public int checkBrickTop(int xWorld,int yWorld,int step)
	{
		System.out.println("checkbricktop");
		if(insideBrick(xWorld,yWorld))
		{
			//System.out.println("inside brick");
			int yMapWorld=yWorld-(pheight-height);
			int mapY=(int)(yMapWorld/22);
			int topOffset=yMapWorld-(mapY*22);
			return step-topOffset;
		}
		else
			System.out.println("not inside");
			
		return step;
	}
	public int checkBrickBase(int xWorld,int yWorld,int step)
	{
		System.out.println("checkbrickbase");
		if(insideBrick(xWorld,yWorld))
		{
			int yMapWorld=yWorld-(pheight-height);
			int mapY=(int)(yMapWorld/22);
			int topOffset=yMapWorld-(mapY*22);
			return (step-(22-topOffset));
		}
		return step;
		
	}
}