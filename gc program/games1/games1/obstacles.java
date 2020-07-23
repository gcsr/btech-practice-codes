import java.util.ArrayList;
import java.awt.*;
import java.awt.Color;



public class obstacles
{
	ArrayList<Rectangle> boxes;
	warmchase wctop;
	public obstacles(warmchase wc)
	{
		wctop=wc;
	    boxes=new ArrayList<Rectangle>();
	}
		public void add(int x,int y)
	{
		boxes.add(new Rectangle(x,y,12,12));
		wctop.setboxnumber(boxes.size());
		
	}
	synchronized public void draw(Graphics gc)
	{

		Rectangle box;
		gc.setColor(Color.BLUE);
		for(int i=0;i<boxes.size();i++)
		{
			box=(Rectangle)boxes.get(i);
			gc.fillRect(box.x,box.y,box.width,box.height);
		}
	}
	public boolean hits(Point p,int size)
	{
		Rectangle r=new Rectangle(p.x,p.y,size,size);
		Rectangle box;
		for(int i=0;i<boxes.size();i++)
		{
			box=(Rectangle)boxes.get(i);
			if(box.intersects(r))
			return true;
		}
		return false;
	}
	public int numofobstacles()
	{
		return boxes.size();
	}
}