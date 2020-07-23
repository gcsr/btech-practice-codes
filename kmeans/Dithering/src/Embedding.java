import java.awt.image.BufferedImage;

public class Embedding{
	BufferedImage image1,image2;
	byte[] image1Pixels;
	byte[] image2Pixels;
	int image1Height,image1Width,image2Height,image2Width;
	
	public Embedding(BufferedImage image1,BufferedImage image2){
		this.image1=image1;
		this.image2=image2;
		image1Width=image1.getWidth();
		image1Height=image1.getHeight();
		image2Width=image2.getWidth();
		image2Height=image2.getHeight();
		
		image1Pixels = (byte[]) image1.getData().getDataElements(0, 0, image1Width, image1Height, null);
		image2Pixels = (byte[]) image2.getData().getDataElements(0, 0, image2Width, image2Height, null);
		
	}
	
	public BufferedImage embed(){
		byte[] pixels=new byte[image1Pixels.length+image2Pixels.length];		
		int offset=0;
		int i=0;
		boolean ff=true;
		
		while(offset<pixels.length){
			if(ff==true)
			pixels[offset++]=image1Pixels[i];
			
			else
			pixels[offset++]=image2Pixels[i];
			
			if(offset%3072==0)
				ff=!ff;
		}
		
		BufferedImage embeddedImage=new BufferedImage(image1Width,image1Height+image2Height, BufferedImage.TYPE_3BYTE_BGR);	  					     
		embeddedImage.getWritableTile(0, 0).setDataElements(0, 0, image1Width,image1Height+image2Height, pixels);
		
		return embeddedImage;
	}
	
	
	
	
	
	
}