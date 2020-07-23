import java.awt.*;
import java.applet.*;

public class imp1 extends Applet
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
int rate=-1;

if((flags&(FRAMEBITS|ALLBITS))!=0)
rate=0;
else if((flags&SOMEBITS)!=0)
{
if(isInc)
{
try
{
rate =incRate;
if(rate<0)
rate=0;
}
catch(Exception e)
{
rate=100;
}
}
}
if(rate>=0)
repaint(rate,0,0,width,height);
return (flags&(ALLBITS|ABORT)==0);
}

}
