import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Frame.*;
import java.awt.Color;

public class waterfall1 extends Applet
{
ColorModel cm=ColorModel.getRGBdefault();
int i=0;
Image image;
Point g;
int k=0;
int pixels[];
public void init()
{

MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("water.jpg");
try
{
image=createImage((ImageProducer)url.getContent());
mt.addImage(image,0);
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}
addMouseListener(
new MouseAdapter()
{
public void mouseClicked(MouseEvent e)
{
g=e.getPoint();
System.out.println(g.x+"  "+g.y);
}
}
);

/*int j=0;
pixels=new int[124*300]; 
PixelGrabber pg=new PixelGrabber(image,900,400,124,300,pixels,0,124);
int[] pp=new int[1200*800];
try
{
pg.grabPixels();
}
catch(Exception e)
{
System.out.println("ss");
}*/
/*for(int i=0;i<pp.length;i++)
{
pp[i]=pixels[j%(124*300)];
j++;
}
MemoryImageSource mis=new MemoryImageSource(124,300,cm,pixels,0,124);
//image=createImage(mis);
*/
}

public void paint(Graphics gc)
{
int i=0;
gc.drawImage(image,i,0,this);

}
}