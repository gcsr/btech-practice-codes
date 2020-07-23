import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Images {
	
	public static BufferedImage getImage(String fileName){
		BufferedImage img = null;
		try{
		    img = ImageIO.read(new File(fileName));
		}
		catch (IOException e) {
		}
		
		return img;
		
	}
	
	public static byte[] getPixels(BufferedImage image){
		byte[] pixels = (byte[]) image.getData().getDataElements(0, 0,image.getWidth(), image.getHeight(), null);
        return pixels;   
	}	
	

}
