import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.*;
import java.net.*;
import java.awt.Image.*;
import javax.swing.ImageIcon;
import java.applet.Applet;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;


public class worm
{
	private static final int DOTSIZE=12;
	private static final int MAX_POINTS=40;
	private static final int RADIUS=DOTSIZE/2;;
	private Point cells[];
	private int npoints;
	private int tailposn,headposn;
	private static final int NUM_DIRS=8;
	private static final int N=0;
	private static final int NE=1;
	private static final int E=2;
	private static final int SE=3;
	private static final int S=4;
	private static final int SW=5;
	private static final int W=6;
	private static final int NW=7;
	private int currcompass;
	Point2D.Double incrs[];
	int pwidth,pheight;
	obstacles obs;
	int[] probsforoffset;
	
	public worm(int pwidth,int pheight,obstacles obts)
	{
		
		

		obs=obts;
		probsforoffset=new int[9];
		probsforoffset[0]=0;
		probsforoffset[1]=0;
		probsforoffset[2]=0;
		probsforoffset[3]=1;
		probsforoffset[4]=1;
		probsforoffset[5]=2;
		probsforoffset[6]=-1;
		probsforoffset[7]=-1;
		probsforoffset[8]=-2;
		this.pwidth=pwidth;
		this.pheight=pheight;
		cells=new Point[MAX_POINTS];
		npoints=0;
		headposn=-1;
		tailposn=-1;
		incrs=new Point2D.Double[NUM_DIRS];
		incrs[N]=new Point.Double(0.0,-1.0);
		incrs[NE]=new Point.Double(0.7,-0.7);
		incrs[E]=new Point.Double(1.0,0.0);
		incrs[SE]=new Point.Double(0.7,0.7);
		incrs[S]=new Point.Double(0.0,1.0);
		incrs[SW]=new Point.Double(-0.7,0.7);
		incrs[W]=new Point.Double(-1.0,0.0);
		incrs[NW]=new Point.Double(-0.7,-0.7);
	}
	private int varybearing()
	{
		int newoffset=probsforoffset[(int)Math.random()*9];
		return calcbearing(newoffset);
	}
	private int calcbearing(int offset)
	{
		int turn=currcompass+offset;
		if(turn>=NUM_DIRS)
		turn-=NUM_DIRS;
		if(turn<0)
		turn+=NUM_DIRS;
		return turn;
	}
	public void move()
	{
		int prevposn=headposn;
		headposn=(headposn+1)%MAX_POINTS;
		
		if(npoints==0)
		{
			tailposn=headposn;
			currcompass=(int)(Math.random()*NUM_DIRS);
			cells[headposn]=new Point(pwidth/2,pheight/2);
			npoints++;
		}
		else if(npoints==MAX_POINTS)
		{
			tailposn=(tailposn+1)%MAX_POINTS;
			newhead(prevposn);
			
		}
		else
		{
			newhead(prevposn);
			npoints++;
		}
	}	
		
	private Point nextpoint(int prevposn,int bearing)
	{
		Point2D.Double incr=incrs[bearing];
		
		int newx=cells[prevposn].x+(int)(DOTSIZE*incr.x);
		int newy=cells[prevposn].y+(int)(DOTSIZE*incr.y);
		
		if(newx+DOTSIZE<0)
		newx+=pwidth;
		else
		if(newx>pwidth)
		newx-=pwidth;
		
		if(newy+DOTSIZE<0)
		newy+=pheight;
		else
		if(newy>pheight)
		newy-=pheight;
		
		return new Point(newx,newy);
	}
	private void newhead(int prevposn)
	{
		int fixedoffs[]={-2,2,-4};
		
		int newbearing=varybearing();
		Point newpt=nextpoint(prevposn,newbearing);
		
		if(obs.hits(newpt,DOTSIZE))
		{
			for(int i=0;i<fixedoffs.length;i++)
			{
				newbearing=calcbearing(fixedoffs[i]);
				newpt=nextpoint(prevposn,newbearing);
				if(!obs.hits(newpt,12))
				break;
			}
		}
		cells[headposn]=newpt;
		currcompass=newbearing;
	}
	public boolean touchedat(int x,int y)
	{
		int i=tailposn;
		while(i!=headposn)
		{
			if((Math.abs(cells[i].x+RADIUS-x)<=RADIUS)&&
			(Math.abs(cells[i].y+RADIUS-y)<=RADIUS))
			return true;
			i=(i+1)%MAX_POINTS;
		
		
		}
		return false;
		
	}
	public boolean nearhead(int x,int y)
	{
		if(npoints>0)
		{
			if((Math.abs(cells[headposn].x+RADIUS-x)<=RADIUS)&&
			(Math.abs(cells[headposn].y+RADIUS-y)<=RADIUS))
			return true;
		}
		return false;
	}
	public Point draw(Graphics gc)
	{
		if(npoints>0)
		{
			gc.setColor(Color.black);
			int i=tailposn;
			while(i!=headposn)
			{
				gc.fillOval(cells[i].x,cells[i].y,12,12);
				i=(i+1)%MAX_POINTS;
			}
			//gc.setColor(Color.red);
		    //gc.fillRect(cells[headposn].x-5,cells[headposn].y-5,17,17);
		 return cells[headposn];

		}
		return null;
			
	}
}