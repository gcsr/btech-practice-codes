import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;


public class ImageOperations {
	public static BufferedImage getGrayScaleImage(BufferedImage image){
		BufferedImageOp op = new ColorConvertOp(
				ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
				return op.filter(image, null);
	}
	
	public static BufferedImage getBinaryImage(BufferedImage image){
		
		int width=image.getWidth();
		int height=image.getHeight();
		int picsize=width*height;
		
		int[] data=new int[picsize];
			////System.out.println("image type is BYTE_GRAY");
		byte[] pixels = (byte[]) image.getData().getDataElements(0, 0, width, height, null);
		for (int i = 0; i < picsize; i++) {
			data[i] = (pixels[i] & 0xff);
		}
		
		byte[] imp=new byte[data.length];
		
		for (int i = 0; i < picsize; i++) {
			//if(i<4000)
			//System.out.println(data[i]);
			if(data[i]<100)
					imp[i]=(byte)0;
			else
				imp[i]=(byte)255;
						
		}		
		
		return writeEdges(imp,width,height);
	}
	
	private static BufferedImage writeEdges(byte pixels[],int width,int height) {
		//NOTE: There is currently no mechanism for obtaining the edge data
		//in any other format other than an INT_ARGB type BufferedImage.
		//This may be easily remedied by providing alternative accessors.
		BufferedImage edgesImage=null;
		edgesImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		////System.out.println("pixels size is "+pixels.length);
		edgesImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, pixels);
		
		return edgesImage;
		
		/*int xx=0;
		for(int p:pixels){
			xx++;
			//System.out.print(p);
			if(xx%180==0)
				//System.out.println();
		}*/
		
		////System.out.println("size is "+pixels.length);
	}
	
	
	
	public static BufferedImage getRotatedImage(BufferedImage image){
		
		return image;
	}
	
	public static BufferedImage getDilatedImage(BufferedImage image){
		
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				
			}
		} 
		
		pixels=dilate(pixels);
		
		raster=image.getRaster();
	
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				image.setRGB(j,i, pixels[i][j]);
				
			}
		} 
		
			
		

		
		return image;
	}
	
	private static int[][] dilate(int[][] image){
	    for (int i=0; i<image.length; i++){
	        for (int j=0; j<image[i].length; j++){
	            if (image[i][j] == 1){
	                if (i>0 && image[i-1][j]==0) image[i-1][j] = 2;
	                if (j>0 && image[i][j-1]==0) image[i][j-1] = 2;
	                if (i+1<image.length && image[i+1][j]==0) image[i+1][j] = 2;
	                if (j+1<image[i].length && image[i][j+1]==0) image[i][j+1] = 2;
	            }
	        }
	    }
	    for (int i=0; i<image.length; i++){
	        for (int j=0; j<image[i].length; j++){
	            if (image[i][j] == 2){
	                image[i][j] = 1;
	            }
	        }
	    }
	    return image;
	}
	
	
	
	public static BufferedImage getThinnedImage(BufferedImage image){
		
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				//if()
					////System.out.print(pixels[i][j]);
				if (pixels[i][j] !=18) {
                    image.setRGB(j, i, 0);
 
                } else {
                    image.setRGB(j, i,-125);
                }
				
			}
			////System.out.println();
		} 
		
	
		return image;
	}
	
	public static BufferedImage getBoundingBox(BufferedImage image){
		
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		
		int left=width,top=height,right=0,bottom=0;
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				if (pixels[i][j] == 0) {
                   
					if(i<top)
						top=i;
					if(i>bottom)
						bottom=i;
					if(j<left)
						left=j;
	
					if(j>right)
						right=j;
					
                } 				
			}
		} 
		
		image=getSubImage(image,left,right,top,bottom);
		return image;
		
	}
	
	public static BufferedImage getResizedImage(BufferedImage originalImage){
		BufferedImage resizedImage = new BufferedImage(256,256,originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0,256,256, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		return resizedImage;
	}
	
	public static BufferedImage getSubImage(BufferedImage image, int left, int right, int top, int bottom){
		image=image.getSubimage(left,top,right-left,bottom-top);
		return image;
	}
	
	public static int[] getMaximumHistogram(BufferedImage image){
		
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				
			}
		} 
	
		int[] results=new int[2];
		
		int preMax=0;
		for(int i=0;i<height;i++){
			int counter=0;
			for(int j=0;j<width;j++){
				if(pixels[i][j]==0)
					counter++;
			}
			if(counter>preMax)
				preMax=counter;
		}
		
		results[0]=preMax;
		
		preMax=0;
		for(int i=0;i<width;i++){
			int counter=0;
			for(int j=0;j<height;j++){
				if(pixels[j][i]==0)
					counter++;
			}
			if(counter>preMax)
				preMax=counter;
		}
		
		results[1]=preMax;
		
		return results;
	}
	
	public static Point[] getCenterOfMass(BufferedImage image){
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		double x=0,y=0;
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		int counter=0;
		for(int i=0;i<height/2;i++)
		{
			for(int j=0;j<width/2;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				if(pixels[i][j]==0){
					x+=i;
					y+=j;
					counter++;
				}
				
			}
		} 
		Point[] result=new Point[2];
		Point p1=new Point((int)(x/counter),(int)(y/counter));
		counter=0;
		x=0;y=0;
		for(int i=height/2;i<height;i++)
		{
			for(int j=width/2;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				if(pixels[i][j]==0){
					x+=i;
					y+=j;
					counter++;
				}
				
			}
		} 
		
		Point p2=new Point((int)(x/counter),(int)(y/counter));
		
		result[0]=p1;
		result[1]=p2;
		
		return result;
	
	
	}
	
	public static double[] getTrisurface(BufferedImage image){
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		double x=0,y=0;
		double[] result=new double[3];
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		int counter=0;
		for(int i=0;i<height/3;i++)
		{
			for(int j=0;j<width/3;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				if(pixels[i][j]==0){					
					counter++;
				}
				
			}
		} 
	
		result[0]=counter;///(width*height);


		counter=0;
		for(int i=height/3;i<2*height/3;i++)
		{
			for(int j=width/3;j<2*width/3;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				if(pixels[i][j]==0){					
					counter++;
				}
				
			}
		} 
	
		result[1]=counter;///(width*height);
		
		counter=0;
		for(int i=2*height/3;i<height;i++)
		{
			for(int j=2*width/3;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				if(pixels[i][j]==0){					
					counter++;
				}
				
			}
		} 
	
		result[2]=counter;///(width*height);	
		
		return result;
	
	}
	
	
	
	public static double getNormalizedArea(BufferedImage image){
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		double x=0,y=0;
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		int counter=0;
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				if(pixels[i][j]==0){					
					counter++;
				}
				
			}
		} 
	
		return counter;///(width*height);
		
	}
	
	
	public static Point[] getSixFoldCenter(BufferedImage image){
		int width=image.getWidth();
		int height=image.getHeight();
		
		
		int[][] pixels=new int[height][width];
		
		Raster raster=image.getRaster();
	
		double x=0,y=0;
		////System.out.println("height is "+height);
		////System.out.println("width is "+width);
		Point[] result=new Point[6];
		
		int counter=0;
		for(int k=1;k<=6;k++){
			counter=0;
			x=0;
			y=0;
			for(int i=(k-1)*(height/6);i<k*height/6;i++)
			{
				for(int j=(k-1)*(width/6);j<k*width/6;j++)
				{
					
					pixels[i][j]=raster.getSample(j,i,0);
					if(pixels[i][j]==0){
						x+=i;
						y+=j;
						counter++;
					}
					
				}
			} 
			Point p=new Point((int)(x/counter),(int)(y/counter));
			result[k-1]=p;
		}
		
		return result;
	
	
	}
	
	
}
