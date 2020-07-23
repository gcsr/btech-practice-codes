import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

class threedbutton extends Canvas
{
static public int borderinset=0,borderraised=1;
private int state=borderraised;
threedbutton()
{
addMouseListener(new threedbuttonlistener());
addMouseMotionListener(new threedbuttonmotionlistener());
System.out.println("this is constructor");

}
public void paint(Graphics gc)
{
paintborderraised();
}
public Dimension getPreferredSize()
{
return new Dimension(250,250);
}
public void paintborderraised()
{
Graphics g=getGraphics();
try
{
g.setColor(new Color(192,192,192));
//g.draw3DRect(0,0,getSize().width-1,getSize().height-1,true);
//g.fillRect(10,10,50,50);
//g.draw3DRect(10,10,50,50,true);
state=borderraised;
Point[] p=new Point[3];
p[0].x=10;p[0].y=10;
p[1].x=20;p[1].y=20;
p[2].x=30;p[2].y=30;
int x[]={10,20,30};
int y[]={10,20,30};
g.drawPolygon(x,y,3);
}
finally
{
g.dispose();
}
}
public void paintborderinset()
{
Graphics g=getGraphics();
try
{
g.setColor(Color.gray);
g.fillRect(10,10,50,50);
g.draw3DRect(10,10,50,50,false);
state=borderinset;
}
finally
{
g.dispose();
}
}
class threedbuttonlistener extends MouseAdapter
{

public void mouseClicked(MouseEvent e)
{
System.out.println("mouseclicked");
paintborderraised();
}
public void mousePressed(MouseEvent e)
{
System.out.println("mousepressed");
paintborderinset();
}
public void mouseReleased(MouseEvent e)
{
System.out.println("mousereleased");
//paintborderraised();
}
}
class threedbuttonmotionlistener extends MouseMotionAdapter
{
public void mouseDragged(MouseEvent e)
{
System.out.println("mousedragged");
if(contains(e.getX(),e.getY()))
{
if(state==borderraised)
paintborderinset();
else
paintborderraised();
}
}
}
}