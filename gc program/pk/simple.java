import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Point;
public class simple extends Applet implements MouseListener
{
int x=0,y=0,xdis=0,ydis=0,mxdis=0,mydis=0,curx=0,cury=0;
int xx=0;
Point[] p=new Point[10];
int i=0;
public void init()
{
addMouseListener(this);
}
public void paint(Graphics gc)
{
Graphics2D gcd=(Graphics2D)gc;
setBackground(new Color(139,139,139));
gcd.setColor(Color.red);
if((mxdis!=xdis)&&(mxdis<0))
{
System.out.println("decrementing x");

mxdis--;
}
else if((mxdis!=xdis)&&(mxdis>0))
{
System.out.println("incrementing x");
mxdis++;
}
if((mydis!=ydis)&&(mydis<0))
{
System.out.println("decrementing y");
mydis--;
}
if((mydis!=ydis)&&(mydis>0))
{
System.out.println("incrementing y");

mydis++;
}
gcd.translate(curx+mxdis,cury+mydis);

gcd.fillOval(10,40,20,20);
gcd.fillOval(80,40,20,20);
gcd.fillArc(20,40,70,20,0,180);
gcd.drawLine(20,50,90,50);
repaint();
}
public void mouseClicked(MouseEvent e)
{
xx=0;
curx=x;
cury=y;
xdis=e.getX()-x;
ydis=e.getY()-y;

if(xdis>0)
mxdis=1;
else
mxdis=-1;
if(ydis>0)
mydis=1;
else
mydis=-1;
x=e.getX();
y=e.getY();
repaint();
}
public void mousePressed(MouseEvent e)
{
}
public void mouseExited(MouseEvent e)
{
}

public void mouseEntered(MouseEvent e)
{
}

public void mouseReleased(MouseEvent e)
{
}

}
