import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class HaarFilterFrame extends JFrame
{

	JButton chooserButton,haarButton,finalImageButton,logoButton,noiseButton,saveWatermark,browseWatermark;	
	JTextField fileField;
	DrawImage middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel,bottomPanel;
	int width,height;
	BufferedImage LL,LH,HH,HL,newImage,greyImage;
	int p;
	int logoWidth,logoHeight;
	byte[] logoPixels,greyBytePixels;
	BufferedImage originalImage;
	byte[] imagePixels;
	Raster raster=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HaarFilterFrame();
		

	}
	
	public HaarFilterFrame()
	{
		super("Har Filter frame");
		setSize(1400,1200);		
		setLayout(new BorderLayout());
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());		
		chooserButton=new JButton(" choose file");		
		haarButton=new JButton(" find har image");
		finalImageButton=new JButton("draw Mix Image");
		logoButton=new JButton("get Logo");
		noiseButton=new JButton("Add Noise");
		saveWatermark=new JButton("save water mark image");
		browseWatermark=new JButton("browse water mark image");
		//numberField=new JTextField("",5);
		fileField=new JTextField("D:/wavelet.png",30);		
		topPanel.add(fileField);
		topPanel.add(chooserButton);		
		//topPanel.add(numberField); 
		middlePanel=new DrawImage();
		JScrollPane scroll = new JScrollPane (middlePanel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	    
	    bottomPanel=new JPanel();
	    fileChooser=new JFileChooser();
	    //readLogo();
	  		
	  		chooserButton.addActionListener(new ActionListener()
	  		{
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showOpenDialog(HaarFilterFrame.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(fileField.getText());
	  				  							
	  				
	  			}
	  		});
	  		
	  		
	  		
	  		haarButton.addActionListener(new ActionListener()
	  		{
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				
	  				try{
	  					
	  					   BufferedImage img = null;
	  					   img =ImageIO.read(new File(fileField.getText()));
	  					   width=img.getWidth();
	  					   height=img.getHeight();
	  					   originalImage=ImageIO.read(new File(fileField.getText()));
	  					   imagePixels = (byte[]) originalImage.getData().getDataElements(0, 0,width,height, null);
	  					   System.out.println("original image length is "+imagePixels.length);
	  					   //raster=originalImage.getRaster();
	  					   
	  					   System.out.println("imge type is "+img.getType());
	  					   //img=GetGreyImageData.getConvertedGrayscaleImage(img);
	  					    img=GetGreyImageData.getConvertedGrayscaleImage(img);
	  					    System.out.println("image height is"+img.getHeight() );
	  					    System.out.println("image width is"+img.getWidth() );
	  					    greyBytePixels = (byte[]) img.getData().getDataElements(0, 0,width,height, null);
	  					    //byte[] imagePixels=greyBytePixels;
	  					    //	System.out.println("pixels size is "+pixels.length);
	  					    HaarFilter haar=new HaarFilter(width);
	  					    int[] greyPixels;
	  					    greyPixels=haar.filter(greyBytePixels,null);
	  					    //byte[] ppp=haar.invert(greyPixels,null);
 					     
 					     
 					   for(int i=0;i<greyPixels.length;i++){
 					    	 greyBytePixels[i]=(byte)greyPixels[i];
 					     }
 					     
 					    BufferedImage greyImage=new BufferedImage(width,height, BufferedImage.TYPE_BYTE_GRAY);	  					     
 					    greyImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, greyBytePixels);
 					    
 					   //greyImage.getWritableTile(0, 0).setDataElements(0, 0, width, height,ppp);
 					    //greyImage.	
 					    p=width/2;
 					    LL=greyImage.getSubimage(0, 0,p,p);
 					    System.out.println("width is "+LL.getWidth()+"height is "+LL.getHeight());
 					    HL=greyImage.getSubimage(p, 0,p,p);
 					    System.out.println("width is "+HL.getWidth()+"height is "+HL.getHeight());
 					    LH=greyImage.getSubimage(0,p, p,p);
 					    System.out.println("width is "+LH.getWidth()+"height is "+LH.getHeight());
 					    HH=greyImage.getSubimage(p,p,p,p);
 					    System.out.println("width is "+HH.getWidth()+"height is "+HH.getHeight());
 					    middlePanel.updateImage(greyImage);
 					    
 					    
	  				}catch(Exception ex)
	  				{
	  					ex.printStackTrace();
	  					System.exit(0);
	  				}
	  				
	  				
	  			}
	  			
	  			
	  			
	  			
	  			private BufferedImage getConvertedGrayscaleImage(BufferedImage source) 
	  			{
	  				BufferedImageOp op = new ColorConvertOp(
	  				ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	  				return op.filter(source, null);
	  			}
	  			
	  		});
	  		
	  	finalImageButton.addActionListener(new ActionListener(){
  				public void actionPerformed(ActionEvent ev){
  					
  					readLogo();					    
					processSubImages();
  					//middlePanel.updateImage(greyImage);
  					
  				}
  		});
	  	
	  	logoButton.addActionListener(new ActionListener(){
	  		
	  		public void actionPerformed(ActionEvent ev){
	  			new Decoder(originalImage,imagePixels,logoWidth,logoHeight);
	  		}
	  		
	  	});
	  	
	  	saveWatermark.addActionListener(new ActionListener(){
	  		public void actionPerformed(ActionEvent ev){
	  			try{
	  				
	  				
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showSaveDialog(HaarFilterFrame.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				FileOutputStream fout=new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath()+"browse");
					fout.write(imagePixels);
					fout.close();
	  				BufferedImage coverImage=new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);	  					     
	  				coverImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, imagePixels);
	  				//middlePanel.updateImage(coverImage); 
	  				fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  			    File outputfile = new File(fileChooser.getSelectedFile().getAbsolutePath());
	  			    
	  			    ImageIO.write(coverImage, "jpeg", outputfile);
	  			    
	  			    
	  				
	  			}catch(Exception ex){
	  				ex.printStackTrace();
	  			}
	  			
	  			
	  		}
	  	});
	  	
	  	browseWatermark.addActionListener(new ActionListener(){
	  		public void actionPerformed(ActionEvent ev){
	  			try{
	  			
	  			/*byte[] cmp=imagePixels;
	  			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  				int result=fileChooser.showOpenDialog(HaarFilterFrame.this);
  				
  				if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				BufferedImage img = null;
  				fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());				  				
  				middlePanel.updateImage(fileField.getText());
  				img =ImageIO.read(new File(fileField.getText()));
	  			width=img.getWidth();
				height=img.getHeight();
				System.out.println("image type is "+img.getType());
				imagePixels = (byte[])img.getData().getDataElements(0, 0,width,height, null);
				
				for(int i=0;i<100;i++){
					System.out.print(imagePixels[i]+ "  "+cmp[i]+ " ");
					if(i%10==0)
					System.out.println();
				}
				
				if(imagePixels[10]!=cmp[10]){
					System.out.println("error ");
					System.exit(1);
				}
					
				System.out.println("image length is "+imagePixels.length);*/
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showOpenDialog(HaarFilterFrame.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				FileInputStream fin=new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath());
	  				int offset=0;
					byte[] data = new byte[1024*1024*3];
					int bytesRead = 0;
					while (offset < 1024*0124*3) {
							
						bytesRead = fin.read(data, offset, data.length-offset);
						if (bytesRead == -1) break;
						offset += bytesRead;
					}
					fin.close();					
					imagePixels=data;
					
					String path=fileChooser.getSelectedFile().getAbsolutePath();
					
					path=path.substring(0,path.indexOf("browse"));
					System.out.println("path is "+path);
					BufferedImage
					 bfImage=ImageIO.read(new File(path));
					middlePanel.updateImage(bfImage);
						
	  		}catch(Exception ex){
	  			ex.printStackTrace();
	  		}
	  			
	  			
	  			
	  		}
	  	});

	  	
	  	
	  	noiseButton.addActionListener(new ActionListener(){
	  		public void actionPerformed(ActionEvent ev){
	  			
	  			/*Point[] points=middlePanel.points;	  			
	  			int length=middlePanel.length;
	  			
	  			System.out.println("length is "+length);
	  			int i=0;
	  			while(i<length){
	  				int x=points[i].x;
	  				int y=points[i].y;
	  				
	  				imagePixels[y*height+x]=0;
	  				imagePixels[y*height+x+1]=0;
	  				imagePixels[y*height+x+2]=0;
	  				i++;
	  				
	  			}*/
	  			
	  			String firstNumber =                                      
	  			JOptionPane.showInputDialog( "Enter gap to insert noise multiple of 3" );
	  			
	  			int p=0;
	  			
	  			try{
	  				p=Integer.parseInt(firstNumber);
	  			}catch(Exception ex){
	  				JOptionPane.showMessageDialog( null, "gap default value is 3000",
	  						"error", JOptionPane.ERROR_MESSAGE );   
	  					p=3000;	 

	  			}
	  			

	  			
	  			int i=0;
	  			while(i<3145720){
	  				
	  				imagePixels[i]=0;
	  				imagePixels[i+1]=0;
	  				imagePixels[i+2]=0;
	  				
	  				i+=p;
	  			}
	  			
	  			BufferedImage coverImage=new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);	  					     
	  			coverImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, imagePixels);
	  			middlePanel.updateImage(coverImage);
	  			
	  		}
	  	});
	  		
		topPanel.add(haarButton);
		topPanel.add(finalImageButton);
		topPanel.add(saveWatermark);
		topPanel.add(browseWatermark);
		topPanel.add(logoButton);
		topPanel.add(noiseButton);
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		//pack();
		setVisible(true);
	
	}
	private void readLogo(){
		System.out.println("in readLogo method");
			try{
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int result=fileChooser.showOpenDialog(HaarFilterFrame.this);
					
					if(result==JFileChooser.CANCEL_OPTION)
						return;
  				
					BufferedImage img = null;
					fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
					img =ImageIO.read(new File(fileField.getText()));
					
					
					/*
					BufferedImage img = null;
				   img =ImageIO.read(new File("D:/dwt/binary4.png"));*/
				   System.out.println("logo type is 12- binary"+img.getType());
				   //img=GetGreyImageData.getConvertedGrayscaleImage(img);
				   logoWidth=img.getWidth();
				   logoHeight=img.getHeight();
				   
				   logoPixels = (byte[]) img.getData().getDataElements(0, 0,logoWidth,logoHeight, null);
				   
				   System.out.println("logo width is "+logoWidth);
				   System.out.println("logo height is "+logoHeight);
				   System.out.println("pixels length "+logoPixels.length);
				   /*for(int i=0;i<logoPixels.length;i++){
					   if(logoPixels[i]>0)
						   logoPixels[i]=1;
				   }*/
				   
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
	
	
	
	public void processSubImages(){
		firstSub();
		secondSub();
		thirdSub();
		fourthSub();
		BufferedImage coverImage=new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);	  					     
		coverImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, imagePixels);
		middlePanel.updateImage(coverImage);
		//new Decoder(originalImage,imagePixels,logoWidth,logoHeight);
	}
	
	private void process(int cover,int logo){
		//System.out.print(" "+(cover*3+2)+" "+logo);
		//System.out.print(" "+logoPixels[logo]);
		imagePixels[cover*3+2]=logoPixels[logo];
		//System.out.print(" "+imagePixels[cover*3+2]);
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
	

	private void firstSub(){
		
		System.out.println("in  process subimages");
		byte[] pixels =(byte[]) LL.getData().getDataElements(0, 0,p,p, null);
		System.out.println("size is "+pixels.length);
		byte max=0;
		for(byte p:pixels){
			if(p>max)
				max=p;
		}
		
		System.out.println("max is "+max);
		
		int j=0;
		for(int i=0;i<logoPixels.length;i++){
			if(greyBytePixels[j]==max ||greyBytePixels[j]==0){j++;i--;}
			else{
				process(j,i);
				j++;
			}
			if(j%512==511)
				j+=513;
		}
		
	}
	
	
	private void secondSub(){
		
		int j=512;
		
		for(int i=0;i<logoPixels.length;i++){
			process(j,i);
			process(102400+j,i);
			j++;
			
			if(j%1024==0)
				j+=512;
		}
				
		
		
	}
	
	
	private void thirdSub(){
		
		
		int j=256*1024;
		for(int i=0;i<logoPixels.length;i++){
			
			process(j,i);
			process(j+102400,i);
			j++;
			if(j%512==511)
				j+=513;
		}
		
		
	}
	
	
	private void fourthSub(){
		
		System.out.println("in  process subimages");
		byte[] pixels =(byte[]) HH.getData().getDataElements(0, 0,p,p, null);
		System.out.println("size is "+pixels.length);
		byte max=0;
		for(byte p:pixels){
			if(p>max)
				max=p;
		}
		
		System.out.println("max is "+max);
		
		int j=511*1024+512;
		
		for(int i=0;i<logoPixels.length;i++){
			if(greyBytePixels[j]==max ||greyBytePixels[j]==0 ){j++;i--;}
			else{
				process(j,i);
				
				j++;
			}
			if(j%1024==0)
				j+=512;
		}
		
		
		
	}
}

/* byte[] pixels = (byte[]) img.getData().getDataElements(0, 0,width,height, null);
  System.out.println("pixels size is "+pixels.length);
    int[] greyPixels=GetGreyImageData.getGreyDatafromABGR(pixels);
    System.out.println("image size is "+greyPixels.length);
    byte[] greyBytePixels=new byte[greyPixels.length];
    for(int i=0;i<greyPixels.length;i++){
   	 greyBytePixels[i]=(byte)greyPixels[i];
    }
   
    System.out.println("grey pixels length"+greyPixels.length);
    HaarFilter haar=new HaarFilter(width);
    greyPixels=haar.filter(greyBytePixels,null);
    //byte[] ppp=haar.invert(greyPixels,null);
    
    
  for(int i=0;i<greyPixels.length;i++){
   	 greyBytePixels[i]=(byte)greyPixels[i];
    }
    
   BufferedImage greyImage=new BufferedImage(width,height, BufferedImage.TYPE_BYTE_GRAY);	  					     
   greyImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, greyBytePixels);
   
  //greyImage.getWritableTile(0, 0).setDataElements(0, 0, width, height,ppp);
   //greyImage.	
   p=width/2;
   LL=greyImage.getSubimage(0, 0,p,p);
   System.out.println("width is "+LL.getWidth()+"height is "+LL.getHeight());
   HL=greyImage.getSubimage(p, 0,p,p);
   System.out.println("width is "+HL.getWidth()+"height is "+HL.getHeight());
   LH=greyImage.getSubimage(0,p, p,p);
   System.out.println("width is "+LH.getWidth()+"height is "+LH.getHeight());
   HH=greyImage.getSubimage(p,p,p,p);
   System.out.println("width is "+HH.getWidth()+"height is "+HH.getHeight());
   middlePanel.updateImage(greyImage);
   processSubImages();*/


/*System.out.println("in  process subimages");
byte[] pixels =(byte[]) LL.getData().getDataElements(0, 0,p,p, null);
System.out.println("size is "+pixels.length);
byte max=0;
for(byte p:pixels){
	if(p>max)
		max=p;
}

System.out.println("max is "+max);

int j=0;
for(int i=0;i<logoPixels.length;i++){
	if(greyBytePixels[j]==max ||greyBytePixels[j]==0){j++;i--;}
	else{
		process(j,i);
		j++;
	}
	if(j%512==511)
		j+=513;
}


j=512;

for(int i=0;i<logoPixels.length;i++){
	process(j,i);
	process(102400+j,i);
	j++;
	
	if(j%1024==0)
		j+=512;
}

j=256*1024;
for(int i=0;i<logoPixels.length;i++){
	
	process(j,i);
	process(j+102400,i);
	j++;
	if(j%512==511)
		j+=513;
}



j=512*512;

for(int i=0;i<logoPixels.length;i++){
	if(greyBytePixels[j]==max ||greyBytePixels[j]==0 ){j++;i--;}
	else{
		process(j,i);
		
		j++;
	}
	if(j%1024==0)
		j+=512;
}


System.out.println("j value is"+j);*/
