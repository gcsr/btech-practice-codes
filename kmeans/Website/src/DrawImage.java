import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class DrawImage extends JPanel implements MouseListener 
{
	final private String defaultImage="D:/tomcat/webapps/nolimits/images/defaultcut.jpg";
	BufferedImage image;
	private ImageIcon picture;	
	private int[] xValues;
	byte[] pixels;
	int height;
	int width;
	String cond="left";
	int a=0;
	int b=0;

	public DrawImage()
	{
				
		try
		{
		    image = ImageIO.read(new File(defaultImage));
		    System.out.println("image type is "+image.getType());
		    height=image.getHeight();
			width=image.getWidth();
		    pixels = (byte[]) image.getData().getDataElements(0, 0, width, height, null);
		    System.out.println("pixels size is "+pixels.length);
		    
			
			System.out.println("height "+height+" width "+width);
		}
		catch (IOException e) 
		{
		}
		
		addMouseListener(this);
		//addActionListener(this);
	}	
	
	
	public void updateImage(String imageURL1)
	{
		
		BufferedImage img = null;
		try
		{
		    image = ImageIO.read(new File(imageURL1));
		    pixels = (byte[]) image.getData().getDataElements(0, 0, width, height, null);
		    System.out.println("pixels size is "+pixels.length);
		    height=image.getHeight();
			width=image.getWidth();
		}
		catch (IOException e) 
		{
		}
		
		repaint();
		
		
	}
	
	
	public void updateImage(BufferedImage image){		
		this.image=image;		
		repaint();		
	}
		
	public void cropImage(){
		
		//image.getS
		//image=image.getSubimage(40,50, image.getWidth()-80, image.getHeight()-70);
		//image=image.getSubimage(40,50, image.getWidth()-80, 100);
		//image=image.getSubimage(40,60, image.getWidth()-80,100);
		//image=image.getSubimage(40,50,100,120);
		repaint();
	}
	
	public void cropImageTop(){
		
		image=image.getSubimage(0,10, image.getWidth(), image.getHeight()-10);
		//image=image.getSubimage(40,50, image.getWidth()-80, 100);
		//image=image.getSubimage(40,60, image.getWidth()-80,100);
		//image=image.getSubimage(40,50,100,120);
		repaint();
	}
	
	public void cropImageBottom(){
		
		image=image.getSubimage(0,0, image.getWidth(), image.getHeight()-10);
		//image=image.getSubimage(40,50, image.getWidth()-80, 100);
		//image=image.getSubimage(40,60, image.getWidth()-80,100);
		//image=image.getSubimage(40,50,100,120);
		repaint();
	}
	
	public void cropImageLeft(){
		
		image=image.getSubimage(10,0, image.getWidth()-10, image.getHeight());
		//image=image.getSubimage(40,50, image.getWidth()-80, 100);
		//image=image.getSubimage(40,60, image.getWidth()-80,100);
		//image=image.getSubimage(40,50,100,120);
		repaint();
	}
	
	public void cropImageRight(){
		
		image=image.getSubimage(0,0, image.getWidth()-10, image.getHeight());
		//image=image.getSubimage(40,50, image.getWidth()-80, 100);
		//image=image.getSubimage(40,60, image.getWidth()-80,100);
		//image=image.getSubimage(40,50,100,120);
		repaint();
	}
	
	
	public void lineLeftCut(){		
				
		cond="left";
		xValues=new int[height];
		
		int y=0;
		while(y<height){
			xValues[y]=xCoordinate(y);
			y++;
		}
		
		change();
		BufferedImage edgesImage=null;
		
		if (edgesImage == null) {
			edgesImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}
		//System.out.println("pixels size is "+pixels.length);
		edgesImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, pixels);
		
		image=edgesImage;
		repaint();
		
	}
	
	
	public void lineRightCut(){
		cond="right";
		xValues=new int[height];
		
		int y=0;
		while(y<height){
			xValues[y]=xCoordinate(y);
			y++;
		}
		
		change();
		BufferedImage edgesImage=null;
		
		if (edgesImage == null) {
			edgesImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}
		//System.out.println("pixels size is "+pixels.length);
		edgesImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, pixels);
		
		image=edgesImage;
		repaint();
		
	}
	
	private void change(){
		int length=xValues.length;
		
		for(int i=0;i<length;i++){
			clear(xValues[i],i);
		}
	}
	
	private void clear(int xValue,int i){
			
		int ht=(height-i-1);
		int p=0;
		//System.out.println("ht is "+ht);
		
		
		for(int x=0;x<xValue;x++){
			//System.out.println("x is "+x);
			if(cond.equals("left")){
				p=(ht*width+x);
				pixels[3*p]=(byte)255;
				pixels[3*p+1]=(byte)255;
				pixels[3*p+2]=(byte)255;
			}
			else{
				p=((ht+1)*width-x-1);
				pixels[3*p]=(byte)255;
				pixels[3*p+1]=(byte)255;
				pixels[3*p+2]=(byte)255;
			}
			
		}
		
	}
	
	public void saveImage(String filename){
		File outputfile = new File(filename);			    
		try {
			ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		
	public BufferedImage getImage(){
		return image;
	}
	
		
	public void paintComponent(Graphics gc)
	{
		
		super.paintComponent(gc);
		Insets i=getInsets();
		System.out.println("on paint");
		
		
		int width=image.getWidth();
		int height=image.getHeight();
		
		int boxWidth=width/10;
		int boxLength=height/10;
		
		gc.drawImage(image,i.left,i.right,this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX()+" "+e.getY());
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void line(int a,int b){
		this.a=a;
		this.b=b;
	}
	
	
	
	private int xCoordinate(int y){
		
		int  width=a*y+b;
		return width;
	}


}
