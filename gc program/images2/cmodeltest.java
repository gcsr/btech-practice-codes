import java.awt.*;
import java.applet.Applet;
import java.awt.image.*;
import java.awt.event.*;


public class cmodeltest extends Applet{
Color[] colors={Color.red,Color.yellow,Color.blue,Color.cyan,Color.white,Color.green,Color.magenta,Color.orange};

byte[] reds={
(byte)colors[0].getRed(),(byte)colors[1].getRed(),(byte)colors[2].getRed(),
(byte)colors[3].getRed(),(byte)colors[4].getRed(),(byte)colors[5].getRed(),
(byte)colors[6].getRed(),(byte)colors[7].getRed()};
byte[] greens={
(byte)colors[0].getGreen(),(byte)colors[1].getGreen(),(byte)colors[2].getGreen(),
(byte)colors[3].getGreen(),(byte)colors[4].getGreen(),(byte)colors[5].getGreen(),
(byte)colors[6].getGreen(),(byte)colors[7].getGreen()};
byte[] blues={
(byte)colors[0].getBlue(),(byte)colors[1].getBlue(),(byte)colors[2].getBlue(),
(byte)colors[3].getBlue(),(byte)colors[4].getBlue(),(byte)colors[5].getBlue(),
(byte)colors[6].getBlue(),(byte)colors[7].getBlue()};

IndexColorModel icm=new IndexColorModel(8,8,reds,greens,blues);

ColorModel cm=ColorModel.getRGBdefault();
Image image;


public void init(){
int[] imagebits={
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7};

MemoryImageSource mis=new MemoryImageSource(24,10,icm,imagebits,0,24);
image=createImage(mis);
int p=icm.getPixelSize();
System.out.println(p);
int f=icm.getRGB(p);
System.out.println(f);
Color opp=Color.decode("-65536");
System.out.println(opp);
}
public void paint(Graphics gc)
{
gc.drawImage(image,0,0,240,200,this);
}
}
