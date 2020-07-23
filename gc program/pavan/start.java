import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Frame.*;
import java.awt.Color;

public class start extends Applet
{
Image image,nw;
Point g[],h[];
int i=0;
int r=0;
int l=0;
int s=0;
public void init()
{
Button label=new Button("left");
add(label);
Button label1=new Button("right");
add(label1);

MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i3.jpg");
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
FilteredImageSource fis=new FilteredImageSource(image.getSource(),new ReplicateScaleFilter(400,400));
image=createImage(fis);
Insets insets=new Insets(65,83,0,100);
FilteredImageSource his=new FilteredImageSource(image.getSource(),new DissolveEdgeFilter(0,insets));
nw=createImage(his);
try
{
g=new Point[10000];
h=new Point[10000];
}
catch(Exception e)
{
System.out.println("ss");
}
addMouseMotionListener(
new MouseMotionAdapter()
{
public void mouseDragged(MouseEvent e)
{
if(s==0)
{
//System.out.println(i);
g[r]=e.getPoint();
r++;
}
else
{
h[l]=e.getPoint();
l++;
}
repaint();
}
}
);


label.addActionListener(
new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
s=1;
}
}
);
label1.addActionListener(
new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
s=0;
}
}
);

}

public void paint(Graphics gc)
{


setBackground(Color.black);
gc.drawImage(nw,200,200,this);
gc.setColor(Color.black);
for(int j=0;j<r;j++)
gc.fillRect(g[j].x,g[j].y,800,1);
for(int j=0;j<l;j++)
gc.fillRect(h[j].x-800,h[j].y,800,1);

}
}