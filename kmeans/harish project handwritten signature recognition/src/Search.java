import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Search extends JFrame implements Runnable{	
	DisplayImage rightPanel;SingleDisplay leftPanel;
	String outputFolder;
	BufferedImage inputImage=null;
	JLabel temp;
	
	boolean matched=false;
	int two=0;
	String inputFile;
	public Search(String inputFile){
		
		super("searching");
		setSize(800,500);		
		
		setLayout(new GridLayout(2,1));
		leftPanel=new SingleDisplay(inputFile);	
		rightPanel=new DisplayImage(inputFile);
		temp=new JLabel("Values");
		this.inputFile=inputFile;
		
		try{
		    inputImage = ImageIO.read(new File(inputFile));
		      
		}
		
		catch (IOException ex) {
			ex.printStackTrace();
		}		
		
		add(rightPanel);
		add(leftPanel);
		//add(temp);
		setVisible(true);
		run();
	}
	
	public Search(BufferedImage image){
		
		super("searching");
		setSize(800,500);		
		setLayout(new GridLayout(2,1));
		inputImage=image;
		leftPanel=new SingleDisplay(image);
		temp=new JLabel("Values");
		rightPanel=new DisplayImage(image);
				
		add(rightPanel);
		add(leftPanel);
		//add(temp);
		setVisible(true);
		run();
	}
	
	public Search(){
		
		super("searching");
		setSize(300,300);		
		setLayout(new FlowLayout());
		leftPanel=new SingleDisplay("D:/chowda.jpg");
		rightPanel=new DisplayImage("D:/chowda.jpg");
		add(leftPanel);
		add(rightPanel);
		add(temp);
		setVisible(true);
	}
	
	public void run(){
		try{
			outputFolder="images\\";
			final File f=new File(outputFolder);
			String[] list=f.list();
			
			
			BufferedImage outputImage=null;
			double matching;
			for(String s:list){
				final String p=s;
				
				try{
				    outputImage = ImageIO.read(new File(f.getAbsolutePath()+"\\"+s));
				    outputImage =ImageOperations.getGrayScaleImage(outputImage );
				    outputImage =ImageOperations.getBinaryImage(outputImage );		
				    outputImage =ImageOperations.getResizedImage(outputImage );
				    outputImage =ImageOperations.getBoundingBox(outputImage );
				    outputImage =ImageOperations.getDilatedImage(outputImage );
				    outputImage =ImageOperations.getThinnedImage(outputImage );
				    
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
				try{
				    inputImage = ImageIO.read(new File(inputFile));
				    inputImage =ImageOperations.getGrayScaleImage(inputImage );
				    inputImage =ImageOperations.getBinaryImage(inputImage );		
				    inputImage =ImageOperations.getResizedImage(inputImage );
				    inputImage =ImageOperations.getBoundingBox(inputImage );
				    inputImage =ImageOperations.getDilatedImage(inputImage );
				    inputImage =ImageOperations.getThinnedImage(inputImage );
				    
				}
				
				catch (IOException ex) {
					ex.printStackTrace();
				}
				//leftPanel.updateImage(inputImage);
				rightPanel.updateImage(f.getAbsolutePath()+"\\"+p);
			     
				//leftPanel.repaint();
				//rightPanel.repaint();
				revalidate();
				repaint();
				
			
				matching=CompareImages.compare(inputImage, outputImage).overallPercentage;
				temp.setText("match of "+matching+" found at "+s);
				System.out.println(matching);
				if(matching>65){
					JOptionPane.showMessageDialog(Search.this,"match of "+matching+" found at "+s);					
				}else{
					JOptionPane.showMessageDialog(Search.this,"not matching");
				}
				
				
				
			}	
			
					
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
		

}
	