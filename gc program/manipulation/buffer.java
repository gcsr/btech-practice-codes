import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Frame.*;
import java.awt.Color;

public class buffer extends Applet
{
Image image;
Image offscreen=null;
Graphics g=null,offg=null;
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i1.jpg");
public void init()
{
try
{
image=createImage((ImageProducer)url.getContent());
mt.addImage(image,0);
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void paint(Graphics gc)
{
try
{
offscreen=this.createImage(1200,800);
offg=offscreen.getGraphics();
gc=this.getGraphics();
offg.setColor(this.getBackground());
offg.fillRect(0,0,1200,800);
offg.drawImage(image,0,0,this);
gc.drawImage(offscreen,0,0,this);
repaint();
}
catch(Exception e)
{
System.out.println("peekutunnav");
}
}

}