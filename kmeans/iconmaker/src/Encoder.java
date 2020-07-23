import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Encoder {
	
	CannyEdgeDetector detector;
	String password,binaryPassword;
	int[] edgeData;
	byte[] imageData;	
	int[] imgPassData;
	int passSize;
	int edgeSize;
	int convertedImgNumber=0;	
	char[] convertedImgChar;	
	
	int width,height;
	BufferedImage embeddedImage=null;
	public static void main(String[] gcs){
		/*if(gcs.length!=1){
			//System.out.println("usage is java SteganographyMain password");
			return;
		}*/
		new SteganographyMain("D:/dwt/BGR1.JPG","password");
	}
	
	public BufferedImage getEmbeddedImage(){
		
		
		if (embeddedImage == null) {
			embeddedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}
		
		embeddedImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, imageData);
		return embeddedImage;
	}
	
	public Encoder(String imageName,String pass){
		
		this.password=pass;
		String binaryPassword=PassToBinary.getBinary(pass);
		convertedImgChar=binaryPassword.toCharArray();		
		passSize=binaryPassword.length();
		
		try{
				
				BufferedImage img = null;
				try{
				    img = ImageIO.read(new File(imageName));
				}
				catch (IOException e) {
				}
				
				int type=img.getType();
				width=img.getWidth();
				height=img.getHeight();
				
				detector = new CannyEdgeDetector();
				//adjust its parameters as desired 
				detector.setLowThreshold(0.5f); 
				detector.setHighThreshold(1f); 
				//apply it to an image 
				detector.setSourceImage(img); 
				detector.process();
				
				edgeData=detector.getEdgeData();
				imageData=detector.getImagePixels();
				
				//imageIntData=new int[imageData.length];
				//imgPassData=imageData;
				//edges = detector.getEdgesImage();
				putPassIntoImage();
				
				
			}catch(Exception ex){
				ex.printStackTrace();
				System.exit(0);
			}
		
	}
	
	private void putPassIntoImage(){
		
		int i=0,j=0;
		System.out.println();
		while(i<passSize){
			if(edgeData[j]==-1){
				processPixel(j);
				i+=2;
				System.out.print(j);
			}
			
			j++;
		}
		System.out.println();
	}
	
	
	
	
	private void processPixel(int j){
		
		int offset=j*3;
		 int b = imageData[offset++]& 0xff;;
         int g = imageData[offset++]& 0xff;;
         int r = imageData[offset++]& 0xff;;
        
		
		char data1=convertedImgChar[convertedImgNumber];
		char data2=convertedImgChar[convertedImgNumber+1];
		convertedImgNumber+=2;
		int imgNumber1=(data1=='1')?1:0;
		int imgNumber2=(data2=='1')?1:0;
		
		int rValue=r%2;
		int gValue=g%2;
		int bValue=b%2;
		
		if(imgNumber1==xor(rValue,bValue) && imgNumber2==xor(gValue,bValue) )
			return;
		else if(imgNumber1==xor(rValue,bValue) && imgNumber2!=xor(gValue,bValue)){
			gValue=xor(gValue,0);
			if(gValue==1)g+=1;
			else g-=1;
		}
		
		else if(imgNumber1!=xor(rValue,bValue) && imgNumber2==xor(gValue,bValue)){
			rValue=xor(rValue,0);
			if(rValue==1)r+=1;
			else r-=1;
			
		}
		else{
			bValue=xor(bValue,0);
			if(bValue==1)b+=1;
			else b-=1;
		}
		
		 offset=j*3;
		 imageData[offset++]=(byte)b;
		 imageData[offset++]= (byte)g;
		 imageData[offset++]=(byte)r;
        
		
	}
	
	private int xor(int i,int j){
		
		return i==j?1:0;
	}
	public void printing(){
		System.out.println();
		for(char c:convertedImgChar)
			System.out.print(c);
		System.out.println();
		
	}
	

}
