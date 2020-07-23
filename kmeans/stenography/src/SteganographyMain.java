import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SteganographyMain {
	
	CannyEdgeDetector detector;
	String password,binaryPassword;
	int[] edgeData;
	int[] imageData;
	int[] imgPassData;
	int passSize;
	int edgeSize;
	int convertedImgNumber=0;
	char[] convertedImgChar;
	BufferedImage edges;
	public static void main(String[] gcs){
		/*if(gcs.length!=1){
			System.out.println("usage is java SteganographyMain password");
			return;
		}*/
		new SteganographyMain("D:/dwt/BGR1.JPG","password");
	}
	
	public SteganographyMain(String imageName,String pass){
		
		this.password=pass;
		String binaryPassword=PassToBinary.getBinary(pass);
		convertedImgChar=binaryPassword.toCharArray();
		passSize=binaryPassword.length();
		edgeSize=passSize/2;
		try{
				
				BufferedImage img = null;
				try{
				    img = ImageIO.read(new File(imageName));
				}
				catch (IOException e) {
				}
				
				detector = new CannyEdgeDetector();
				//adjust its parameters as desired 
				detector.setLowThreshold(0.5f); 
				detector.setHighThreshold(1f); 
				//apply it to an image 
				detector.setSourceImage(img); 
				detector.process();
				edgeData=detector.getEdgeData();
				imageData=detector.getImagePixels();
				for(int i=0;i<100;i++){
					System.out.print(" "+imageData[i]);
					if(i%10==0)
					System.out.println();
				}
				imgPassData=imageData;
				edges = detector.getEdgesImage();
				
				
			}catch(Exception ex){
				ex.printStackTrace();
				System.exit(0);
			}
		
	}
	
	private void putPassIntoImage(){
		
		int i=0,j=0;
		while(i<edgeSize){
			if(edgeData[j]==-1){
				processPixel(j);
				i+=2;
			}
			j++;
		}
	}
	
	public BufferedImage getEmbeddedImage(){
		putPassIntoImage();
		setEmbeddedImage();
		detector.process();
		return detector.getEdgesImage();
	}
	
	
	public void getEdgeAfterEmbedding(){
		//detector.setS
	}
	
	public BufferedImage getEdgesImage(){
		
		return edges;
	}
	
	public void setEmbeddedImage(){
		
		detector.setSourceImage(imgPassData);
	}
	
	private void processPixel(int j){
		
		int r = (imageData[j] & 0xff0000) >> 16;
		int g = (imageData[j] & 0xff00) >> 8;
		int b = imageData[j] & 0xff;
		
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
			if(bValue==1)bValue+=1;
			else bValue-=1;
		}
		
		imgPassData[j]=65536*r+256*g+b;
		
	}
	
	private int xor(int i,int j){
		
		return i==j?1:0;
	}

}
