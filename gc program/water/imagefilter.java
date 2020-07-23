import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;


class imagefilter extends ImageFilter
{
ColorModel defaultrgb=ColorModel.getRGBdefault();
int s=0;
int pixelrow[],alpha;

public imagefilter()
{
this(100);
}

public imagefilter(int alpha)
{
if(alpha<0||alpha>255)

throw new IllegalArgumentException("bad alpha argument");

this.alpha=alpha;
}

public void setDimensions(int width,int height)
{
pixelrow=new int[width];;
consumer.setDimensions(width,height);
}

public void setColorModel(ColorModel cmodel)
{
consumer.setColorModel(defaultrgb);
}

public void setProperties(Hashtable props)
{
String key="image";
String val=Integer.toString(alpha);

Object o=props.get(key);

if(o!=null && o instanceof String)
val=((String)o) +","+val;
props.put(key,val);
consumer.setProperties(props);
}

public void setPixels(int x,int y,int w,int h,ColorModel defaultrgb,
int pixels[],int offset,int scansize)
{
int pixel,index,originalalpha;
for(int row=0;row<h;row++)
{
for(int col=0;col<w;col++)
{
s++;
index=offset+row*scansize+col;
pixel=defaultrgb.getRGB(pixels[index]);
originalalpha=defaultrgb.getAlpha(pixel);
int alpha=defaultrgb.getAlpha(pixel);
int red=defaultrgb.getRed(pixel);
int blue=defaultrgb.getBlue(pixel);
int green=defaultrgb.getGreen(pixel);
if(((y>200)&&(y<500))&&(col>400))
{
if((red>0)&&((blue>0)||(green>0)))
pixel=-16599084;
}

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
int alpha=defaultrgb.getAlpha(pixel);
int red=defaultrgb.getRed(pixel);
int blue=defaultrgb.getBlue(pixel);
int green=defaultrgb.getGreen(pixel);

if((red>200)&&(blue>200)&&(green>200))
pixel=-16599084;
pixelrow[col]=pixel;
}
consumer.setPixels(x,y+row,w,1,defaultrgb,pixelrow,0,w);
}
}
}