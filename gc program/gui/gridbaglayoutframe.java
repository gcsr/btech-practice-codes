package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;

public class gridbaglayoutframe extends JFrame
{
	public gridbaglayoutframe()
	{
		setTitle("gridbag layouttest");
		
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		
		JLabel facelabel=new JLabel("face");
	}
	
}