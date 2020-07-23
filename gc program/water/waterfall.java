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


public class waterfall extends Applet
{
Image orig,imagefiltered;
public waterfall()
{
URL url=getClass().getResource("water.JPG");
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


FilteredImageSource fis=new FilteredImageSource(orig.getSource(),new imagefilter(0));
imagefiltered=createImage(fis);

}
public void paint(Graphics gc)
{
Insets i=getInsets();
gc.drawImage(imagefiltered,0,0,this);
}
}