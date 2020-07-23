import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class DrawingFrame extends JFrame{
		public DrawingFrame(int[][] pixels){
			
			super("pixel values");
			System.out.println("in drawing frame");
			
			JTextArea textArea=new JTextArea();
			JScrollPane scroll = new JScrollPane (textArea);
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		          
		    int hLength=pixels.length<100?pixels.length:100;
		    int vLength=pixels[0].length<100?pixels[0].length:100;
		    
		    System.out.println("HLength is "+hLength);
		    System.out.println("VLength is "+vLength);
		    
		    String s="";
		    for(int i=0;i<hLength;i++){
		    	for(int j=0;j<vLength;j++){
		    		s+=(pixels[i][j]+"  ");
		    	}
		    	s+=("\n");
		    	//System.out.println("appending");
		    }
		    
		    textArea.setText(s);
		    add(scroll);		
		    System.out.println("constructor ended");
		}

}
	
