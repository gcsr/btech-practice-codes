import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class threedbutton1 extends Canvas implements MouseListener,MouseMotionListener
{
static public int borderinset=0,borderraised=1;
private int state=borderraised;
public void mouseExited(MouseEvent e)
{
}
public void mouseEntered(MouseEvent e)
{
}
public void mouseMoved(MouseEvent e)
{
}
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
paintborderraised();
}
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
public void paint(Graphics gc)
{
System.out.println("paint");
paintborderraised();
}
public Dimension getPreferredSize()
{
System.out.println("getpreferredsize");
return new Dimension(250,250);
}
public void paintborderraised()
{
System.out.println("paintborderraised");
Graphics g=getGraphics();
try
{
g.setColor(Color.gray);
g.draw3DRect(0,0,getSize().width-1,getSize().height-1,true);
state=borderraised;
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
g.draw3DRect(0,0,getSize().width-1,getSize().height-1,false);
state=borderinset;
}
finally
{
g.dispose();
}
}

}