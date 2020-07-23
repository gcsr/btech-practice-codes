import java.awt.*;
import java.awt.event.*;


public class animation extends Frame
{
Insets insets;
Image im;
public static  void main(String gcs[])
{
animation app=new animation();
app.show();
}
public animation()
{
super("image test");
im=Toolkit.getDefaultToolkit().getImage("i2.GIF");

try
{
MediaTracker mt=new MediaTracker(this);
mt.addImage(im,0);
mt.waitForID(0);
}
catch(Exception e)
{
System.out.println("exeption");
e.printStackTrace();
}
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
dispose();
System.exit(0);
}
}
);
}

public void addNotify()
{
super.addNotify();
insets=getInsets();
setBounds(100,100,500+insets.left,421+insets.top);
}
public void paint(Graphics gc)
{
System.out.println("y");
gc.drawImage(im,24,24,this);
}
/*public boolean imageUpdate(Image im,int flags,int x,int y,int w,int h)
{
System.out.println(flags);
if((flags&FRAMEBITS)!=0)
{
try
{
Thread.sleep(500);
}
catch(Exception e)
{
System.out.println("exception");
e.printStackTrace();
}
repaint();
}
return true;
}*/
}