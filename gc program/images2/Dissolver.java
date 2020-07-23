import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;


class Dissolver  implements ImageConsumer
{
ColorModel defaultrgb=ColorModel.getRGBdefault();
Image images[];
Image image;
Component component;
Graphics g;
int s=0;
int intpixels[],x,y,width,height,pinterval,numimages;

public Dissolver(Component c,int x,int y)
{
this(c,x,y,10,50);
}

public Dissolver(Component c,int x,int y,int numimages,int pinterval)
{
if(c!=null)
System.out.println(c);
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
//System.out.println(intpixels[index]);
}
}
}

public void setPixels(int x,int y,int w,int h,ColorModel cm,
byte pixels[],int offset,int scansize)
{
for(int row=0;row<h;row++)
{
for(int col=0;col<w;col++)
{
int index=offset+row*scansize+col;
intpixels[index]=cm.getRGB(pixels[index]&0xff);
}
}
}
public synchronized void imageComplete(int status)
{

/*if(status==IMAGEERROR||status==IMAGEABORTED)
{
System.out.println("pp");
return;
}*/
System.out.println(width+"  "+height);
MemoryImageSource mis=new MemoryImageSource(width,height,defaultrgb,intpixels,0,width);
System.out.println(intpixels[width*height-1]);
image=component.createImage(mis);
MediaTracker mt=new MediaTracker(component);
mt.addImage(image,0);
try
{
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}

g=component.getGraphics();
if(g!=null)
{
System.out.println(g);
System.out.println(x+"  "+y);
g.drawImage(image,width,height,0,0,component);
}
try
{
Thread.sleep(1000);
}
catch(Exception e)
{
System.out.println("why");
}
//makedissolvedimages();
notifyAll();
}

public void makedissolvedimages()
{
MediaTracker mt=new MediaTracker(component);
DissolveFilter df;

for(int i=0;i<images.length;++i)
{
df=new DissolveFilter((255/(numimages-1))*i);

FilteredImageSource fis=new FilteredImageSource(image.getSource(),df);
images[i]=component.createImage(fis);
mt.addImage(images[i],i);
}
mt.addImage(image,numimages);
try
{
mt.waitForAll();
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void dimages()
{
//g=component.getGraphics();
if(g!=null)
{
for(int i=numimages-1;i>0;--i)
{
g.drawLine(10,100,100,100);
try
{
Thread.sleep(1000);
}
catch(Exception e)
{
System.out.println("why");
}
g.clearRect(x,y,width,height);
System.out.println("drawimages");
g.drawImage(images[i],x,y,component);
}
g.dispose();
}
}
}