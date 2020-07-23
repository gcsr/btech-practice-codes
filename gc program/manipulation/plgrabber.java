import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;


public class plgrabber extends Applet
{
private Image im;
private Image cropped;
private int w,h;

public void init()
{
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i3.GIF");
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

w=im.getWidth(this);
h=im.getHeight(this);
int[] pixels=new int[w*h];
PixelGrabber pg=new PixelGrabber(im,0,0,w,h,pixels,0,w);
try
{
pg.grabPixels();
}
catch(Exception e)
{
e.printStackTrace();
}
ImageProducer ip=new MemoryImageSource(w,h,pixels,0,w);
cropped=createImage(ip);
}
public void paint(Graphics gc)
{
//gc.drawImage(im,0,0,this);
System.out.println("printing");
gc.drawImage(cropped,20,0,this);
//repaint();
}
}