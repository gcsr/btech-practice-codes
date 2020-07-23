package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.*;	
import java.awt.event.MouseMotionListener;




public class mouseframe extends JFrame
{
Image image;
public mouseframe()
{
setTitle("mouseframe");
setSize(300,200);

mousecomponent component=new mousecomponent();
add(component);
}
}


class mousecomponent extends JPanel
{

public mousecomponent()
{
//Toolkit tk=Toolkit.getDefaultToolkit();
//Image image=tk.getImage("gc.jpg");
//Cursor dcursor=tk.createCustomCursor(image,new Point(10,10),"me");
squares=new ArrayList<Rectangle2D>();
current=null;

addMouseListener(new mousehandler());
addMouseMotionListener(new mousemotionhandler());
}

public void paintComponent(Graphics gc)
{
Graphics2D gcd=(Graphics2D)gc;
System.out.println("paints");

for(Rectangle2D r:squares)
{
System.out.println(r);
gcd.draw(r);
}
}

public Rectangle2D find(Point2D p)
{
for(Rectangle2D r:squares)
{
if(r.contains(p))
return r;
}
return null;
}

public void add(Point2D p)
{
double x=p.getX();
double y=p.getY();

current=new Rectangle2D.Double(x-10,y-10,10,10);
squares.add(current);
repaint();
}


public void remove(Rectangle2D s)
{
if(s==current)
{
current=null;
Rectangle2D sp=squares.remove(squares.indexOf(s));
System.out.println(sp);
repaint();
}
else
return;
}


private ArrayList<Rectangle2D> squares;
private Rectangle2D current;


private class mousehandler extends MouseAdapter
{

public void mousePressed(MouseEvent e)
{
current=find(e.getPoint());
System.out.println("mousePressed");
if(current==null)
add(e.getPoint());
}

public void mouseClicked(MouseEvent e)
{
current=find(e.getPoint());
System.out.println(current);

if(current!=null&&e.getClickCount()>=2)
{
System.out.println("removing");
remove(current);
}
}

}


private class mousemotionhandler implements MouseMotionListener
{
public void mouseMoved(MouseEvent e)
{
System.out.println("mousemoved");
//setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
}
public void mouseDragged(MouseEvent e)
{
System.out.println("mousemoved");
}
}


}