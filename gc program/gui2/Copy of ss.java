package test;

import java.awt.Graphics;
import java.awt.image.ImageProducer;
import javax.swing.JFrame;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import javax.swing.JPanel;

public class ss extends JPanel
{
Image orig;
public void paintComponent(Graphics gc)
{
super.paintComponent(gc);
URL url=getClass().getResource("i1.JPG");
try
{
System.out.println("Ss");
orig=createImage((ImageProducer)url.getContent());
MediaTracker mt=new MediaTracker(this);
mt.addImage(orig,0);
mt.waitForID(0);
//System.out.println("Ss");
}
catch(Exception e)
{
System.out.println("Ss");
e.printStackTrace();
}
gc.drawImage(orig,0,0,this);
}
}