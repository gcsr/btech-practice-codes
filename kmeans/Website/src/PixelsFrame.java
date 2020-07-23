import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PixelsFrame extends JFrame{

	
		public PixelsFrame(BufferedImage img,int x,int y)
		{
			
			super("pixel values");
			setSize(1000,800);
			JTextArea textArea=new JTextArea();
			JScrollPane scroll = new JScrollPane (textArea);
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		          
		    int startX=x-10;
		    int startY=y-10;
		    
		    int endX=x+10;
		    int endY=y+10;
		    
		    Raster raster=img.getRaster();
		    
		    for(int i=startY;i<endY;i++)
		    {
		    	for(int j=startX;j<startY;j++)
		    	{
		    		textArea.append(raster.getSample(j,i,0)+"  ");
		    	}
		    	textArea.append("\n");
		    }
		    add(scroll);

			setVisible(true);
			
		}
		
}
