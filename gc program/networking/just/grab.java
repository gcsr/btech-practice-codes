import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Frame.*;
import java.awt.Color;

public class grab extends Applet
{
Image[] jkl=new Image[10000];
ColorModel cm=ColorModel.getRGBdefault();
int x=0;
int pt=0;
Image image;
Point g[];
int r=0;
Point cli;
int p[];
int s=0;
Point u[];
public void init()
{
try
{
u=new Point[10000];
}
catch(Exception e)
{
System.out.println("porare");
}
System.out.println("allocated");
Button lb=new Button("paint");
add(lb);
Button lb1=new Button("made");
add(lb1);
lb.addActionListener(
new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
repaint();
}
}
);
lb1.addActionListener(
new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
for(int i=0;i<r;i++)
{
for(int j=0;j<r;j++)
{
if((i!=j)&&(g[i].y==g[j].y)&&(g[j].x>g[i].x))
{
int[] a=new int[(g[j].x-g[i].x)];
for(int k=0;k<(g[j].x-g[i].x);k++)
a[k]=p[k];
try
{
u[s]=g[i];
}
catch(Exception ek)
{
System.out.println("mundamopi");
}
MemoryImageSource mis=new MemoryImageSource((g[j].x-g[i].x),1,cm,a,0,(g[j].x-g[i].x));
jkl[s]=createImage(mis);
s++;
break;

}

}

}
r=0;
}
}
);
cli=new Point();
p=new int[500];
MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("bomma.jpg");
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
try
{
g=new Point[10000];
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
g[r]=e.getPoint();
r++;
}
}
);

addMouseListener(
new MouseAdapter()
{
public void mouseClicked(MouseEvent e)
{
cli=e.getPoint();

PixelGrabber pg=new PixelGrabber(image,cli.x,cli.y,500,1,p,0,500);
try
{
pg.grabPixels();
System.out.println("grabbed");
}
catch(Exception ep)
{
System.out.println("exception");
}


}
}
);

}

public void paint(Graphics gc)
{
pt++;
System.out.println("paint  "+pt);

gc.drawImage(image,0,0,this);
for(int i=0;i<s;i++)
gc.drawImage(jkl[i],u[i].x,u[i].y,this);

}
}