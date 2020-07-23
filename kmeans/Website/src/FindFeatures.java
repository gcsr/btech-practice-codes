import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class FindFeatures {
	
	int[][] grabbedPixels;
	private  static String defaultImage="D:/male/9326871/9326871.1.jpg";
	BufferedImage image;
	Raster raster;
	
	class Max{
		int no;
		int std;
		Max(int no,int std){
			this.no=no;
			this.std=std;
		}
	}
	
	class BlockComparator implements Comparator<Max>{
		
		public int compare(Max arg0, Max arg1) {
			// TODO Auto-generated method stub
			return (int)(arg1.std-arg0.std);
			
		}
		
	}
	
	List<Max> blocks=new LinkedList<Max>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new FindFeatures();
		
	}
	
	public FindFeatures(BufferedImage image){
		
		System.out.println(image.getType());
		this.image=image;
		raster=image.getRaster();
		
	}
	
	public BufferedImage getBlockWithHighSTD(int width,int height){
		
		int imageHeight=image.getHeight();
		int imageWidth=image.getWidth();
		//image.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
		int widthNo=imageWidth/width;
		int heightNo=imageHeight/height;
		
		System.out.println("no of blocks are "+widthNo*heightNo);
		
		grabbedPixels=new int[widthNo*heightNo][width*height];
		
		int block=0;
		try{
			for(int j=0;j<heightNo;j++){
				for(int i=0;i<widthNo;i++){			
					/*PixelGrabber grabber=new PixelGrabber(image,i*width,j*height,width,
							height,grabbedPixels[block],0,width);*/
					//PixelGrabber grabber=new PixelGrabber(image,i*width,j*height,width,height,false);
					//grabber.grabPixels();
					
					grabPixels(block,i*width,j*height,width,height);
					//grabbedPixels[block]=(int[])grabber.getPixels();
					//System.out.println(grabbedPixels[block]);
					block++;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(1);
		}
		
		int blockNo=0;
		int stdDev=-12;
		for(int i=0;i<grabbedPixels.length;i++){
			printPixels(i);
			System.out.println();
			
			int calc=calcStd(grabbedPixels[i]);//count(grabbedPixels[i]);//
			
			blocks.add(new Max(i,calc));
			
			if(calc>stdDev){
				blockNo=i;
				stdDev=calc;
			}
		}
		
		Collections.sort(blocks,new BlockComparator());
		
		for(int ip=0;ip<5;ip++){
			
			blockNo=(int)blocks.get(ip).no;
		
			int heightBlock=blockNo/widthNo;
			int widthBlock=blockNo%widthNo;
			
			int posy=heightBlock*height;
			int posx=widthBlock*width;
			
			int[] pp=new int[grabbedPixels[blockNo].length];
			
			System.out.println("hi std block no is "+blockNo);
			for(int i=0;i<pp.length;i++){
				pp[i]=255;
			}		
		
			image.setRGB(posx,posy, width, height,pp,0,width);
		}	
		return image;
		//return blockNo;
		
		//System.out.println("no of blocks are "+block);
		
		
	}
	
	private void grabPixels(int block,int curWidth,int curHeight,int width,int height){
		
		int pixelNo=0;
		
		grabbedPixels[block]=new int[width*height];
		
		for(int i=curHeight;i<(curHeight+height);i++){
			for(int j=curWidth;j<(curWidth+width);j++){
				
				grabbedPixels[block][pixelNo]=raster.getSample(j,i,0);
				pixelNo++;
			}
		}
	}
	
	
	private int count(int[] nums){
		
		int count=0;
		for(int x:nums){
			if(x<50)
				count++;
		}
		
		return count;
	}

	
	private int calcStd(int[] num)
	{
		int sum=0;
		for(int x:num){
			sum+=x;
		}
		return sum;
		/*int avg=sum/num.length;
		
		int stdNum=0;
		
		for(int x:num)
			stdNum+=((x-avg)*(x-avg));
		
		return (int)Math.sqrt(stdNum/(num.length));*/
	}
	
	public void printPixels(int i){
		for(int x:grabbedPixels[i]){
			System.out.print(" "+x);
		}
	}

}
