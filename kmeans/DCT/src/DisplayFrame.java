import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DisplayFrame extends JFrame{
		
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
	