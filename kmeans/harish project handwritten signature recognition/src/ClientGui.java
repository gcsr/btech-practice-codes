import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class ClientGui {
	
	public static void main(String[] gcs){
		
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result=fileChooser.showOpenDialog(null);
			
			if(result==JFileChooser.CANCEL_OPTION)
				return;
			
		
		String inputFile=(fileChooser.getSelectedFile().getAbsolutePath());//"D:/cut/081/081A01cut.jpg";
		System.out.println(inputFile);
		BufferedImage img = null;
		try	{
			    img = ImageIO.read(new File(inputFile));
		}catch (IOException e) {
		}
			
		
		
		
		String path="D:/output/";
		File f=new File(path);
		
		File[] dirs=f.listFiles();
		
		//ExecutorService exe=Executors.newFixedThreadPool(1);
		//for(File s:dirs){
			//exe.execute((new Search(inputFile,leftHistograms,rightHistograms,lipsHistogram,new int[]{},s.getAbsolutePath())));
			new Search(inputFile,path);
		//}
		
		
		
		/*
		String file1="D:/cut/081/081A01cut6left.jpg";
		
		
		BufferedImage greyImage = null;
		try
		{
			greyImage = ImageIO.read(new File(file1));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Raster raster=greyImage.getRaster();
		int height=greyImage.getHeight();
		int width=greyImage.getWidth();
		int[][] pixels=new int[height][width];
		
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				pixels[i][j]=raster.getSample(j,i,0);
				
			}
		}

		new DrawingFrame(pixels);
		*/

	}
	
	

}
