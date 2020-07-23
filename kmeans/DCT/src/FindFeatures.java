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
	
	public int[][][] getBlockWithHighSTD(int width,int height){
		
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
					
					grabPixels(block,i*width,j*height,width,height);
					block++;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(1);
		}
		
		int[][][] blockPixels=new int[block][8][8];
		for(int i=0;i<grabbedPixels.length;i++){
			int[] pp=grabbedPixels[i];
			
			for(int j=0;j<pp.length;j++){
				blockPixels[i][j/8][j%8]=pp[j];
			}
		}
		
		return blockPixels;
		
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
	
	
	

}
