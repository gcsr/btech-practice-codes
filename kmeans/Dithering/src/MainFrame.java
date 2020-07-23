import java.awt.BorderLayout;

import com.jhlabs.image.UnsharpFilter;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.UnsharpFilter;

public class MainFrame extends JFrame{

	JButton chooserButton,findButton;	
	JTextField fileField;
	DrawImage middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel;
	
	int height;
	int width;
	
	int[][] redChannel;
	boolean[][] redValues;
	int[][] blueChannel;
	boolean[][] blueValues;
	int[][] greenChannel;
	boolean[][] greenValues;
	
	
	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new MainFrame();
		

	}
	
	public MainFrame(){
		super("Dithering");
		setSize(1200,800);		
		setLayout(new BorderLayout());
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());		
		
		chooserButton=new JButton("choose file");
		fileField=new JTextField("",30);
		findButton=new JButton("Dither");
		topPanel.add(fileField);
		topPanel.add(chooserButton);
		
		
		 
		middlePanel=new DrawImage();
		JScrollPane scroll = new JScrollPane (middlePanel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	    
	
	    fileChooser=new JFileChooser();
	  		
	  		chooserButton.addActionListener(new ActionListener(){
	  			public void actionPerformed(ActionEvent event){
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showOpenDialog(MainFrame.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(fileField.getText());
	  				  							
	  				
	  			}
	  		});
	  		
	  		
	  		
	  		findButton.addActionListener(new ActionListener(){
	  			public void actionPerformed(ActionEvent event){
	  				
	  				
	  				try{
	  					
	  					BufferedImage img = null;
	  					
	  					img = ImageIO.read(new File(fileField.getText()));
	  					
	  					/*height=img.getHeight();
	  					width=img.getWidth();
	  					
	  					byte[] pixels = (byte[]) img.getData().getDataElements(0, 0, width, height, null);
	  		            //System.out.println("pixels size is "+pixels.length);
	  					
	  					int picSize=height*width;
	  					
	  					blueChannel=new int[height][width];
	  					blueValues=new boolean[height][width];
	  					greenChannel=new int[height][width];
	  					greenValues=new boolean[height][width];
	  					redChannel=new int[height][width];
	  					redValues=new boolean[height][width];
	  					
	  					System.out.println("blues values"+blueValues[10][19]);
	  					  
	  					int offset = 0;
	  					for (int i = 0; i < picSize; i++) {
	  						int chevi=i/width;
	  						int sesham=i%width;
	  					  blueChannel[chevi][sesham] = pixels[offset++] & 0xff;
	  					  greenChannel[chevi][sesham] = pixels[offset++] & 0xff;
	  					  redChannel[chevi][sesham] = pixels[offset++] & 0xff;	  						  
	  					} */
	  					
	  					String conform=                                      
	  				  			JOptionPane.showInputDialog( "Dithered Image" );
	  				  	if(conform.equals("no"))
	  				  		System.exit(1);
	  					
	  					BufferedImage ditheredImage=Test.main(new String[]{fileField.getText()});
	  					middlePanel.updateImage(ditheredImage);
	  					
	  					conform=                                      
	  				  			JOptionPane.showInputDialog( "Average Filter" );
		  				  	if(conform.equals("no"))
		  				  		System.exit(1);
	  				  	
	  					
	  					
	  				  	float ninth = 1.0f / 9.0f;
	  				  	float[] blurKernel = {
	  						  ninth, ninth, ninth,
	  						  ninth, ninth, ninth,
	  						  ninth, ninth, ninth
	  				  	};
	  				  	ConvolveOp meanOp = new ConvolveOp(
	  						  new Kernel(3, 3, blurKernel),ConvolveOp.EDGE_NO_OP, null);
	  						 
	  				  	
	  				  	BufferedImage meanImage=meanOp.filter(ditheredImage,null);
	  				  	
	  				  	
	  				  conform=                                      
	  				  			JOptionPane.showInputDialog( "Median Filter" );
		  				if(conform.equals("no"))
		  				  		System.exit(1);
	  				  	
	  				  	RenderedImage rImage=(RenderedImage)meanImage;
	  				  	RenderedOp renderedOp = MedianFilterDescriptor.create(rImage,
	  				  			MedianFilterDescriptor.MEDIAN_MASK_SQUARE , 5, null);
	  				  	BufferedImage image = renderedOp.getAsBufferedImage();
	  				  	middlePanel.updateImage(image);
	  				  	
	  				  	
		  				
		  			  	conform=                                      
	  				  			JOptionPane.showInputDialog( "Adaptive Filter" );
		  				if(conform.equals("no"))
		  				  		System.exit(1);
		  				BufferedImage adaptImage=null;
	  				  	
		  			  	AdaptiveFilter af=new AdaptiveFilter(image,0.5);
		  			  	adaptImage=af.adaptiveImage();
		  			  	middlePanel.updateImage(adaptImage);
		  			  	
		  			  conform=                                      
	  				  			JOptionPane.showInputDialog( "Gaussian Filter" );
		  				if(conform.equals("no"))
		  				  		System.exit(1);
		  				BufferedImage gaussImage=adaptImage;
	  				  	
		  			  	GaussianFilter gf=new GaussianFilter(5);
		  			  	//gf.setSigma((float).3);
		  			  	//gf.setRadius(3);
		  			  	//gaussImage=gf.getBlurredImage(adaptImage);
		  			  	gf.filter(adaptImage, gaussImage);
		  			  	middlePanel.updateImage(gaussImage);
		  			  	
		  			  /*conform=                                      
	  				  			JOptionPane.showInputDialog( "Gaussian Filter" );
		  				if(conform.equals("no"))
		  				  		System.exit(1);
		  				
	  				  	
		  			  	//gf=new GaussianFilter();
		  			  //	gf.setSigma((float)7);
		  			  	//gf.setRadius(3);
		  			  	//gaussImage=gf.getBlurredImage(gaussImage);
		  			  	middlePanel.updateImage(gaussImage);*/
		  			  conform=                                      
	  				  			JOptionPane.showInputDialog( "Adaptive Filter" );
		  				if(conform.equals("no"))
		  				  		System.exit(1);
		  				af=new AdaptiveFilter(gaussImage,1);
		  			  	adaptImage=af.adaptiveImage();
		  			  	middlePanel.updateImage(adaptImage);
		  			  conform=                                      
	  				  			JOptionPane.showInputDialog( "Sharpen Filter" );
		  				if(conform.equals("no"))
		  				  		System.exit(1);
		  			  	BufferedImage sharpImage=null;
		  			  	UnsharpFilter sf=new UnsharpFilter();
		  			  	sharpImage=sf.filter(adaptImage);
		  			  	middlePanel.updateImage(sharpImage);
		  			  			  			  	
	  				  	
	  				}catch(Exception ex){
	  					ex.printStackTrace();
	  					System.exit(0);
	  				}
	  				
	  				//dithering();
	  				JOptionPane.showMessageDialog(MainFrame.this,"Done");
	  				
	  				
	  			}
	  		});
	  		
	  		
		topPanel.add(findButton);
		//bottomPanel.add(clusterTotalField);
		//bottomPanel.add(clusterNumberField);
		
		
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);

		//pack();
		setVisible(true);
	
	}
	
	private void dithering(){
		System.out.println("in dithering");
		System.out.println("height is"+height);
		System.out.println("width is"+width);
		int newblueChannel,quant_error,oldblueChannel;
		for(int j=0;j<height;j++){
			for(int i=0;i<width;i++){
				oldblueChannel=blueChannel[i][j];
				newblueChannel  = (oldblueChannel/256)>1?1:0;
				blueChannel[i][j]  = newblueChannel;
				if(i<10){
					System.out.print(oldblueChannel+"\t");
					System.out.print(newblueChannel+"\t");
				}
				blueValues[i][j]=true;
				
				quant_error  = oldblueChannel - newblueChannel;
				
				if((i+1)<(width) && blueValues[i+1][j]==false)
				blueChannel[i+1][j  ] = blueChannel[i+1][j  ] +(int)(quant_error * 7.0/16);
				
				if((j+1)<(height) && (i>0) && blueValues[i-1][j+1]==false)
				blueChannel[i-1][j+1] = blueChannel[i-1][j+1] +(int) (quant_error * 3.0/16);
				
				if((j+1)<(height) && blueValues[i][j+1]==false)
				blueChannel[i  ][j+1] = blueChannel[i  ][j+1] + (int)(quant_error * 5.0/16);
				
				if((j+1)<height && (i+1)<width && blueValues[i+1][j+1]==false)
				blueChannel[i+1][j+1] = blueChannel[i+1][j+1] + (int)(quant_error * 1.0/16);
			}
			System.out.println();
		
		}
		
		
		
		DrawingFrame frm=new DrawingFrame(blueChannel);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(1200, 1200);
		frm.setVisible(true);
		
	}
	
	
	private int find_closest_palette_color(int oldblueChannel){
		 return (int) Math.floor(oldblueChannel/256.0);		 
	}
	
	
	/*private int[][] shares(int i){
		if (i==1){
			return new int[][]{{1,0,0,1},{0,1,1,0}};
		}
		else if(i==0){
			return new int[][]{{1,0,0,1},{1,0,0,1}};
		}
	}*/
}
