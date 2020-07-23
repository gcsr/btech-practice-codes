import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class CannyUser {
	
	public static void main(String[] gcs){
		
		JFileChooser fileChooser=new JFileChooser();
  		
  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  		int result=fileChooser.showOpenDialog(null);
  		if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				
  		String path=(fileChooser.getSelectedFile().getAbsolutePath());
  		
  		BufferedImage img = null;
		try
		{
		    img = ImageIO.read(new File(path));
		}
		catch (IOException e) 
		{
		}
		
		  				  		
		CannyEdgeDetector detector = new CannyEdgeDetector();
		//adjust its parameters as desired 
		detector.setLowThreshold(0.5f); 
		detector.setHighThreshold(1f); 
		//apply it to an image 
		detector.setSourceImage(img); 
		detector.process(); 
		BufferedImage edges = detector.getEdgesImage();
		new DisplayFrame(edges);
	}

}

class DisplayFrame extends JFrame{
	
	DisplayFrame(final BufferedImage image){
		super("image");
		setSize(1400,1200);
		
		JPanel panel=new JPanel(){				
			public void paintComponent(Graphics gc){
				super.paintComponent(gc);
				gc.drawImage(image,0,0,this);
			}
		};
		add(panel);
		setVisible(true);
	}
}

