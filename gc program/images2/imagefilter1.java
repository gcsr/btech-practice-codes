import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;


class imagefilter1 extends ImageFilter
{
ColorModel defaultrgb=ColorModel.getRGBdefault();
int s=0;
int pixelrow[],alpha;

public imagefilter1()
{
this(100);
}

public imagefilter1(int alpha)
{
System.out.println("cons");
if(alpha<0||alpha>255)

throw new IllegalArgumentException("bad alpha argument");

this.alpha=alpha;
}

public void setDimensions(int width,int height)
{
System.out.println("setdimensions");
pixelrow=new int[width];;
consumer.setDimensions(width,height);
}

public void setColorModel(ColorModel cmodel)
{
System.out.println("setcolormodel");
consumer.setColorModel(defaultrgb);
}

public void setProperties(Hashtable props)
{
System.out.println("set properties");
String key="image";
String val=Integer.toString(alpha);

Object o=props.get(key);

if(o!=null && o instanceof String)
val=((String)o) +","+val;
props.put(key,val);
consumer.setProperties(props);
}

public void setPixels(int x,int y,int w,int h,ColorModel cm,
int pixels[],int offset,int scansize)
{
//x==0  y==row number  w==width  h==1  offset==0  scansize==400
//System.out.println(scansize);
int pixel,index,originalalpha;
for(int row=0;row<h;row++)
{
for(int col=0;col<w;col++)
{
index=offset+row*scansize+col;
pixel=cm.getRGB(pixels[index]);
originalalpha=defaultrgb.getAlpha(pixel);
pixelrow[col]=pixel;
}
consumer.setPixels(x,y+row,w,1,defaultrgb,pixelrow,0,w);
}
}

public void setPixels(int x,int y,int w,int h,ColorModel cm,
byte pixels[],int offset,int scansize)
{
int pixel,index,originalalpha;
for(int row=0;row<h;row++)
{
for(int col=0;col<w;col++)
{
index=offset+row*scansize+col;
pixel=cm.getRGB(pixels[index]&0xff);
originalalpha=defaultrgb.getAlpha(pixel);
pixelrow[col]=pixel;
}
consumer.setPixels(x,y+row,w,1,defaultrgb,pixelrow,0,w);
}
}
}