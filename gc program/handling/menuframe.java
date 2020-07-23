package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractAction;
import javax.swing.*;
import java.awt.event.*;

public class menuframe extends JFrame
{
	public menuframe()
	{
		setTitle("menuframe");
		JMenuBar mbar=new JMenuBar();
		setJMenuBar(mbar);
		JMenu filemenu=new JMenu("file");
		mbar.add(filemenu);
			    
	    JMenuItem newitem=filemenu.add(new testaction("new"));
		filemenu.add(newitem);
		newitem.setAccelerator(KeyStroke.getKeyStroke("ctrl n"));
	
	    filemenu.addSeparator();
	    
	    JMenuItem openitem=filemenu.add(new testaction("open"));;
		filemenu.add(openitem);
		openitem.setAccelerator(KeyStroke.getKeyStroke("ctrl o"));
		
		filemenu.addSeparator();
		
		final testaction saveaction=new testaction("save");
		JMenuItem saveitem=filemenu.add(saveaction);;
		filemenu.add(saveitem);
		openitem.setAccelerator(KeyStroke.getKeyStroke("ctrl s"));
		
	    final testaction saveasaction=new testaction("save as");
		JMenuItem saveasitem=filemenu.add(saveasaction);;
		filemenu.add(saveasitem);
		saveasitem.setAccelerator(KeyStroke.getKeyStroke("ctrl a"));
        
        filemenu.add(new AbstractAction("Exit")
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.exit(0);
        	}
        });
        
        final JCheckBoxMenuItem ronly=new JCheckBoxMenuItem("read only");
        
        ronly.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
            {
        	Boolean saveok=!ronly.isSelected();
        	saveaction.setEnabled(saveok);
        	saveasaction.setEnabled(saveok);
        	
        	
        
            }
        } );       
        
        JRadioButtonMenuItem insertitem=new JRadioButtonMenuItem("insert");
        insertitem.setSelected(true);
        
        
        JRadioButtonMenuItem overtype=new JRadioButtonMenuItem("overtype");
        
        
        ButtonGroup group=new ButtonGroup();
        group.add(insertitem);
        group.add(overtype);
        
        
        Action cutaction=new testaction("cut");
        cutaction.putValue(Action.SMALL_ICON,new ImageIcon("cut.gif"));
        
        Action copyaction=new testaction("copy");
        copyaction.putValue(Action.SMALL_ICON,new ImageIcon("copy.JPEG"));
        
        Action pasteaction=new testaction("paste");
        pasteaction.putValue(Action.SMALL_ICON,new ImageIcon("paste.JPEG"));


        
        JMenu edit=new JMenu("edit");
        edit.add(ronly);
        edit.add(copyaction);
        edit.add(pasteaction);
        edit.add(cutaction);
       
        
        JMenu opmenu=new JMenu("options");
        
        
        opmenu.add(ronly);
        opmenu.addSeparator();
        opmenu.add(insertitem);
        opmenu.add(overtype);
        
        edit.add(opmenu);
        
        final JPopupMenu popup=new JPopupMenu();
        popup.add(cutaction);
        popup.add(pasteaction);
        popup.add(copyaction);
        final JPanel panel=new JPanel();
        panel.setComponentPopupMenu(popup);
        add(panel);
        panel.addMouseListener(new MouseAdapter()
        {
        	public void mouseClicked(ActionEvent e)
        	{
        		popup.show(panel,6,7);
        	}
        });
        
        mbar.add(edit);



	}
	
	
	class testaction extends AbstractAction
	{
		public testaction(String name)
		{
			super(name);
		}	
		
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(getValue(Action.NAME)+"selected.");
		}
	}
}