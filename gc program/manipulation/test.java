
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;


public class test extends Frame
{
Image im=Toolkit.getDefaultToolkit().getImage("i2.jpg");

public test()
{
super("scaling and flipping");
MediaTracker mt=new MediaTracker(this);
mt.addImage(im,0);
try
{
mt.waitForID(0);
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void paint(Graphics gc)
{
Insets insets=getInsets();
gc.drawImage(im,insets.left+10,insets.top+10,400,266,400,266,0,0,this);
}
public static void main(String gcs[])
{
final test f=new test();
f.setBounds(100,100,800,850);
f.setVisible(true);


f.addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
f.dispose();
System.exit(0);
}
}
);
}
}