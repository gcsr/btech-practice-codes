import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.java.awt.image.*;
import java.net.URL;

public class test1 extends Applet
{
private ColorModel cm=ColorModel.getRGBdefault();
private Image image;
int pixels[];
public void init()
{
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i2.jpg");
try
{
image createImage((ImageProducer)url.getContent());
mt.addImage(image,0);
mt.waitForID(0);
}
catch(Exception e)
{
}
try
{
pixels=new int[400*266];
PixelGrabber pg=new PixelGrabber(image,0,0,400,266,pixels,0,266);
pg.grabPixels();
}
catch(Exception e)
{
}
System.out.println(pixels[0]);
}
public void paint(Graphics gc)
{
}
}