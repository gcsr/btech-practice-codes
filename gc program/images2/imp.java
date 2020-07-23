import java.awt.*;
import java.applet.*;

public class imp extends Applet
{
Image im;
public void init()
{
MediaTracker t=new MediaTracker(this);
im=getImage(getCodeBase(),"i4.GIF");
t.addImage(im,0);
try
{
t.waitForID(0);
}
catch(InterruptedException e)
{
e.printStackTrace();
}
}

public void paint(Graphics gc)
{
gc.drawImage(im,0,0,this);
}
public boolean imageUpdate(Image im,int flags,int x,int y,int w,int h)
{
if((flags&FRAMEBITS)!=0)
{
System.out.println(FRAMEBITS);
try
{
Thread.sleep(1000);
}
catch(Exception e)
{
System.out.println("exception");
e.printStackTrace();
}
repaint();
}

System.out.println("sorry"+FRAMEBITS);
return true;
}
}
