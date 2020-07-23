import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class DrawImage extends JPanel implements MouseListener,MouseMotionListener{
	Point[] points=new Point[10000];
	int length=0;
	//final private String defaultImage="D:/engine.jpg";
	final private String defaultImage="D:/wavelet.png";
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
		addMouseListener(this);
		addMouseMotionListener(this);
		
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
		//System.out.println("on paint");
						
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		points[length]=e.getPoint();
		length++;
		if(length>9999)
			length=9999;
			
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		points[length]=e.getPoint();
		length++;
		if(length>9999)
			length=9999;
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
