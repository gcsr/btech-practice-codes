

package fsem;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.image.ImageProducer;




import javax.swing.JFrame;

public class simple extends JFrame
{
	public simple()
	{
		setTitle("paintcomponent test");
		add(new panel());
	}
	
}

class panel extends JPanel
{
	private Image image;
	private ImageIcon picture;
	public panel()
	{
		//picture=new ImageIcon("i2.GIF");
		MediaTracker mt=new MediaTracker(this);
URL url=getClass().getResource("i2.JPG");
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
		setIgnoreRepaint(false);
	}
	public void paintComponent(Graphics gc)
	{
		super.paintComponent(gc);
		System.out.println("thhis is jkdfljsdfk");
		//picture.paintIcon(this,gc,0,0,20,20);
		gc.drawImage(image,0,0,20,20,this);
		System.out.println("mga");
		
	}
	public Dimension getPreferredSize()
{
return new Dimension(picture.getIconWidth(),picture.getIconHeight());
}
}