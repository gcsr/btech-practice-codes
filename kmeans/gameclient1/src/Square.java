

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;



public class Square extends javax.swing.JPanel { 

	private static final long serialVersionUID = 1L;
	private JPanel squarePanel;
	private int snum; 
	
	public Square() {
		squarePanel = new JPanel(new BorderLayout());
		squarePanel.setBackground(new Color(0, 0, 0));	
		squarePanel.setBounds(0, 0, 20, 20);
	}
	
	public Square(int i) {
		squarePanel = new JPanel(new BorderLayout());
		snum = i;
		//squarePanel.setBackground(getColor(i));
	}
	
	public Color getColor(int color)
	{
		int i;
		if(color>=0 && color<64)
			i = color;
		else
			i = snum;
		if( ((i / 8) % 2) == 0)
		{
		        if( (i % 2) ==0)
		        {
		        	return Color.white;
		        } else {
		        	return Color.lightGray;
		        }
		} else {
		        if( (i % 2) ==0)
		        {
		        	return Color.lightGray;
		        } else {
		        	return Color.white;
		        }
		}
		//return null;
	}	

}
