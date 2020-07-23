import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class SteganographyMain {
	
	CannyEdgeDetector detector;
	String password,binaryPassword;
	int[] edgeData;
	byte[] imageData;
	String fileName;
	int[] imgPassData;
	int passSize;
	int edgeSize;
	int convertedImgNumber=0;
	int decodedImgNumber=0;
	char[] convertedImgChar;
	char[] decodedImgChar;
	String result="";
	BufferedImage edges;
	int width,height;
	BufferedImage embeddedImage=null;
	
	public SteganographyMain(){
		
	}
	public static void main(String[] gcs){
		/*if(gcs.length!=1){
			//System.out.println("usage is java SteganographyMain password");
			return;
		}*/
		new SteganographyMain("D:/BGR3.JPG","password");
	}
	
	public BufferedImage getEmbeddedImage(){
		
		
		if (embeddedImage == null) {
			embeddedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}
		
		embeddedImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, imageData);
		
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result2=fileChooser.showSaveDialog(null);
			System.out.println("after save dilalog");
			if(result2==JFileChooser.CANCEL_OPTION)
				return null;
			
			
			try{	  	  				
  				int width=edges.getWidth();
  				int height=edges.getHeight();  				
  				FileOutputStream fout=new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath()+"browse");
  				fout.write(imageData);
				fout.close();				
  				  		
				System.out.println("writing");
  				//middlePanel.updateImage(coverImage);	  
			
				BufferedImage coverImage=new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);	  					     
				coverImage.getWritableTile(0, 0).setDataElements(0, 0, width, height,imageData);
				//middlePanel.updateImage(coverImage); 
			
				File outputfile = new File(fileChooser.getSelectedFile().getAbsolutePath()+".jpg");
		    
				ImageIO.write(coverImage, "jpg", outputfile);
			}catch(Exception ex){
		  		ex.printStackTrace();
			}
				
	
		return embeddedImage;
	}
	
	public SteganographyMain(String imageName,String pass){
		
		this.password=pass;
		String binaryPassword=PassToBinary.getBinary(pass);
		convertedImgChar=binaryPassword.toCharArray();
		decodedImgChar=new char[convertedImgChar.length];
		passSize=binaryPassword.length();
		edgeSize=passSize;
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
				detector.setLowThreshold(0.75f); 
				detector.setHighThreshold(1f); 
				//apply it to an image 
				detector.setSourceImage(img); 
				detector.process();
				
				edgeData=detector.getEdgeData();
				imageData=detector.getImagePixels();
				
				//imageIntData=new int[imageData.length];
				//imgPassData=imageData;
				edges = detector.getEdgesImage();
				new DisplayFrame(edges);
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
				System.out.print(j+" ");
			}
			
			j++;
		}
		System.out.println();
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
	
	public String decoded(String fileName){
		BufferedImage imge;
		BufferedImage finalImage=null;;
		try{
		
			imge= ImageIO.read(new File(fileName));
			//System.out.println(fileName);
			String decodeFileName=fileName.substring(0,fileName.lastIndexOf("."))+"browse";
			//System.out.println(decodeFileName);
			
			try{
			FileInputStream fin=new FileInputStream(decodeFileName);		
			int offset=0;
			
			int fff=imge.getWidth()*imge.getHeight()*3;
			
			byte[] data = new byte[fff];
			int bytesRead = 0;
			
			while (offset < fff) {					
				bytesRead = fin.read(data, offset, data.length-offset);
				if (bytesRead == -1) break;
				offset += bytesRead;
			}
			fin.close();
			
			finalImage = new BufferedImage(imge.getWidth(),imge.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			finalImage.getWritableTile(0, 0).setDataElements(0, 0,imge.getWidth(),imge.getHeight(), data);
			}
			catch(Exception ex){
				finalImage=imge;
			}
		
		CannyEdgeDetector detector = new CannyEdgeDetector();
		//adjust its parameters as desired 
		detector.setLowThreshold(0.5f); 
		detector.setHighThreshold(1f); 
		//apply it to an image 
		detector.setSourceImage(finalImage); 
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
				System.out.print(j+" ");
			}
			
			
			
			j++;
		}	
		System.out.println();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
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
		
		decodedImgChar[decodedImgNumber++]=xor(rValue,bValue)==1?'1':'0';
		decodedImgChar[decodedImgNumber++]=xor(gValue,bValue)==1?'1':'0';
		result+=xor(rValue,bValue);
		result+=xor(gValue,bValue);
		
	}
	
	public void printing(){
		System.out.println();
		for(char c:convertedImgChar)
			System.out.print(c);
		System.out.println();
		for(char c:decodedImgChar)
			System.out.print(c);
		System.out.println();
	}
	

}
