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


public class ReplicateTest extends Applet
{
Image orig,Replicated;
public ReplicateTest()
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
FilteredImageSource fis=new FilteredImageSource(orig.getSource(),new ReplicateScaleFilter(400,400));
//fis=new FilteredImageSource(createImage(fis).getSource(),new ReplicateScaleFilter(310,250));
Replicated=createImage(fis);
}
public void paint(Graphics gc)
{
Insets i=getInsets();
//gc.drawImage(orig,i.left,i.top,this);
gc.drawImage(Replicated,i.left,i.top,this);
}
}