import java.awt.color.ColorSpace;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;

public class StandardDeviation {
		private static final int height=200;
		private static final int width=180;
		private static final int hLength=20;
		private static final int wLength=18;

		public static void main(String[] args) 
		{
			// TODO Auto-generated method stub
			//new DrawHistogram("");

		}
		BufferedImage greyImage;
		int[][] pixels;
		int[][] stdDeviations=new int[height][width];
		
		public StandardDeviation(String fileName)
		{
				
			
			greyImage=getImage(fileName);
			System.out.println("type is "+greyImage.getType());
			greyImage=getConvertedGrayscaleImage(greyImage);
			
			System.out.println(greyImage.getType());
			Raster raster=greyImage.getRaster();
			
						
			pixels=new int[height][width];
			for(int i=0;i<height;i++)
			{
				for(int j=0;j<width;j++)
				{
					
					pixels[i][j]=raster.getSample(j,i,0);
					
				}
			}
			
			System.out.println("constructor is ending");
		}
		
		
		private BufferedImage getConvertedGrayscaleImage(BufferedImage source) 
		{
			BufferedImageOp op = new ColorConvertOp(
			ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
			return op.filter(source, null);
			
		}
		
		
		
		private BufferedImage getImage(String fileName)
		{
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
		
		public int[][] getGreyPixels(){
			return pixels;
		}
		
		public void findStandardDeviations(){
			
			
			for(int i=5;i<height-5;i++){
				
				for(int j=10;j<(width-10);j++){
					
										
					int[] neighbours=new int[25];
					
					neighbours[0]=Math.abs(pixels[i-1][j-1]-pixels[i][j]);
					neighbours[1]=Math.abs(pixels[i-1][j]-pixels[i][j]);;
					neighbours[2]=Math.abs(pixels[i-1][j+1]-pixels[i][j]);;
					neighbours[3]=Math.abs(pixels[i][j+1]-pixels[i][j]);;
					neighbours[4]=Math.abs(pixels[i+1][j+1]-pixels[i][j]);;
					neighbours[5]=Math.abs(pixels[i+1][j]-pixels[i][j]);;
					neighbours[6]=Math.abs(pixels[i+1][j-1]-pixels[i][j]);;
					neighbours[7]=Math.abs(pixels[i][j-1]-pixels[i][j]);;
					neighbours[8]=Math.abs(pixels[i][j]-pixels[i][j]);;
					neighbours[9]=Math.abs(pixels[i-2][j-2]-pixels[i][j]);;
					neighbours[10]=Math.abs(pixels[i-2][j-1]-pixels[i][j]);;
					neighbours[11]=Math.abs(pixels[i-2][j]-pixels[i][j]);;
					neighbours[12]=Math.abs(pixels[i-2][j+1]-pixels[i][j]);;
					neighbours[13]=Math.abs(pixels[i-2][j+2]-pixels[i][j]);;
					neighbours[14]=Math.abs(pixels[i-1][j-2]-pixels[i][j]);;
					neighbours[15]=Math.abs(pixels[i-1][j+2]-pixels[i][j]);;
					neighbours[16]=Math.abs(pixels[i][j-2]-pixels[i][j]);;
					neighbours[17]=Math.abs(pixels[i][j+2]-pixels[i][j]);;
					neighbours[18]=Math.abs(pixels[i+1][j-2]-pixels[i][j]);;
					neighbours[19]=Math.abs(pixels[i+1][j+2]-pixels[i][j]);;
					neighbours[20]=Math.abs(pixels[i+2][j-2]-pixels[i][j]);;
					neighbours[21]=Math.abs(pixels[i+2][j-1]-pixels[i][j]);;
					neighbours[22]=Math.abs(pixels[i+2][j]-pixels[i][j]);;
					neighbours[23]=Math.abs(pixels[i+2][j+1]-pixels[i][j]);;
					neighbours[24]=Math.abs(pixels[i+2][j+2]-pixels[i][j]);;
					
					
					stdDeviations[i][j]=calcStd(neighbours);
					
				}
			}
					
		}
		
		private int calcStd(int[] num)
		{
			int sum=0;
			for(int x:num)
				sum+=x;
			int avg=sum/num.length;
			
			int stdNum=0;
			
			for(int x:num)
				stdNum+=((x-avg)*(x-avg));
			
			return (int)Math.sqrt(stdNum/(num.length));
		}
		
		public int[][] deviatedPixels()
		{
			return pixels;
		}
		
		
		
		public BufferedImage getGreyImage(){
			return greyImage;
		}
		
	


}
