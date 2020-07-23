import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class FindTypes {
	public static void main(String[] gcs){
		while(true){
			
		
		 JFileChooser fileChooser=new JFileChooser();
		 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 int result=fileChooser.showOpenDialog(null);
		 if(result==JFileChooser.CANCEL_OPTION)
		 	return;
		 String filePath=fileChooser.getSelectedFile().getAbsolutePath();
		 try{
			 BufferedImage img =ImageIO.read(new File(filePath));
			 int width=img.getWidth();
			 int height=img.getHeight();
			 System.out.println("imge type is "+img.getType());
			 System.out.println("image size are "+width+" "+height );
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		}  				  							
		  				  		
		  		
	}

}
