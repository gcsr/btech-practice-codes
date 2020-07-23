import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;


public class GetGreyImageData {
	
	public static int[] getGreyDatafromABGR(byte[] abgr){
		int[] greyData=new int[abgr.length/4];
		int j=0;
		for(int i=0;i<abgr.length;i+=4){
			
			greyData[j]=(int)((0.0722*(int)abgr[i+1])+(0.7152*(int)abgr[i+2])+(0.2126*(int)abgr[i+3]));
			j++;
		}
		System.out.println("grey data size is "+j);
		return greyData;
	}
	
	public static BufferedImage getConvertedGrayscaleImage(BufferedImage source) 
	{
		BufferedImageOp op = new ColorConvertOp(
		ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
	}

}
