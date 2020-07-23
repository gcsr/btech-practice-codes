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
		
		int jp=0,kp=0;
		while(jp<size)
		{
			if(greyClusters[jp]!=null)
			{
				newClusters[kp]=greyClusters[jp];
				jp++;
				kp++;
			}
			
			else
				jp++;
		}
		
		System.out.println("empty clusters are "+less);
		size=size-less;
		
		greyClusters=newClusters;
	
		
		double preMse=0;
		
		double mseDiff=100;
		while(true)
		{	
			
			conflicts=new Conflict[height*width];
			
			conflictNumber=0;
			for(int j=0;j<height;j++)
			{
				for(int k=0;k<width;k++)
				{
					int min=0;
					boolean conflict=false;
					double diff=(pixels[j][k]-greyClusters[0].center)*(pixels[j][k]-greyClusters[0].center);
					for(int l=1;(l<size);l++)
					{
						double curDiff=(pixels[j][k]-greyClusters[l].center)*(pixels[j][k]-greyClusters[l].center);
						if(curDiff==diff)
						{	conflicts[conflictNumber]=new Conflict();
							conflicts[conflictNumber].value=diff;
							conflictNumber++;
							conflict=true;
							break;
													
						}
						else if(curDiff<diff)
						{
							diff=curDiff;
							min=l;
						}
						
					}
					
					
					
					if(conflict!=true)
					{
						//System.out.print(min);
						greyClusters[min].add(diff,new Point(j,k));
						greyClusters[min].sum+=pixels[j][k];
					}
					
				}
			}
			System.out.println("conflict number is "+conflictNumber);
			
			for(GreyCluster clust:greyClusters)
			{
				System.out.println(clust.fitness);
							
			}
			Arrays.sort(greyClusters,new ClusterComparator());
			
			for(GreyCluster clust:greyClusters)
			{
				System.out.println(clust.fitness);
							
			}
			
			if(greyClusters[0].fitness==0)
			{
				int z=1;
				while(z<size && greyClusters[z].fitness==0)
				{
					z++;
				}
				
				int j=0;
				int cNumber=0;
				while(cNumber!=conflictNumber)
				{
					greyClusters[j].add(conflicts[cNumber]);
					j++;
					cNumber++;
					if(j==z)
						j=0;
				}
			}
			
			else
			{
				int j=0;
				int k=0;
				while(j<conflictNumber)
				{
					greyClusters[k].add(conflicts[j]);
					j++;
					if(k==size)
						k=0;
				}
			}
			
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
			
			int j=0,k=0;
			while(j<size)
			{
				if(greyClusters[j]!=null)
				{
					newClusters[k]=greyClusters[j];
					j++;
					k++;
				}
				
				else
					j++;
			}
			
			System.out.println("empty clusters are "+less);
			size=size-less;
			
			greyClusters=newClusters;
			
			int sum=0;			
			double mse=0;	
			
			for(GreyCluster clust:greyClusters)
			{
				//System.out.println(clust.fitness);
				sum+=clust.size;
				mse+=clust.fitness;
				
			}
			
			
			mse=mse/(width*height);
			mseDiff=mse-preMse;
			preMse=mse;
			
			sum+=conflictNumber;
			//System.out.println("end check conflict number is "+conflictNumber);
			System.out.println("end check height is "+height+" width is "+width  +"  "+(height*width)+"  = "+sum);
			System.out.println("mse differnce is "+mseDiff);
			
			if(Math.abs(mseDiff)<.1)
			{
				break;
			}
			else
			{
				for(GreyCluster clust:greyClusters)
				{
					clust.reset();
				}
			}

		
		}
		return size;
	}
	
	public BufferedImage returnImage(int cluster)
	{
		
		image=getImage(fileName);
		raster=image.getRaster();
		int[][] clusterImagePixels=new int[height][width];
		
		
		/*for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				clusterImagePixels[i][j]=raster.getSample(j,i,0);
				
			}
		}*/
		
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
