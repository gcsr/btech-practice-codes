import java.applet.Applet;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ReplicateScaleFilter;


public class Scale extends Applet{
	
	
	static public BufferedImage getScaledImage(int imagex,int imagey,int width,int height,BufferedImage image){
		BufferedImage bi = null;
	    try {
	        
	        bi = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);
	        Graphics2D g = (Graphics2D) bi.createGraphics();
	        g.drawImage(image, imagex, imagey,width,height, null);
	    	g.dispose();	
	    	g.setComposite(AlphaComposite.Src);
	     
	    	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	    	RenderingHints.VALUE_RENDER_QUALITY);
	    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	    	RenderingHints.VALUE_ANTIALIAS_ON);
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }
	    return bi;

	}

}
