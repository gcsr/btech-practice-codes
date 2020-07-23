import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Frame.*;
import java.awt.Color;

public class end extends Applet
{
Image image,nw;
Point g[];
int r=0;
int paint=0;
public void init()
{
Button label=new Button("paint");
add(label);


MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("p1.jpg");
URL url1=getClass().getResource("f1.jpg");
try
{
image=createImage((ImageProducer)url.getContent());
nw=createImage((ImageProducer)url1.getContent());
mt.addImage(image,0);
mt.waitForID(0);
mt.addImage(nw,1);
mt.waitForID(1);
}
catch(Exception e)
{
e.printStackTrace();
}
Insets insets=new Insets(200,100,50,100);
FilteredImageSource his=new FilteredImageSource(image.getSource(),new DissolveEdgeFilter(0,insets));
image=createImage(his);
Insets insets1=new Insets(320,300,245,550);
FilteredImageSource hist=new FilteredImageSource(nw.getSource(),new DissolveEdgeFilter(0,insets1));
nw=createImage(hist);
FilteredImageSource fis=new FilteredImageSource(nw.getSource(),new ReplicateScaleFilter(200,200));
nw=createImage(fis);
FilteredImageSource first=new FilteredImageSource(nw.getSource(),new DissolveFilter(0));
nw=createImage(first);

try
{
g=new Point[10000];
}
catch(Exception e)
{
System.out.println("ss");
}
addMouseListener(
new MouseAdapter()
{
public void mouseClicked(MouseEvent e)
{
g[r]=e.getPoint();
r++;
repaint();
}
}
);
label.addActionListener(
new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
paint=1;
repaint();
}
}
);

}
public void paint(Graphics gc)
{

setBackground(Color.black);
for(int x=0;x<300;x++)
{
gc.drawImage(image,(500+f(x))-80,x-100,this);
}
for(int x=0;x<1200;x++)
{
gc.drawImage(image,x-80,600+f(x)-100,this);
}
for(int x=0;x<1200;x++)
{
gc.drawImage(image,x-80,650+f(x)-100,this);
}
for(int x=0;x<1200;x++)
{
gc.drawImage(image,x-80,700+f(x)-100,this);
}
for(int j=0;j<r;j++)
gc.drawImage(nw,g[j].x-80,g[j].y-100,this);

if(paint==1)
{
paint(gc);
}
}
int f(int x)
{
return ((int)(40*Math.sin((double)x)));
}

}