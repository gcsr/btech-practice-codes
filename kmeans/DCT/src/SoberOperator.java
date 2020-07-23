import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SoberOperator {
	
	private int height=1024;
	private int width=1024;
	private static final int hLength=20;
	private static final int wLength=18;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new DrawHistogram("");

	}
	BufferedImage image;
	int[][] pixels;
		
	public SoberOperator(String fileName){			
		
		image=getImage(fileName);
		System.out.println("type is "+image.getType());
		image=getConvertedGrayscaleImage(image);
	}
	
	public SoberOperator(BufferedImage image){			
		System.out.println("type is "+image.getType());
		this.image=image;		
	}
	
	private BufferedImage getConvertedGrayscaleImage(BufferedImage source) {
		BufferedImageOp op = new ColorConvertOp(
		ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
		
	}
	
	
	
	private BufferedImage getImage(String fileName){
		BufferedImage img = null;
		try
		{
		    img = ImageIO.read(new File(fileName));
		}
		catch (IOException e) 
		{
		}
		
		return img;
	}
	
	public void soberValues(){
		
		float[] kernel={-1,0,1,-2,0,2,-1,0,1};
		BufferedImageOp op = new ConvolveOp(new Kernel(3,3,kernel),ConvolveOp.EDGE_NO_OP,null);
		BufferedImage left= op.filter(image, null);
		
		float[] rightKernel={-1,-2,-1,0,0,0,1,2,1};
	    op = new ConvolveOp(new Kernel(3,3,rightKernel),ConvolveOp.EDGE_NO_OP,null);
		BufferedImage right= op.filter(image, null);
		
		Raster raster1=left.getRaster();
		Raster raster2=right.getRaster();
		
		
		pixels=new int[height][width];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				int leftSample=raster1.getSample(j,i,0);
				int rightSample=raster2.getSample(j,i,0);
				pixels[i][j]=(int)(Math.sqrt((leftSample*leftSample)+(rightSample*rightSample)));
				
			}
		}
		
				
	}
	
	public int[] getPixels(){
		
		int[] pp=new int[1024*1024];
		int no=0;
		for(int i=0;i<1024;i++){
			for(int j=0;j<1024;j++){
				pp[no]=pixels[i][j];
				no++;
			}
		}
		
		return pp;
	}
	
	public BufferedImage getEdgesImage() {
	
		
		BufferedImage edgesImage=null;
		if (edgesImage == null) {
			edgesImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		}
		
		//new DrawingFrame(pixels);
		
		 for(int x=0;x<height;x++)
		 {
	            for(int y=0;y<width;y++)
	            {
	                int Pixel=pixels[x][y];
	                edgesImage.setRGB(y, x,Pixel);
	                
	            }

		 }

				
		//edgesImage.getWritableTile(0, 0).setDataElements(0, 0, width, height,bytePixels);
		
		return edgesImage;		
	}


}
