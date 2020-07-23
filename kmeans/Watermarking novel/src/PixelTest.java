import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PixelTest {
	
	
	public PixelTest(){
		readLogo();
		/*try{
		   BufferedImage img = null;
		   img =ImageIO.read(new File("D:/dwt/BGR13.jpg"));
		   int width=img.getWidth();
		   int height=img.getHeight();
		   
		   System.out.println(img.getType());
		   
		   byte[] imagePixels = (byte[]) img.getData().getDataElements(0, 0,width,height, null);
		   //for(int i=0;i<imagePixels.length;i++)
			  // imagePixels[i]+=128;
		   
		   BufferedImage newImage=new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);	  					     
		   newImage.getWritableTile(0, 0).setDataElements(0, 0, width, height,imagePixels);		   
		   
		   displayImage(newImage);
		   
		   System.out.println("pixels per  is "+imagePixels.length/(width*height));
		   
		   //Raster raster=img.getRaster();
		}catch(Exception ex){
			ex.printStackTrace();
		}*/
		   
	}
	
	public static void main(String[] gcs)throws Exception{
		   
		new PixelTest();
		   		   
	}
	
	private void displayImage(BufferedImage image){
		readLogo();
		//new DisplayFrame(image);
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
	
	private void readLogo(){
		System.out.println("in readLogo method");
			try{
				   BufferedImage img = null;
				   img =ImageIO.read(new File("D:/dwt/binary5.gif"));
				   new DisplayFrame(img);
				   System.out.println(img.getType());
				   //img=GetGreyImageData.getConvertedGrayscaleImage(img);
				   int logoWidth=img.getWidth();
				   int logoHeight=img.getHeight();
				   
				   byte[] logoPixels = (byte[])img.getData().getDataElements(0, 0,logoWidth,logoHeight, null);
				   
				   System.out.println("logo width is "+logoWidth);
				   System.out.println("logo height is "+logoHeight);
				   System.out.println("pixels length "+logoPixels.length);
				   /*for(int i=0;i<5000;i++){
					   System.out.print(logoPixels[i]+" ");
				   }*/
				   BufferedImage newImage=new BufferedImage(logoWidth,logoHeight, BufferedImage.TYPE_BYTE_INDEXED);	  					     
					newImage.getWritableTile(0, 0).setDataElements(0, 0,logoWidth,logoHeight,logoPixels);
					new DisplayFrame(newImage);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
	
	

}
