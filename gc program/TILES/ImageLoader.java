import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.ArrayList;

public class ImageLoader
{
private GraphicsConfiguration gc;
	private HashMap imagesmap;
	public ImageLoader()
	{
		imagesmap=new HashMap();
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		gc=ge.getDefaultScreenDevice().getDefaultConfiguration();

	}
	public BufferedImage loadsingleimage(String gcs) throws IOException
	{

		if(imagesmap.containsKey(gcs))
		{
			System.out.println("already used");
			return null;
		}


		BufferedImage bi=loadimage(gcs);
		//System.out.println(bi);
		if(bi!=null)
		{
			ArrayList imslist=new ArrayList();
			imslist.add(bi);
			imagesmap.put(gcs,imslist);
			//System.out.println("stored "+gcs);
			return bi;
		}
		else
        throw new IOException();



	}
	public BufferedImage loadimage(String gcs)throws IOException
	{

		    //System.out.println("loadimage     "+gcs);

			BufferedImage im=ImageIO.read(getClass().getResource(gcs));
			int transparency=im.getColorModel().getTransparency();
			BufferedImage copy=gc.createCompatibleImage(im.getWidth(),
			im.getHeight(),transparency);
			Graphics2D g2d=copy.createGraphics();
		    g2d.drawImage(im,0,0,null);
			g2d.dispose();
			return copy;
	}

}