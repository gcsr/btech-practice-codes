package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class toolbarframe extends JFrame
{
	JPanel panel;
	
	public toolbarframe()
    {

	setTitle("toolbar frame");
	panel=new JPanel();
	add(panel,BorderLayout.CENTER);
	JToolBar bar=new JToolBar();
	JMenuBar mbar=new JMenuBar();
	JMenu menu=new JMenu("Color");
	mbar.add(menu);
	setJMenuBar(mbar);
	Action blueaction=new coloraction("blue",new ImageIcon("blue.gif"),Color.blue);
	Action yellowaction=new coloraction("yellow",new ImageIcon("yelow.gif"),Color.yellow);
	Action redaction=new coloraction("red",new ImageIcon("red.gif"),Color.red);
	menu.add(new AbstractAction("exit")
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	});
	bar.add(new AbstractAction("exit")
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	});
	bar.add(blueaction);
	bar.add(yellowaction);
	bar.add(redaction);
	
	menu.add(yellowaction);
	menu.add(redaction);
	menu.add(blueaction);
	add(bar,BorderLayout.NORTH);
    }
    class coloraction extends AbstractAction
    {
    	public coloraction(String name,Icon icon,Color c)
    	{
    		putValue(Action.NAME,name);
    		putValue(Action.SMALL_ICON,icon);
    		putValue(Action.SHORT_DESCRIPTION,name+"background");
    		putValue("gc",c);
    	}
    	public void actionPerformed(ActionEvent e)
    	{
    		Color c=(Color)getValue("gc");
    		panel.setBackground(c);
    	}
    }
}