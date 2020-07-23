import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Frame.*;
import java.awt.Color;

public class imp extends Applet
{
Image image;
public void init()
{
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("f1.jpg");
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
Insets insets=new Insets(320,300,245,550);
FilteredImageSource his=new FilteredImageSource(image.getSource(),new DissolveEdgeFilter(0,insets));
image=createImage(his);
FilteredImageSource fis=new FilteredImageSource(image.getSource(),new ReplicateScaleFilter(400,400));
image=createImage(fis);
}
public void paint(Graphics gc)
{
setBackground(Color.white);
gc.drawImage(image,0,0,this);
}
}