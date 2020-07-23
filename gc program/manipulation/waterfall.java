import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Frame.*;
import java.awt.Color;

public class waterfall extends Applet
{
int i=0;
Image image;
Point g[];
int k=0;
int r=0;
public void init()
{
Button label=new Button("left");
add(label);
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("water.jpg");
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
Insets insets=new Insets(20,20,20,20);
FilteredImageSource fis=new FilteredImageSource(image.getSource(),new DissolveEdgeFilter(0,insets));
dissolveedgefiltered=createImage(fis);
}

public void paint(Graphics gc)
{


}
}