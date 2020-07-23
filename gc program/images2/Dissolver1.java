import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;


class Dissolver1  implements ImageConsumer
{
ColorModel defaultrgb=ColorModel.getRGBdefault();
Image images[];
Image image;
Component component;
Graphics g;
int s=0;
int intpixels[],x,y,width,height,pinterval,numimages;

public Dissolver1(Component c,int x,int y)
{
this(c,x,y,10,50);
}

public Dissolver1(Component c,int x,int y,int numimages,int pinterval)
{
this.component=c;
this.numimages=numimages;
this.pinterval=pinterval;
this.x=x;
this.y=y;
images=new Image[numimages];
}

public void setDimensions(int width,int height)
{
this.width=width;
this.height=height;
intpixels=new int[width*height];;

}

public void setColorModel(ColorModel cmodel)
{
}

public void setProperties(Hashtable props)
{
}
public void setHints(int hints)
{
}

public void setPixels(int x,int y,int w,int h,ColorModel cm,
int pixels[],int offset,int scansize)
{
for(int row=0;row<h;row++)
{
for(int col=0;col<w;col++)
{
int index=offset+row*scansize+col;
intpixels[s]=cm.getRGB((pixels[index])&0xff);
s++;
}
}
}

public void setPixels(int x,int y,int w,int h,ColorModel cm,
byte pixels[],int offset,int scansize)
{
int s=0;
for(int row=0;row<h;row++)
{
for(int col=0;col<w;col++)
{
int index=offset+row*scansize+col;
intpixels[s]=cm.getRGB(pixels[index]&0xff);
s++;
}
}
}
public synchronized void imageComplete(int status)
{

if(status==IMAGEERROR||status==IMAGEABORTED)
{
System.out.println("pp");
return;
}
MemoryImageSource mis=new MemoryImageSource(width,height,defaultrgb,intpixels,0,width);
image=component.createImage(mis);
MediaTracker mt=new MediaTracker(component);
mt.addImage(image,0);
try
{
mt.waitForID(0);
System.out.println("id");
}
catch(Exception e)
{
e.printStackTrace();
}

g=component.getGraphics();
if(g!=null)
{
System.out.println(g.drawImage(image,x,y,component));
g.drawImage(image,x,y,component);
/*try
{
Thread.sleep(1000);
}
catch(Exception e)
{
System.out.println("why");
}
finally{*/
g.dispose();//}

}
notifyAll();
}
}
