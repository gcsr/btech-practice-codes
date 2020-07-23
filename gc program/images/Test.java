package images;

//import java.awt.Frame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.awt.Insets;
//import java.util.Hashtable;

public class Test extends JPanel
{
Image orig,croppedandscaled;
public Test()
{
URL url=getClass().getResource("i1.JPG");
try
{
System.out.println("Ss");
orig=createImage((ImageProducer)url.getContent());
MediaTracker mt=new MediaTracker(this);
mt.addImage(orig,0);
mt.waitForID(0);
System.out.println("Ss");
}
catch(Exception e)
{
e.printStackTrace();
}
setSize(300,300);
setVisible(true);
}
public void paintComponent(Graphics gc)
{
super.paintComponent(gc);
Insets i=getInsets();
gc.drawImage(orig,i.left,i.top,this);
}

}