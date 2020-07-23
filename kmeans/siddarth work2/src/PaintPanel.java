import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class PaintPanel extends JPanel{
	
	int rotations=0;
	int angle=0;
	boolean draw1=false;
	boolean draw2=false;
	boolean draw3=false;
	
	
	public synchronized void paintComponent(Graphics g) {
		
		//System.out.println(draw1);
		//System.out.println(draw2);
		//System.out.println(draw3);
		
		if(draw1){
			draw1(g);
		}
		else if(draw2){
			draw2(g);
		}
		else if(draw3){
			draw3(g);
		}
		
		
		
		//repaint();
		
	}
	
	public void draw1(Graphics g){
		g.clearRect(0, 0, getWidth(), getHeight());
		System.out.println("drawing 1");
	}
	public void draw2(Graphics g){
		g.clearRect(0, 0, getWidth(), getHeight());
		System.out.println("drawing 2");
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.GRAY);
		int[] xpoints=new int[]{50,50, 450};
		int[] ypoints=new int[]{20, 270, 270};
		g.fillPolygon(xpoints ,ypoints , 3);
		g.fillOval(85, 240,50, 50);
		g.fillOval(350, 240,50, 50);
		
	}
	public void draw3(Graphics g){
		System.out.println("drawing 3");
		g.clearRect(0, 0, getWidth(), getHeight());
		 BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		 Graphics2D graphics = image.createGraphics();
		
	     graphics.rotate(Math.toRadians(angle), getWidth()/2, getHeight()/2);
	     graphics.setColor(Color.GRAY);
	     graphics.fillPolygon(new int[] {220,220, 240,260,280,260,240}, new int[] {90,130,120,120,110,100,100}, 7);
         g.drawImage(image, 0, 0, null);
	     g.setColor(Color.GRAY);	    
	     g.fillOval(160, 135,180, 180);
	     g.setColor(Color.orange);
	     g.drawString(""+rotations, 245, 235);
	     angle+=45;
		 if(angle==360){
				rotations++;
				angle=0;
		}
			
	     
	    
	}

	public int getRotations() {
		return rotations;
	}

	public void setRotations(int rotations) {
		this.rotations = rotations;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public boolean isDraw1() {
		return draw1;
	}

	public void setDraw1(boolean draw1) {
		System.out.println("draw1");
		draw1=false;
		draw2=false;
		draw3=false;
		this.draw1 = true;
		
	}

	public boolean isDraw2() {
	
		return draw2;
	}

	public void setDraw2(boolean draw2) {
		System.out.println("draw2");
		draw1=false;
		draw2=false;
		draw3=false;
		this.draw2 = true;
	}

	public boolean isDraw3() {
		return draw3;
	}

	public void setDraw3(boolean draw3) {
		System.out.println("draw3");
		draw1=false;
		draw2=false;
		draw3=false;
		this.draw3 = true;
	}
}
