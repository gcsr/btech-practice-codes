import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FindClusters
{
	int height;
	int width;

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//new DrawHistogram("");

	}
	BufferedImage image;
	double[][][] pixels;
	
	int number;
	
	public FindClusters(String fileName,int number)
	{
			
		this.number=number;
		image=getImage(fileName);
		Raster raster=image.getRaster();
				
		height=image.getHeight();
		width=image.getWidth();
		
		pixels=new double[height][width][3];
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				int color=raster.getSample(j,i,0);
				pixels[i][j][0]=(color & 0x00ff0000) >> 16;
				pixels[i][j][1]=(color & 0x0000ff00) >> 8;
				pixels[i][j][2]= (color & 0x000000ff);
				
			}
		}
		
		System.out.println("constructor is ending");
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
	
	
	public void findClusters(int size)
	{
		
		
		Cluster[] clusters=new Cluster[size];
		
		int hor=width/3;
		int vert=(height)/(number/2+1);
		
		int x,y;
		x=hor;
		y=vert;
		int i=0;
		
		while(true)
		{
			clusters[i]=new Cluster(pixels[y][x],width*height);i++;if(i==number)break;
			clusters[i]=new Cluster(pixels[y][x+hor],width*height);i++;if(i==number)break;
			clusters[i]=new Cluster(pixels[y][x+hor+hor],width*height);i++;if(i==number)break;			
			y+=vert;			
		}
		
		for(int j=0;j<height;j++)
		{
			for(int k=0;k<width;k++)
			{
				int min=0;
				double diff=difference(clusters[0].values,pixels[j][k]);
				for(int l=1;l<size;l++)
				{
					if(difference(pixels[j][k],clusters[i].values)<diff)
						min=i;
				}
			}
		}
	}
	
	private double difference(double[] x,double[] y)
	{
		double diff=(x[0]-y[0])*(x[0]-y[0])+(x[1]-y[1])*(x[1]-y[1])+(x[2]-y[2])*(x[2]-y[2]);
		return diff;
	}
	
	
}
