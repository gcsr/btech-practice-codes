import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;



public class cons extends Frame implements Runnable

{
Image im;
boolean showing=false;
Dissolver dissolver=null;
int x,y;

public cons()
{

super("image dissolver");
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i2.jpg");
try
{
im=createImage((ImageProducer)url.getContent());
mt.addImage(im,0);
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void addNotify()
{
super.addNotify();

Insets i=getInsets();
dissolver=new Dissolver(this,x=i.left,y=i.top);
synchronized(dissolver)
{
ImageProducer ip=im.getSource();
ip.startProduction(dissolver);
try
{
dissolver.wait();
}
catch(Exception e)
{
e.printStackTrace();
}
}
try
{

Thread thread=new Thread(this);
thread.start();
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void run()
{
while(true)
{
//dissolver.dimages();
try
{
Thread.currentThread().sleep(500);
}
catch(Exception e)
{
e.printStackTrace();
}
}
}
public static void main(String gcs[])
{
final cons f=new cons();
f.setBounds(100,100,373,375);
f.setVisible(true);
f.addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
f.dispose();
System.exit(0);
}
}
);
}
}
