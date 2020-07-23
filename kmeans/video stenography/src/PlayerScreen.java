import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PlayerScreen extends JPanel{
	boolean play=false;
	BufferedImage[] images;
	int counter=0;
	int frameNo=0;
	
	int[] pixelPositions;
	String result="";
	String binary="";
	String dir="";
	public void update(String dir){
		this.dir=dir;
		File f=new File(dir);
		String[] names=f.list();
		
		images=new BufferedImage[names.length];
		frameNo=names.length;
		JOptionPane.showConfirmDialog(this,"avialable frame  are "+frameNo);
		try{
			for(int i=0;i<images.length;i++){
				    images[i] = ImageIO.read(new File(dir+"/"+names[i]));
			}
			
		}catch(Exception ex){
				
		}

	}
	public PlayerScreen(String dir){
		
		this.dir=dir;
		File f=new File(dir);
		String[] names=f.list();
		
		images=new BufferedImage[names.length];
		frameNo=names.length;
		JOptionPane.showConfirmDialog(this,"avialable frame  are "+frameNo);
		try{
			for(int i=0;i<images.length;i++){
				    images[i] = ImageIO.read(new File(dir+"/"+names[i]));
			}
			
		}catch(Exception ex){
				
		}
	}
	public void paintComponent(Graphics gc){
		
		super.paintComponent(gc);
		Insets i=getInsets();		
		//System.out.println("on paint");
		if(play){
			counter++;
		}
		if(counter==frameNo)
			counter=0;
		gc.drawImage(images[counter],i.left,i.right,this);
		try{
			Thread.currentThread().sleep(60);
		}
		catch(Exception ex){
			
		}
		gc.drawImage(images[counter],i.left,i.right,this);
		
		try{
		Thread.currentThread().sleep(60);
		}
		catch(Exception ex){
			
		}
		
		repaint();
	}
	
	public void setPlay(boolean play){
		this.play=play;
	}
	
	public void encode(int frame,String password){
		BufferedImage image=images[frame];
		byte[] pixelsByte=Images.getPixels(image);
		int[] pixels=new int[pixelsByte.length];
		
		int offset = 0;
        for (int i = 0; i < pixels.length;i++) {
        	pixels[i] = pixelsByte[i] & 0xff;
        }
		
		
		String binaryPassword=PassToBinary.getBinary(password);
		
		String[] array=new String[password.length()];
		
		for(int i=0;i<array.length;i++){
			array[i]=PassToBinary.getBinary(password.substring(i,i+1));
		}		
		
		GeneticAlgo3 algo=new GeneticAlgo3(pixels,array,image.getWidth(),image.getHeight());		
		images[frame]=algo.getEmbeddedeImage();
		pixelPositions=algo.pixelPositions();		
		try{	  	  				
  				int width=images[frame].getWidth();
  				int height=images[frame].getHeight();  				
  				byte[] bytes=algo.getEmbeddedPixels();  				
  				FileOutputStream fout=new FileOutputStream(dir+"/"+"1 ("+frame+")browse");
  				fout.write(bytes);
				fout.close();				
  				  		
				System.out.println("writing");
  				//middlePanel.updateImage(coverImage);	  	
	  	}catch(Exception ex){
	  		ex.printStackTrace();
	  	}
		
		
		

	}
	
	
	public String decode(int frame,String pass){		
		
		
		BufferedImage image=images[frame];
		byte[] pixelsByte=Images.getPixels(image);
		int[] pixels=new int[pixelsByte.length];
		
		int offset = 0;
        for (int i = 0; i < pixels.length;i++) {
        	pixels[i] = pixelsByte[i] & 0xff;
        }
		
        int[] pixelPositions;
        String[] ss=pass.split(",");
        int length=ss.length;        
        
       
        
        pixelPositions=new int[length];
        
        for(int i=0;i<length;i++){
        	pixelPositions[i]=Integer.parseInt(ss[i]);
        }
        
        String[] sss=new String[length];
        for(int i=0;i<length;i++){
        	System.out.println(pixelPositions[i]);
        	sss[i]=calc(pixels[3*pixelPositions[i]],pixels[3*pixelPositions[i]+1],pixels[3*pixelPositions[i]+2]);
        }
        
        String password="";
        for(int i=0;i<length;i++){
        	password+=(char)Integer.parseInt(sss[i],2);
        }
        
        return password;
        
        
	}
	
	private String calc(int a,int b,int c){
		result="";
		binary ="";
		
		System.out.println(a+" "+b+" "+c);
		     int val = a;
		     for (int i = 0; i < 8; i++){
		        binary+=((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     //binary.append(' ');
		     result+=binary.substring(5,8); 
		 

		  binary ="";
		  val = b;
		     for (int i = 0; i < 8; i++){
		        binary+=((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     //binary.append(' ');
		  
		     result+=binary.substring(5,8);
		
		  
		  binary = "";
		     val = c;
		     for (int i = 0; i < 8; i++){
		        binary+=((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     //binary.append(' ');
		  
		     result+=binary.substring(6,8);
		return result;
		
	}

}
