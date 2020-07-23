import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class MainClass {
	BufferedImage coverImage;
	BufferedImage logoImage;
	int logoWidth,logoHeight,coverWidth,coverHeight;
	byte[] logoPixels;
	
	public MainClass(){
		getImagesandDimensions();
		//
	}
	
	public static void main(String[] gcs){
		new MainClass();
	}
	
	private void getImagesandDimensions(){
		
		JFileChooser fileChooser=new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result=fileChooser.showOpenDialog(null);			
			
			if(result==JFileChooser.CANCEL_OPTION)
				return;
			
		String imagePath=fileChooser.getSelectedFile().getAbsolutePath();
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		result=fileChooser.showOpenDialog(null);			
		
		if(result==JFileChooser.CANCEL_OPTION)
			return;
		
		String logoPath=fileChooser.getSelectedFile().getAbsolutePath();
		
		getImages(imagePath,logoPath);
		
		
	}
	
	private void getImages(String imagePath,String logoPath){
		
			try
			{
			    coverImage = ImageIO.read(new File(imagePath));
			    int width=coverImage.getWidth();
			    int height=coverImage.getHeight();
			    byte[] pixels = (byte[]) coverImage.getData().getDataElements(0, 0, width, height, null);
			    int[] greyPixels=GetGreyImageData.getGreyDatafromABGR(pixels);
			    
			    
			    
			    logoImage = ImageIO.read(new File(logoPath));
			    logoWidth=logoImage.getWidth();
			    logoHeight=logoImage.getHeight();
			    //readCoverImage
			    readLogoImage();
			}
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
			
			
			
			
	}
	
	private BufferedImage getConvertedGrayscaleImage(BufferedImage source) 
	{
		BufferedImageOp op = new ColorConvertOp(
		ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
	}
	
	private void readLogoImage(){
		byte[] logoPixels = (byte[]) logoImage.getData().getDataElements(0, 0,logoWidth, logoHeight, null);
		System.out.print("logopixels size is "+logoPixels.length);
		System.out.print("image type is  "+logoImage.getType());
		for(int i=0;i<100;i++)
		System.out.print(logoPixels[i]);
		//logoImage.getData().getD
	}

}
