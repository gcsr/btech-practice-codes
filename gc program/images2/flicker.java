import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;


public class flicker extends Applet
{
private Image im;
public void init()
{
System.out.println("init");
im=getImage(getCodeBase(),"i2.jpg");
System.out.println("image width"+im.getWidth(this));
System.out.println("image height"+im.getHeight(this));
}
public void paint(Graphics gc)
{
System.out.println("paint");
gc.drawImage(im,0,0,this);
}
public boolean imageUpdate(Image im,int flags,int x,int y,int w,int h)
{
System.out.println("iupdate");
//System.out.println(flags);
repaint();
try
{
Thread.sleep(10);
}
catch(InterruptedException e)
{
}
if((flags&ALLBITS)==0)
return true;
else
return false;
}
public void update(Graphics gc)
{
System.out.println("update");
paint(gc);
}
}
