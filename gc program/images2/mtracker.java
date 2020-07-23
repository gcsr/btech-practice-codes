import java.net.URL;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;



public class mtracker extends Applet
{
private Image im;

public void init()
{
MediaTracker t=new MediaTracker(this);
im=getImage(getCodeBase(),"i2.jpg");
t.addImage(im,0);
try
{
t.waitForID(0);
}
catch(InterruptedException e)
{
e.printStackTrace();
}
System.out.println("image width"+im.getWidth(this));
System.out.println("image height"+im.getHeight(this));
}
public void paint(Graphics gc)
{
gc.drawImage(im,0,0,this);
}
}
