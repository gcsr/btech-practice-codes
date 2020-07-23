package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import java.awt.Frame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class colorchooserframe extends JFrame
{
	public colorchooserframe()
	{
		setTitle("colorchoosertest");
		colorchooserpanel panel=new colorchooserpanel();
		add(panel);
	}
}

class colorchooserpanel extends JPanel
{
	JColorChooser chooser;
	JDialog dialog;
	public colorchooserpanel()
	{
		JButton modelbutton=new JButton("modal");
		modelbutton.addActionListener(new modellistener());
		add(modelbutton);
		
		JButton modellessbutton=new JButton("modelless");
		modellessbutton.addActionListener(new modellesslistener());
		add(modellessbutton);
		
		JButton immediatebutton=new JButton("immediate");
	    immediatebutton.addActionListener(new immediatelistener());
		add(immediatebutton);
	}

	private class modellistener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Color dcolor=getBackground();
			Color selected=JColorChooser.showDialog(colorchooserpanel.this,
			"set background",dcolor);
			if(selected!=null)setBackground(selected);
		}
	}
	private class modellesslistener implements ActionListener
	{
		public modellesslistener()
		{
			chooser=new JColorChooser();
			dialog=JColorChooser.createDialog(colorchooserpanel.this,"background color",
			false,chooser,new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					setBackground(chooser.getColor());
				}
			},null);
		}
		public void actionPerformed(ActionEvent e)
		{
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}
	}
	private class immediatelistener implements ActionListener
	{
		public immediatelistener()
		{
			chooser=new JColorChooser();
			chooser.getSelectionModel().addChangeListener(new ChangeListener()
			{
				public void stateChanged(ChangeEvent e)
				{
					setBackground(chooser.getColor());
				}
			});
			dialog=new JDialog((Frame)null,false);
			dialog.add(chooser);
			dialog.pack();
		}
		public void actionPerformed(ActionEvent e)
		{
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}
	}
}