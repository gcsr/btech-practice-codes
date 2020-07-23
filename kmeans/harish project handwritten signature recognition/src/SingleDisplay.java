import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class SingleDisplay extends JPanel{
	
	BufferedImage image;
	public SingleDisplay(String path){
		
		try{
		    image = ImageIO.read(new File(path));		    
		    
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		setVisible(true);
		
	}
	
	public SingleDisplay(BufferedImage image){
		this.image=image;
		setVisible(true);
		revalidate();
		repaint();
	}
	
	
	public void updateImage(BufferedImage image){
		this.image=image;
		revalidate();
		repaint();
	}
	
	public void updateImage(String path){
		
		try{
		    image = ImageIO.read(new File(path));
		    revalidate();
		    repaint();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics gc){
		
		
		super.paintComponent(gc);
		System.out.println("repaint called");
		Insets i=getInsets();
		//gc.drawImage(image,i.left+150,i.top,this);
	
		gc.drawImage(ImageOperations.getResizedImage(image),i.left+150,i.top,this);
	}
	
	

}
