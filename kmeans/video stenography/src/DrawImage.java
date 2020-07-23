import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class DrawImage extends JPanel
{
	final private String defaultImage="D:/engine.jpg";
	BufferedImage image;
	private ImageIcon picture;
	private boolean hasHistogramValues;

	public DrawImage()
	{
				
		try
		{
		    image = ImageIO.read(new File(defaultImage));
		}
		catch (IOException e) 
		{
		}
		
		
	}
	
	public void updateImage(String imageURL)
	{
		
		BufferedImage img = null;
		try
		{
		    image = ImageIO.read(new File(imageURL));
		}
		catch (IOException e) 
		{
		}
		
		repaint();
		
		
	}
	
	public void updateImage(BufferedImage image)
	{
		this.image=image;
		repaint();
			
	}
	
	
	public void paintComponent(Graphics gc)
	{
		
		super.paintComponent(gc);
		Insets i=getInsets();
		gc.drawImage(image,i.left,i.right,this);
		System.out.println("on paint");
						
		
	}


}
