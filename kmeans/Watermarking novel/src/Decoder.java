import java.awt.Graphics;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Decoder {
	
	int width,height;
	BufferedImage LL,LH,HH,HL,newImage;
	int p;
	byte[] greyBytePixels;
	byte[] coverPixels;
	int logoWidth;
	int logoHeight;
	byte[][] finalSub;
	byte[] resultPixels;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Decoder(BufferedImage originalImage,byte[] coverPixels,int logoWidth,int logoHeight){
			BufferedImage img=GetGreyImageData.getConvertedGrayscaleImage(originalImage);
		    System.out.println("image height is"+img.getHeight() );
		    System.out.println("image width is"+img.getWidth() );
		    System.out.println("logo height is"+logoHeight );
		    System.out.println("logo width is"+logoWidth );
		    this.logoWidth=logoWidth;
		    this.logoHeight=logoHeight;
		    width=img.getWidth();
			height=img.getHeight();
			this.coverPixels=coverPixels;
			System.out.println("cover pixels length is "+coverPixels.length);
		    greyBytePixels = (byte[]) img.getData().getDataElements(0, 0,width,height, null);
		    
		    byte[] imagePixels=greyBytePixels;
				//System.out.println("pixels size is "+pixels.length);
				HaarFilter haar=new HaarFilter(width);
				int[] greyPixels;
			     greyPixels=haar.filter(greyBytePixels,null);
			     //byte[] ppp=haar.invert(greyPixels,null);
			     
			     
			   for(int i=0;i<greyPixels.length;i++){
			    	 greyBytePixels[i]=(byte)greyPixels[i];
			     }
			     
			    BufferedImage greyImage=new BufferedImage(width,height, BufferedImage.TYPE_BYTE_GRAY);	  					     
			    greyImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, greyBytePixels);
			    
			   //greyImage.getWritableTile(0, 0).setDataElements(0, 0, width, height,ppp);
			    //greyImage.
			    finalSub=new byte[logoWidth*logoHeight][6];
			    p=width/2;
			    LL=greyImage.getSubimage(0, 0,p,p);
			    System.out.println("width is "+LL.getWidth()+"height is "+LL.getHeight());
			    HL=greyImage.getSubimage(p, 0,p,p);
			    System.out.println("width is "+HL.getWidth()+"height is "+HL.getHeight());
			    LH=greyImage.getSubimage(0,p, p,p);
			    System.out.println("width is "+LH.getWidth()+"height is "+LH.getHeight());
			    HH=greyImage.getSubimage(p,p,p,p);
			    System.out.println("width is "+HH.getWidth()+"height is "+HH.getHeight());
			    
			findImage();
	}
	
	private void findImage(){
		
		firstSub();
		secondSub();
		thirdSub();
		fourthSub();
		results();
		
	}
	
	private void firstSub(){
		System.out.println("in  process subimages");
		byte[] pixels =(byte[]) LL.getData().getDataElements(0, 0,p,p, null);
		System.out.println("size is "+pixels.length);
		byte max=0;
		for(byte p:pixels){
			if(p>max)
				max=p;
		}
		
		System.out.println("max is "+max);
		
		byte[] logoPixels=new byte[logoWidth*logoHeight];
		
		int j=0;
		for(int i=0;i<logoPixels.length;i++){
			if(greyBytePixels[j]==max ||greyBytePixels[j]==0){j++;i--;}
			else{
				//System.out.print(coverPixels[j*3+2]);
				logoPixels[i]=coverPixels[j*3+2];
				finalSub[i][0]=coverPixels[j*3+2];
				j++;
			}
			if(j%512==511)
				j+=513;
		}
		
		System.out.println("j value is"+j);
		/*
		BufferedImage newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_BINARY);	  					     
		newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,logoPixels);
		new DisplayFrame(newImage);
		*/
		
		
	}
	
	private void secondSub(){
		
		byte[] logoPixels=new byte[logoWidth*logoHeight];
		
		int j=512;
		
		for(int i=0;i<logoPixels.length;i++){
				
				//process(102400+j,i);
				logoPixels[i]=coverPixels[j*3+2];
				finalSub[i][1]=coverPixels[j*3+2];
				j++;
			
				if(j%1024==0)
				j+=512;
		}
		/*
		newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_BINARY);	  					     
		newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,logoPixels);
		new DisplayFrame(newImage);
		*/
		
		logoPixels=new byte[logoWidth*logoHeight];
		
		
		
		j=512+102400;
		
		for(int i=0;i<logoPixels.length;i++){
				
				//process(102400+j,i);
				logoPixels[i]=coverPixels[j*3+2];
				finalSub[i][2]=coverPixels[j*3+2];
				j++;
			
				if(j%1024==0)
				j+=512;
		}
		/*
		newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_BINARY);	  					     
		newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,logoPixels);
		new DisplayFrame(newImage);
		*/
		
	}
	private void thirdSub(){
		
		byte[] logoPixels=new byte[logoWidth*logoHeight];
		int j=256*1024;
		for(int i=0;i<logoPixels.length;i++){
			logoPixels[i]=coverPixels[j*3+2];
			finalSub[i][3]=coverPixels[j*3+2];
			j++;
			if(j%512==511)
				j+=513;
		}
		
		/*newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_BINARY);	  					     
		newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,logoPixels);
		new DisplayFrame(newImage);
		*/
		logoPixels=new byte[logoWidth*logoHeight];
		
		
		logoPixels=new byte[logoWidth*logoHeight];
		j=256*1024+102400;
		for(int i=0;i<logoPixels.length;i++){
			logoPixels[i]=coverPixels[j*3+2];
			finalSub[i][4]=coverPixels[j*3+2];
			j++;
			if(j%512==511)
				j+=513;
		}
		/*
		newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_BINARY);	  					     
		newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,logoPixels);
		new DisplayFrame(newImage);*/
		
	
	}
	private void fourthSub(){
		
		byte[] logoPixels=new byte[logoWidth*logoHeight];
		
		byte[] pixels =(byte[]) HH.getData().getDataElements(0, 0,p,p, null);
		System.out.println("size is "+pixels.length);
		byte max=0;
		for(byte p:pixels){
			if(p>max)
				max=p;
		}
		
		System.out.println("max is "+max);
		
		int j=511*1024+512;
		for(int i=0;i<logoPixels.length;i++){
			if(greyBytePixels[j]==max ||greyBytePixels[j]==0){j++;i--;}
			else{
				//System.out.print(coverPixels[j*3+2]);
				logoPixels[i]=coverPixels[j*3+2];
				finalSub[i][5]=coverPixels[j*3+2];
				j++;
			}
			if(j%1024==0)
				j+=512;
		}
		/*
		newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_BINARY);	  					     
		newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,logoPixels);
		new DisplayFrame(newImage);
		*/
		
		
		
	
	}
	
	private void results(){
		resultPixels=new byte[logoWidth*logoHeight];
		
		for(int i=0;i<resultPixels.length;i++){
			resultPixels[i]=findPix(finalSub[i]);
		}
		
		newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_BINARY);	  					     
		newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,resultPixels);
		new DisplayFrame(newImage);
	}
	
	
	private byte findPix(byte[] values){
		
		String s="";
		int length=values.length;
		int[] occurences=new int[length];
		
		for(int i=0;i<length;i++){
			int rep=0;
			for(int j=0;j<length;j++){
				if(values[i]==values[j])
					rep++;
			}
			
			occurences[i]=rep;
		}
				
		int max=0;
		for(int i=0;i<values.length;i++){
			if(occurences[max]<occurences[i])
				max=i;
		}
		
		return values[max];
	}
	
	
	
	private class DisplayFrame extends JFrame{
		
		DisplayFrame(final BufferedImage image){
			super("image");
			setSize(1400,1200);
			
			JPanel panel=new JPanel(){				
				public void paintComponent(Graphics gc){
					super.paintComponent(gc);
					gc.drawImage(image,0,0,this);
				}
			};
			add(panel);
			setVisible(true);
		}
	}
	

}
