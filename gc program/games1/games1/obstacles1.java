import java.util.ArrayList;
import java.awt.*;
import java.awt.Color;



public class obstacles1
{
	ArrayList<Rectangle> boxes;
	
	public obstacles1()
	{
	
	    boxes=new ArrayList<Rectangle>();
	}
		public void add(int x,int y)
	{
		boxes.add(new Rectangle(x,y,12,12));
		
		
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
	
}