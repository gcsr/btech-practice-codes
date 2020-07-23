import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class LogoProcessor {
	
	BufferedImage logo;
	
	public LogoProcessor(BufferedImage logo,int width,int height){
		
		this.logo=logo;
		System.out.println("before type is "+logo.getType());
		logo=Scale.getScaledImage(0, 0, 1024, 1024, logo);
		
		System.out.println("agter type is "+logo.getType());
		
		logo=GetGreyImageData.getConvertedGrayscaleImage(logo);
		FindFeatures f=new FindFeatures(logo);		
		int blockPixels[][][]=f.getBlockWithHighSTD(8,8);
		
		double[] blockNormalValues=NormalizedGreyImageValues.getNormalizedGreyValues(blockPixels);
		DCTCoefficients coeffinder=new DCTCoefficients(blockPixels);
		double[][][] dctCoefficients=coeffinder.getDCTCoefficients();
		double imageGreyValue=getImgGreyValue(blockNormalValues);
		double[] variances=GetVariances.getVariances(dctCoefficients);
		
		new DisplayFrame(logo);
	}
	
	public static void main(String[] gcs){
		JFileChooser fileChooser=new JFileChooser();
  		
  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  		int result=fileChooser.showOpenDialog(null);
  		if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				
  		String path=(fileChooser.getSelectedFile().getAbsolutePath());
  		
  		BufferedImage img = null;
		try
		{
		    img = ImageIO.read(new File(path));
		}
		catch (IOException e) 
		{
		}
		
		new LogoProcessor(img,1024,1024);
	}
	
	private double getImgGreyValue(double[] normals){
		
		double sum=0;
		for(double x:normals)
			sum+=x;
		return sum/(normals.length) ;
	}
	
	

}
