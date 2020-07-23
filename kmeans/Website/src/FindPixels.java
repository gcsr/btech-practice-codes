import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class FindPixels extends JPanel implements MouseListener 
{
	final private String defaultImage="D:/chowda.jpg";
	BufferedImage image;
	private ImageIcon picture;
	private int[][][] histogramValues;
	
	private boolean hasHistogramValues;

	public FindPixels()
	{
				
		try
		{
		    image = ImageIO.read(new File(defaultImage));
		}
		catch (IOException e) 
		{
		}
		
		image=getConvertedGrayscaleImage(image);
		
		addMouseListener(this);
		//addActionListener(this);
	}
	
	private BufferedImage getConvertedGrayscaleImage(BufferedImage source) {
		BufferedImageOp op = new ColorConvertOp(
		ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
	}
	
		
		
	public void paintComponent(Graphics gc)
	{
		
		super.paintComponent(gc);
		Insets i=getInsets();
		System.out.println("on paint");
		
		
		int width=image.getWidth();
		int height=image.getHeight();
		
		int boxWidth=width/10;
		int boxLength=height/10;
		
		gc.drawImage(image,i.left,i.right,this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX()+" "+e.getY());
		
		BufferedImage block=image.getSubimage(e.getX()-10, e.getY()-10,20,20);
		
		//new PixelsFrame(block);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
