import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;




public class Stenographydecoder {
	
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
	
	
	public Stenographydecoder(){
		
	}
	
	public String decode(String fileName,int passSize){
		this.passSize=passSize;
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
	
}
