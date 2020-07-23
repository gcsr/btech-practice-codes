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


public class Exponential 
{
	//private static final int height=200;
	//private static final int width=180;
	private static final int hLength=20;
	private static final int wLength=18;
	int width,height;
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//new DrawHistogram("");

	}
	BufferedImage greyImage;
	int[][] pixels;
	int[][] pixelsLBP;
	int[][] inkoPixels;
	int[][][] histogram;
	
	
	
	public Exponential(String fileName,boolean findSub)
	{
			
		
		greyImage=getImage(fileName);
		//greyImage=greyImage.getSubimage(40,50, greyImage.getWidth()-80, greyImage.getHeight()-70);
		

		System.out.println("type is "+greyImage.getType());
		greyImage=getConvertedGrayscaleImage(greyImage);
		
		
		if(findSub)
			subImage();
		
		System.out.println(greyImage.getType());
		Raster raster=greyImage.getRaster();
		height=greyImage.getHeight();
		width=greyImage.getWidth();
		pixels=new int[height][width];
		pixelsLBP=new int[height][width];
		
		System.out.println("height is "+height);
		System.out.println("width is "+width);
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				
			}
		}
		
		System.out.println("constructor is ending");
	}
		
	private BufferedImage getConvertedGrayscaleImage(BufferedImage source) {
		BufferedImageOp op = new ColorConvertOp(
		ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
	}
	
private void subImage(){
		
		greyImage=greyImage.getSubimage(40,50,100,120);
		
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
	
	public void findLBPHistogramValues()
	{
		
		
		//need to implement small changes
		
		
		histogram=new int[10][10][3];
		int height=this.height-1;
		int width=this.width-1;
		
		System.out.println("height is "+height);
		
		//first row first column
		int i=0;
		
		int diff=30;
		
		i+=Math.abs(pixels[0][1]-pixels[0][0])>diff?1:0;
		i+=Math.abs(pixels[1][1]-pixels[0][0])>diff?1:0;
		i+=	Math.abs(pixels[1][0]-pixels[0][0])>diff?1:0;	
		pixelsLBP[0][0]=(int)(Math.pow(2, i)-1);
		if(pixelsLBP[0][0]>64)
			histogram[0][0][2]++;
		else
		if(pixelsLBP[0][0]>8)
			histogram[0][0][1]++;
		else
			histogram[0][0][0]++;

		
		//first row last column
		i=0;
		i+=Math.abs(pixels[1][width]-pixels[0][width])>diff?1:0;
		i+=Math.abs(pixels[1][width-1]-pixels[0][width])>diff?1:0;
		i+=Math.abs(pixels[0][width-1]-pixels[0][width])>diff?1:0;				
		pixelsLBP[0][width]=(int)(Math.pow(2, i)-1);
		if(pixelsLBP[0][width]>64)
			histogram[0][width/wLength][2]++;
		else
		if(pixelsLBP[0][width]>8)
			histogram[0][width/wLength][1]++;
		else
			histogram[0][width/wLength][0]++;

		
		//last row first column
		i=0;
		i+=Math.abs(pixels[height-1][0]-pixels[height][0])>diff?1:0;
		i+=Math.abs(pixels[height-1][1]-pixels[height][0])>diff?1:0;
		i+=Math.abs(pixels[height][1]-pixels[height][0])>diff?1:0;
		pixelsLBP[height][0]=(int)(Math.pow(2, i)-1);;
		
		if(pixelsLBP[height][0]>64)
			histogram[height/hLength][0][2]++;
		else
		if(pixelsLBP[height][0]>8)
			histogram[height/hLength][0][1]++;
		else
			histogram[height/hLength][0][0]++;

		
		//last row last column
		i=0;
		System.out.println(pixels[height][width]);
		i+=Math.abs(pixels[height-1][width-1]-pixels[height][width])>diff?1:0;
		i+=Math.abs(pixels[height-1][width]-pixels[height][width])>diff?1:0;
		i+=Math.abs(pixels[height][width-1]-pixels[height][width])>diff?1:0;
		pixelsLBP[height][width]=(int)(Math.pow(2, i)-1);;
		
		
		if(pixelsLBP[height][width]>64)
			histogram[height/hLength][width/wLength][2]++;
		else
		if(pixelsLBP[height][width]>8)
			histogram[height/hLength][width/wLength][1]++;
		else
			histogram[height/hLength][width/wLength][0]++;

		
		//first row
		int f=1;
		while(f<width)
		{
			int center=pixels[0][f];
			i=0;
			i+=Math.abs(pixels[0][f+1]-center)>diff?1:0;
			i+=Math.abs(pixels[1][f+1]-center)>diff?1:0;
			i+=Math.abs(pixels[1][f]-center)>diff?1:0;
			i+=Math.abs(pixels[1][f-1]-center)>diff?1:0;
			i+=Math.abs(pixels[0][f-1]-center)>diff?1:0;
			pixelsLBP[0][f]=(int)(Math.pow(2, i)-1);;
			
			
			if(pixelsLBP[0][f]>64)
				histogram[0][f/wLength][2]++;
			else
			if(pixelsLBP[0][f]>8)
				histogram[0][f/wLength][1]++;
			else
				histogram[0][f/wLength][0]++;

			
			f++;
		}
		
		
		
		//last row
		f=1;
		while(f<width)
		{
			int center=pixels[height][f];
			i=0;
			i+=Math.abs(pixels[height-1][f-1]-center)>diff?1:0;
			i+=Math.abs(pixels[height-1][f]-center)>diff?1:0;
			i+=Math.abs(pixels[height-1][f+1]-center)>diff?1:0;
			i+=Math.abs(pixels[height][f+1]-center)>diff?1:0;
			i+=Math.abs(pixels[height][f-1]-center)>diff?1:0;
			
			pixelsLBP[height][f]=(int)(Math.pow(2, i)-1);;
			
			if(pixelsLBP[height][f]>64)
				histogram[height/hLength][f/wLength][2]++;
			else
			if(pixelsLBP[height][f]>8)
				histogram[height/hLength][f/wLength][1]++;
			else
				histogram[height/hLength][f/wLength][0]++;

			
			f++;
		}
		
		
		//left column
		f=1;
		while(f<height)
		{
			int center=pixels[f][0];
			i=0;
			i=Math.abs(pixels[f-1][0]-center)>diff?1:0;
			i+=Math.abs(pixels[f-1][1]-center)>diff?1:0;
			i+=Math.abs(pixels[f][1]-center)>diff?1:0;
			i+=Math.abs(pixels[f+1][1]-center)>diff?1:0;
			i+=Math.abs(pixels[f+1][0]-center)>diff?1:0;
			
			pixelsLBP[f][0]=(int)(Math.pow(2, i)-1);;
			
			if(pixelsLBP[f][0]>64)
				histogram[f/hLength][0][2]++;
			else
			if(pixelsLBP[f][0]>8)
				histogram[f/hLength][0][1]++;
			else
				histogram[f/hLength][0][0]++;

			
			f++;
		}
		
		
		
		//right column
		f=1;
		while(f<height)
		{
			int center=pixels[f][width];
			i=0;
			i=Math.abs(pixels[f-1][width-1]-center)>diff?1:0;
			i+=Math.abs(pixels[f-1][width]-center)>diff?1:0;
			i+=Math.abs(pixels[f+1][width]-center)>diff?1:0;
			i+=Math.abs(pixels[f+1][width-1]-center)>diff?1:0;
			i+=Math.abs(pixels[f][width-1]-center)>diff?1:0;
			
			pixelsLBP[f][width]=(int)(Math.pow(2, i)-1);
			
			if(pixelsLBP[f][width]>64)
				histogram[f/hLength][width/wLength][2]++;
			else
			if(pixelsLBP[f][width]>8)
				histogram[f/hLength][width/wLength][1]++;
			else
				histogram[f/hLength][width/wLength][0]++;

			f++;
		}
		
		
		
		
		for(int z=1;z<height;z++)
		{
			for(int j=1;j<width;j++)
			{
				int center=pixels[z][j];
				i=0;
				i+=Math.abs(pixels[z-1][j-1]-center)>diff?1:0;
				i+=Math.abs(pixels[z-1][j]-center)>diff?1:0;
				i+=Math.abs(pixels[z-1][j+1]-center)>diff?1:0;
				i+=Math.abs(pixels[z][j+1]-center)>diff?1:0;
				i+=Math.abs(pixels[z+1][j+1]-center)>diff?1:0;
				i+=Math.abs(pixels[z+1][j]-center)>diff?1:0;
				i+=Math.abs(pixels[z+1][j-1]-center)>diff?1:0;
				i+=Math.abs(pixels[z][j-1]-center)>diff?1:0;
				
				pixelsLBP[z][j]=(int)(Math.pow(2, i)-1);;
				
				if(pixelsLBP[z][j]>64)
					histogram[z/hLength][j/wLength][2]++;
				else
				if(pixelsLBP[z][j]>8)
					histogram[z/hLength][j/wLength][1]++;
				else
					histogram[z/hLength][j/wLength][0]++;

						
			}
		}
		
		System.out.println("finding completed");
		
				
	}
	
	public int[][] getFeaturedPixels()
	{
		int[][] featuredPixels=pixelsLBP;
		
		for(int i=0;i<height;i++)
		{
			System.out.println("in featured loop");
			
			for(int j=0;j<width;j++)
			{
				if(featuredPixels[i][j]<255)
					featuredPixels[i][j]=0;
			}
		}
		return featuredPixels;
	}
	
	public int[][] getLBPValues()
	{
		return pixelsLBP;
	}
	
	public int[][][] getHistogramValues()
	{
		return histogram;
	}
	
	public int[][] getInkoPixels()
	{
		return inkoPixels;
	}
	
	public int[][] getGScalePixels()
	{
		return pixels;
	}

}
