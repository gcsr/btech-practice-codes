import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class DrawingFrame extends JFrame implements WindowListener
	{
		public DrawingFrame(int[][] pixels)
		{
			
			super("pixel values");
			setSize(1400,1200);
			JTextArea textArea=new JTextArea();
			JScrollPane scroll = new JScrollPane (textArea);
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		          
		    int hLength=pixels.length;
		    int vLength=pixels[0].length;
		    
		    for(int i=0;i<hLength;i++)
		    {
		    	for(int j=0;j<vLength;j++)
		    	{
		    		textArea.append(pixels[i][j]+"  ");
		    	}
		    	textArea.append("\n");
		    }
		    add(scroll);

			setVisible(true);
			
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			arg0.getWindow().dispose();
			
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
