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
	final private String defaultImage="E:\\androidtemplates\\icons\\material-design-icons-master\\material-design-icons-master\\editor\\drawable-mdpi\\ic_attach_money_white_36dp.PNG";;
	BufferedImage image;
	private ImageIcon picture;
	private boolean hasHistogramValues;

	public DrawImage()
	{
		System.out.println("in constructor");
				
		try
		{
		    image = ImageIO.read(new File(defaultImage));
		    int type = image.getType();
			System.out.println(type);
			BufferedImage bufImg = ImageIO.read( new File(defaultImage) );
		    BufferedImage convertedImg = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		    convertedImg.getGraphics().drawImage(bufImg, 0, 0, null);
		    type = convertedImg.getType();
			System.out.println(type);
			image = convertedImg;
			int[] pixels = (int[]) convertedImg.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
			
			for (int i = 0; i < pixels.length; i++) {
				int p = pixels[i];
				if(p!=0)
				System.out.print(p+" ");				
			}
		
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	public void updateImage(String imageURL)
	{
		
		BufferedImage img = null;
		try
		{
		    image = ImageIO.read(new File(imageURL));
		    int type = image.getType();
		    System.out.println(type);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		repaint();
		
		
	}
	
	public void updateImage(BufferedImage image)
	{
		this.image=image;
		int type = image.getType();
		System.out.println(type);
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
