import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Decoder {
	
	CannyEdgeDetector detector;
	String password,binaryPassword;
	int[] edgeData;
	byte[] imageData;	
	int passSize;	
	int decodedImgNumber=0;	
	String result="";
	
	int width,height;
	BufferedImage embeddedImage=null;
	public Decoder(BufferedImage image,int length){
		
		this.passSize=length*8;
		
		try{
				
				this.embeddedImage=image;
							
			}catch(Exception ex){
				ex.printStackTrace();
				System.exit(0);
			}
		
	}
	
	
	private int xor(int i,int j){
		
		return i==j?1:0;
	}
	
	public String decoded(){
		
		detector = new CannyEdgeDetector();
		//adjust its parameters as desired 
		detector.setLowThreshold(0.5f); 
		detector.setHighThreshold(1f); 
		//apply it to an image 
		detector.setSourceImage(embeddedImage); 
		detector.process();
		
		edgeData=detector.getEdgeData();
		imageData=detector.getImagePixels();
		
		
		int i=0,j=0;
		//edgeSize*=2;
		System.out.println();
		while(i<passSize){
			if(edgeData[j]==-1){
				processDecodePixel(j);
				i+=2;
				System.out.print(j);
			}
			
			j++;
		}	
		System.out.println();
		return result;
		
	}
	
	private void processDecodePixel(int j){
		
		int offset=j*3;
		 int b = imageData[offset++]& 0xff;;
         int g = imageData[offset++]& 0xff;
         int r = imageData[offset++]& 0xff; 
		
		
		int rValue=r%2;
		int gValue=g%2;
		int bValue=b%2;
		
		//decodedImgChar[decodedImgNumber++]=xor(rValue,bValue)==1?'1':'0';
		//decodedImgChar[decodedImgNumber++]=xor(gValue,bValue)==1?'1':'0';
		result+=xor(rValue,bValue);
		result+=xor(gValue,bValue);
		
	}
	
	public String getPassword(){
		return result;
	}
	
	public void printing(){		
		System.out.println(result);
	}
	

}
