import java.applet.Applet;
import java.net.URL;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.awt.event.*;


public class urltest extends Applet
{
Image im;

public void start()
{
URL url=getClass().getResource("i2.jpg");
System.out.println(url);
try
{
im=createImage((ImageProducer)url.getContent());
if(im==null)
System.out.println("null image");
}
catch(Exception e)
{
System.out.println("printing stack trace");
e.printStackTrace();
}
}
public void paint(Graphics gc)
{
Insets insets=getInsets();
gc.drawImage(im,insets.left,insets.top,this);
}
/*public void update(Graphics gc)
{
paint(gc);
}*/
}