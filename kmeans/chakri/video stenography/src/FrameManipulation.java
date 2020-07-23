import java.awt.image.BufferedImage;


public class FrameManipulation {
	
	public static void main(String[] gcs){
		
		String f="D:/dwt/BGR3.JPG";
		BufferedImage image=Images.getImage(f);
		byte[] pixelsByte=Images.getPixels(image);
		int[] pixels=new int[pixelsByte.length];
		
		int offset = 0;
        for (int i = 0; i < pixels.length;i++) {
        	pixels[i] = pixelsByte[i] & 0xff;
        }
		
		String password="passwordfidahua";
		String binaryPassword=PassToBinary.getBinary(password);
		
		String[] array=new String[password.length()];
		
		for(int i=0;i<array.length;i++){
			array[i]=PassToBinary.getBinary(password.substring(i,i+1));
		}
		
		
		GeneticAlgo3 algo=new GeneticAlgo3(pixels,array);
		//System.out.println(binaryPassword);
		//System.out.println();
		//for(String x:array)
			//System.out.println(x);
		//System.out.println();
	}
	
	

}
