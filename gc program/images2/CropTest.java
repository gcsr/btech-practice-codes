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


public class CropTest extends Applet
{
Image orig,cropped;
public CropTest()
{
System.out.println(getClass());
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
FilteredImageSource fis=new FilteredImageSource(orig.getSource(),new CropImageFilter(0,150,1400,25));
fis=new FilteredImageSource(createImage(fis).getSource(),new ReplicateScaleFilter(2000,40));
cropped=createImage(fis);
}
public void paint(Graphics gc)
{
Insets i=getInsets();
//gc.drawImage(orig,i.left,i.top,this);
gc.drawImage(cropped,i.left,i.top,this);
}
}