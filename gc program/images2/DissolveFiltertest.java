import java.applet.*;
import java.awt.*;

import java.awt.Frame;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.awt.Insets;


public class DissolveFiltertest extends Applet
{
Image orig,dissolvefiltered;
public DissolveFiltertest()
{
URL url=getClass().getResource("i1.JPG");
try
{
orig=createImage((ImageProducer)url.getContent());
MediaTracker mt=new MediaTracker(this);
mt.addImage(orig,0);
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}
FilteredImageSource fis=new FilteredImageSource(orig.getSource(),new DissolveFilter(30));
dissolvefiltered=createImage(fis);
}
public void paint(Graphics gc)
{
Insets i=getInsets();
//gc.drawImage(orig,i.left,i.top,this);
gc.drawImage(dissolvefiltered,i.left,i.top,this);
}
}