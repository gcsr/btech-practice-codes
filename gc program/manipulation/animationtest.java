import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;


public class animationtest extends Applet
{
private Image im;
private int w,h;
private int pixels[],allpixels[];
Image offscreen,animatedimage;
MemoryImageSource mis;



public void init()
{
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i3.GIF");
try
{
im=createImage((ImageProducer)url.getContent());
mt.addImage(im,0);
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}

w=im.getWidth(this);
h=im.getHeight(this);

pixels=new int[w*h];
allpixels=new int[w*h];

offscreen=createImage(w,h);

mis=new MemoryImageSource(w,h,pixels,0,w);
mis.setAnimated(true);
animatedimage=createImage(mis);

PixelGrabber pg=new PixelGrabber(im,0,0,w,h,allpixels,0,w);
try
{
pg.grabPixels();
}
catch(Exception e)
{
e.printStackTrace();
}
Animator anm=new Animator(25);
anm.start();
}
public void update(Graphics g)
{
paint(g);
}
public void paint(Graphics gc)
{
Graphics offg=null;
Dimension size=getSize();
try
{
offg=offscreen.getGraphics();
offg.clearRect(0,0,size.width,size.height);
offg.drawImage(animatedimage,0,0,this);
gc.drawImage(offscreen,0,0,this);
}
finally
{
offg.dispose();
}

}

class Animator extends Thread
{

private int numboxes,curx,cury,curw,curh;
private boolean showing=false;
private Graphics g;
private int[] emptybuffer=new int[w*h];
public Animator(int numboxes)
{
this.numboxes=numboxes;
}
public void run()
{
while(true)
{
if(isShowing())
{
if(showing)
boxin();
else
boxout();
showing=showing?false:true;
pause(1000);
}
}
}
private void boxin()
{
curw=w;
curh=h;
while(curw>0 && curh>0)
{
curx=w/2-curw/2;
cury=h/2-curh/2;
System.arraycopy(emptybuffer,0,pixels,0,w*h);
for(int i=0;i<curh;++i)
{
System.arraycopy(allpixels,(cury+i)*w+curx,pixels,
(cury+i)*w+curx,curw);
}
mis.newPixels();
curw-=w/numboxes;
curh-=h/numboxes;
pause(50);
}
System.arraycopy(emptybuffer,0,pixels,0,w*h);
mis.newPixels();
}
private void boxout()
{
mis.setFullBufferUpdates(false);

curw=w/numboxes;
curh=h/numboxes;


while(curw<w && curh<h)
{
curx=w/2-curw/2;
cury=h/2-curh/2;
System.arraycopy(emptybuffer,0,pixels,0,w*h);
for(int i=0;i<curh;++i)
{
System.arraycopy(allpixels,(cury+i)*w+curx,pixels,
(cury+i)*w+curx,curw);
}
mis.newPixels();
curw+=w/numboxes;
curh+=h/numboxes;
pause(50);
}
System.arraycopy(allpixels,0,pixels,0,w*h);
mis.newPixels();
}
private void pause(int sec)
{
try
{
Thread.currentThread().sleep(sec);
}
catch(Exception e)
{
e.printStackTrace();
}
}
}
}