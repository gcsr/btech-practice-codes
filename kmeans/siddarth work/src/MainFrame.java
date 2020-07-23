import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class MainFrame extends JFrame{
	
	
	JPanel topPanel=new JPanel();
	int counter=0;
	Timer timer=null;
	JComboBox comboBox;
	JPanel bottomPanel;
	int rotatingAngle=0;
	boolean selected1=false;
	boolean selected2=false;
	boolean isSelected=false;
	boolean first=false;
	public MainFrame(){
		super("Geometry and Animation");
		setSize(400,400);
		setResizable(false);
		setLocation(350,230);
		
		topPanel.setLayout(new GridBagLayout());
		Border b=new EmptyBorder(10, 10, 10, 10);
		Border b2=BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		topPanel.setBorder(new CompoundBorder(b2,b));
		//topPanel.add(comp)
		setLayout(new BorderLayout());
		  //comboBox creation
        final String[] name = {"","Geometry","Animation"};

        comboBox=new JComboBox(name);
        comboBox.setMaximumRowCount(3);
        comboBox.setSelectedIndex(0);
        first=false;
        topPanel.add(comboBox);
        bottomPanel=new BottomPanel1();
        add(bottomPanel,BorderLayout.CENTER);
        add(topPanel,BorderLayout.NORTH);
        setVisible(true);
		updateDisplay();
        
        comboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {

                if(comboBox.getSelectedIndex()==0){
                	isSelected=false;
                }else if(comboBox.getSelectedIndex()==1){
                	isSelected=true;
                	first=true;
                	bottomPanel.repaint();
                }else{
                	isSelected=true;
                	first=false;
                	bottomPanel.repaint();
                }
            }
        });
	}
	
	public void updateDisplay(){
		timer = new Timer();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
			    // Your database code here
				  updateDisplay();
			  
				  if(isSelected){
					  if(!first){
						  rotatingAngle+=30;
						  if(rotatingAngle==360){
							  counter++;
							  rotatingAngle=0;
						  }
						  bottomPanel.repaint();
					  }  
				  }else{
					  bottomPanel.repaint();
				  }
				  
			  }
			}, 2*1000);
		
	}
	
	
	public static void main(String[] gcs){
		 EventQueue.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	                MainFrame centeredJLabel = new MainFrame();
	            }
	        });
	}
	
	class BottomPanel1 extends JPanel{
		
		
		public BottomPanel1(){
			
		}
		
		public synchronized void paintComponent(Graphics g) {
			
			
			g.clearRect(0, 0, getWidth(), getHeight());
			
			if(isSelected){
			
				System.out.println("paint called");
		
				if(first){
					g.clearRect(0, 0, getWidth(), getHeight());
					int width = getWidth();
					int height = getHeight();
					g.setColor(Color.GRAY);
					g.fillOval(100, 100,200, 50);
					g.fillOval(140, 130,40, 40);
					g.fillOval(220, 130,40, 40);
				}else{
					
					//	g.fillOval(120, 100,150, 150);
					BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2d = image.createGraphics();
					int w2 = getWidth() / 2;
					int h2 = getHeight() / 2;
					g2d.rotate(Math.toRadians(rotatingAngle), w2, h2);
					g2d.setColor(Color.GRAY);
					g2d.fillPolygon(new int[] {130,130, 250}, new int[] {20, 70, 70}, 3);
					g2d.fillOval(135, 60,20, 20);
					g2d.fillOval(205, 60,20, 20);		     
					//	g2d.fillOval(120, 100,150, 150);
			     
					g.setColor(Color.GRAY);
					g.drawImage(image, 0, 0, null);
					g.fillOval(120, 100,120, 120);
					g.setColor(Color.blue);
					g.drawString(""+counter, 180, 160);
					
				}
			}
		}
	}
	
	class BottomPanel2 extends JPanel{
		public BottomPanel2(){
			
		}
		
		public void paintComponent(Graphics g) {
			
			 BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			 Graphics2D g2d = image.createGraphics();
			 
            
			 int w2 = getWidth() / 2;
		     int h2 = getHeight() / 2;
		     g2d.rotate(Math.toRadians(rotatingAngle), w2, h2);
             g2d.setColor(Color.GRAY);
		     g2d.fillPolygon(new int[] {130,130, 250}, new int[] {20, 70, 70}, 3);
		     g2d.fillOval(135, 60,20, 20);
		     g2d.fillOval(205, 60,20, 20);		     
		     g2d.fillOval(120, 100,150, 150);
		     
		     
		     g.drawImage(image, 0, 0, null);
		     
		     
		     
		 }
	}
}
