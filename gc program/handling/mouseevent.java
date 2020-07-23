import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;


public class mouseevent extends Applet
{
Point p[];
int i=0;
public void init()
{
p=new Point[500];
Button button=new Button("press me");
button.addMouseListener(
new MouseAdapter()
{
public void mouseEntered(MouseEvent e)
{
System.out.println("mouse entered");
}
public void mouseExited(MouseEvent e)
{
System.out.println("mouse excited");
}
public void mouseClicked(MouseEvent e)
{
System.out.println("mouse clicked");
int s=e.getClickCount();
//e.translatePoint(20,20);
p[i]=e.getPoint();
System.out.println(p[i].x);
repaint();
}
}
);
add(button);
}
public void paint(Graphics gc)
{
gc.setColor(Color.RED);
//if(i>0)
//gc.drawRect(p[i].x,p[i].y,10,10);
}
}