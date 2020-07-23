import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DecoderPlayerScreen extends JPanel{
	boolean play=false;
	BufferedImage[] images;
	int counter=0;
	int frameNo=0;
	
	int[] pixelPositions;
	String result="";
	String binary="";
	
	public void update(String dir){
		File f=new File(dir);
		String[] names=f.list();		
		images=new BufferedImage[names.length];
		frameNo=names.length;
		JOptionPane.showConfirmDialog(this,"avialable frame  are "+frameNo);
		int j=0;
		try{
			for(int i=0;i<images.length;i++){
					String fName=dir+"/"+names[i];
					if(fName.contains("browse")){						
						continue;
					}					
					String fffName=fName.substring(0,fName.lastIndexOf('.')-1)+")browse";
					System.out.println(fffName);
					File ffff=new File(fffName);
					if(ffff.exists()){
						images[j] =getImage(fffName,fName);
						j++;
					}
					else{
						images[j] = ImageIO.read(new File(fName));
						j++;
					}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
				
		}
		
		System.out.println(" result "+j);
	
	}
	public DecoderPlayerScreen(String dir){
		
		File f=new File(dir);
		String[] names=f.list();		
		images=new BufferedImage[names.length];
		frameNo=names.length;
		JOptionPane.showConfirmDialog(this,"avialable frame  are "+frameNo);
		int j=0;
		try{
			for(int i=0;i<images.length;i++){
					String fName=dir+"/"+names[i];
					if(fName.contains("browse")){						
						continue;
					}					
					String fffName=fName.substring(0,fName.lastIndexOf('.')-1)+")browse";
					System.out.println(fffName);
					File ffff=new File(fffName);
					if(ffff.exists()){
						images[j] =getImage(fffName,fName);
						j++;
					}
					else{
						images[j] = ImageIO.read(new File(fName));
						j++;
					}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
				
		}
		
		System.out.println(" result "+j);
	}
	
	
	public BufferedImage getImage(String fName,String name){		
		try{
			System.out.println("in get image for"+name+" "+fName);
			BufferedImage imge = ImageIO.read(new File(name));
		
			FileInputStream fin=new FileInputStream(fName);		
			int offset=0;
			
			int fff=imge.getWidth()*imge.getHeight()*3;
			
			byte[] data = new byte[fff];
			int bytesRead = 0;
			
			while (offset < fff) {					
				bytesRead = fin.read(data, offset, data.length-offset);
				if (bytesRead == -1) break;
				offset += bytesRead;
			}
			fin.close();
			BufferedImage finalImage=null;
			finalImage = new BufferedImage(imge.getWidth(),imge.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			finalImage.getWritableTile(0, 0).setDataElements(0, 0,imge.getWidth(),imge.getHeight(), data);
			
			return finalImage;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
			
		
		
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
	
		
	
	public String decode(int frame,String pass){		
		System.out.println("in decode");		
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
