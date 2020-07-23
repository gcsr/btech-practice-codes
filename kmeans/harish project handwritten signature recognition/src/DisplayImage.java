import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DisplayImage extends JPanel{
	
	BufferedImage preImage;
	BufferedImage currImage;
	public DisplayImage(String path){
		
		try{
			preImage = ImageIO.read(new File(path));			 
			 
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		setVisible(true);
	}
	
	public DisplayImage(BufferedImage image){
		preImage = image;
	    currImage = image;
	    setVisible(true);
	    revalidate();
	    repaint();
	}
	
	
	public void updateImage(BufferedImage image){
		preImage=currImage;
		currImage=image;
		revalidate();
		repaint();
	}
	
	public void updateImage(String path){
		
		try{
			preImage=currImage;
		    currImage = ImageIO.read(new File(path));
		    revalidate();
		    repaint();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics gc){
		
		super.paintComponent(gc);
		Insets i=getInsets();	
		System.out.println("repaint called");
		
		//gc.drawImage(preImage,i.left,i.top,this);
		//gc.drawImage(currImage,i.left+300,i.top,this);
		gc.drawImage(ImageOperations.getResizedImage(preImage),i.left,i.top,this);
		gc.drawImage(ImageOperations.getResizedImage(currImage),i.left+300,i.top,this);
	}
	
	

}
