import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;


public class imupdate extends Applet
{
private Image im;
public void init()
{
im=getImage(getCodeBase(),"i2.jpg");
System.out.println("image width"+im.getWidth(this));
System.out.println("image height"+im.getHeight(this));
}
public void paint(Graphics gc)
{
gc.drawImage(im,0,0,this);
}
public boolean imageUpdate(Image im,int flags,int x,int y,int w,int h)
{
System.out.println(flags);
if((flags&ALLBITS)==0)
return true;
else
{
repaint();
return true;
}
}
}
