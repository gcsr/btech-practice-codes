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


public class DissolveEdgeFiltertest extends Applet
{
Image orig,dissolveedgefiltered;
public DissolveEdgeFiltertest()
{
URL url=getClass().getResource("word.JPG");
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

Insets insets=new Insets(20,20,20,20);
FilteredImageSource fis=new FilteredImageSource(orig.getSource(),new DissolveEdgeFilter(100,insets));
dissolveedgefiltered=createImage(fis);
}
public void paint(Graphics gc)
{
Insets i=getInsets();
gc.drawImage(dissolveedgefiltered,300,200,this);
}
}