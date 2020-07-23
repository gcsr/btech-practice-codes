import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
public class test extends Applet
{
Component cpnt=this;
int i=0;
test2 pp;
public void init()
{
pp=new test2();
try
{
Thread.sleep(5000);
}
catch(Exception e)
{
}
}
public void paint(Graphics gc)
{
/**/
/*if(i>100)
{
System.out.println(i);
}
i++;
if(i==1)
while(i<100)
{
gc.drawRect(10,10,i,i);
System.out.println(i);
i++;
}*/
gc.drawLine(20,20,40,40);
Graphics g=cpnt.getGraphics();
pp.aint(g);
}
public void aint(Graphics f)
{
//setBackground(f);
}
}