import java.awt.Image;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;


public class FindGreyCluster
{
	int height;
	int width;
	GreyCluster[] greyClusters;

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//new DrawHistogram("");

	}
	BufferedImage image;
	double[][] pixels;
	double[][] memb,belong;
	
	
	Conflict[] conflicts;
	
	
	int conflictNumber;
	int size;
	Raster raster;
	
	String fileName;
	public FindGreyCluster(String fileName,int size)
	{
		this.fileName=fileName;	
		this.size=size;
		image=getImage(fileName);
		System.out.println("image type is "+image.getType());
		image=getConvertedGrayscaleImage(image);

		raster=image.getRaster();
		
		height=image.getHeight();
		width=image.getWidth();
		
		pixels=new double[height][width];
		memb=new double[height][width];
		belong=new double[height][width];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				pixels[i][j]=raster.getSample(j,i,0);
				
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
	
	private BufferedImage getConvertedGrayscaleImage(BufferedImage source) 
	{
		BufferedImageOp op = new ColorConvertOp(
		ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
	}
	
	
	public int findGreyClusters()
	{
		greyClusters=new GreyCluster[size];
		int hor=width/3;
		int vert=(height)/(size/2+size%2+1);
		
		int x,y;
		x=hor;
		y=vert;
		int i=0;
		int po=255/size;
		
		double preSum=0;
		while(true)
		{			
			
			
			System.out.println(y+"  "+x);
			greyClusters[i]=new GreyCluster(pixels[y][x],width*height);i++;if(i==size)break;
			System.out.println(y+"  "+(x+hor));
			greyClusters[i]=new GreyCluster(pixels[y][x+hor],width*height);i++;if(i==size)break;			
			y+=vert;			
						
		}
		Arrays.sort(greyClusters,new ClusterCleaner());
		int less=0;
		for(int j=0;j<(greyClusters.length-1);j++)
		{
			if(greyClusters[j].center==greyClusters[j+1].center)
			{
				less++;
				greyClusters[j]=null;
			}
		}
		
		GreyCluster[] newClusters=new GreyCluster[size-less];
		int jj=0,kk=0;
		while(jj<size)
		{
			if(greyClusters[jj]!=null)
			{
				newClusters[kk]=greyClusters[jj];
				jj++;
				kk++;
			}
			
			else
				jj++;
		}
		
		System.out.println("empty clusters are "+less);
		size=size-less;
		
		greyClusters=newClusters;
		
		
		
		int pk=0;
		
		while(true)
		{	
			//pk++;
			
			for(int j=0;j<height;j++)
			{
				for(int k=0;k<width;k++)
				{
					int min=0;
					double diff=(pixels[j][k]-greyClusters[0].center)*(pixels[j][k]-greyClusters[0].center);
					
					for(int l=1;(l<size);l++)
					{
						double curDiff=(pixels[j][k]-greyClusters[l].center)*(pixels[j][k]-greyClusters[l].center);
						
					    if(curDiff<diff)
						{
							diff=curDiff;
							min=l;
						}
						
					}
					
					double nume=diff;
					
					double denSum=0;
					for(GreyCluster xx:greyClusters)
					{
						denSum+=((pixels[j][k]-xx.center)*(pixels[j][k]-xx.center));
					}
					
					denSum-=nume;
					nume=(nume)*(size-1);
					
					if(nume==0 || denSum==0)
						memb[j][k]=1;
					
					
					else
					memb[j][k]=(denSum/nume);
					
											
					//System.out.print(memb[j][k]);
					
					//double pre=belong[j][k];
					belong[j][k]=greyClusters[min].center/memb[j][k];
					//double e=belong[j][k]-pre;						                                                                                                                                                                                                                                                                          
					greyClusters[min].add(pixels[j][k],memb[j][k], belong[j][k], new Point(j,k));
					
				}
			}
			
			double[] centers=new double[size];
			int ppp=0;
			for(GreyCluster gc:greyClusters)
			{
				System.out.println("size of the cluster is "+gc.size);
				centers[ppp]=gc.center;
				ppp++;
			}
			
			for(GreyCluster gc:greyClusters)
				gc.calcCenter();;
			
			double totalDiff=0;
			
			for(int jkp=0;jkp<size;jkp++){
				System.out.println("line before diff calculation "+centers[jkp] );
				totalDiff+=(centers[jkp]-greyClusters[jkp].center);
				System.out.println("difference is "+totalDiff);
			}
			
			System.out.println("difference is "+totalDiff);
			
			if(Math.abs(totalDiff)<1)
				return size;
			
			Arrays.sort(greyClusters,new ClusterCleaner());
			less=0;
			for(int j=0;j<(greyClusters.length-1);j++)
			{
				if(greyClusters[j].center==greyClusters[j+1].center)
				{
					less++;
					greyClusters[j]=null;
				}
			}
			
			newClusters=new GreyCluster[size-less];
			jj=0;kk=0;
			while(jj<size)
			{
				if(greyClusters[jj]!=null)
				{
					newClusters[kk]=greyClusters[jj];
					jj++;
					kk++;
				}
				
				else
					jj++;
			}
			
			System.out.println("empty clusters are "+less);
			size=size-less;
			
			greyClusters=newClusters;
			
			
			for(GreyCluster gc:greyClusters)
				gc.reset();
			
		}
		
	}
	
	public BufferedImage returnImage(int cluster)
	{
		
		image=getImage(fileName);
		raster=image.getRaster();
		int[][] clusterImagePixels=new int[height][width];
		List<Point> points=greyClusters[cluster-1].points;
		System.out.println(" cluster points size is"+points.size());
		Iterator iterator=points.iterator();
		
		
		while(iterator.hasNext())
		{
			Point p=(Point)iterator.next();
			image.setRGB(p.y, p.x, 0000);
			//clusterImagePixels[p.x][p.y]=0000;
		}
		
		 /*BufferedImage newImage=new BufferedImage(width, height,image.getType());
		 for(int x=0;x<height;x++)
		 {
	            for(int y=0;y<width;y++)
	            {
	                int pixel=clusterImagePixels[x][y];
	                newImage.setRGB(y,x,pixel);
	                
	                
	            }

		 }*/
		 

		
		
		
		return image;
	}
	
	
	
}
