package gui;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.beans.*;
import java.awt.Image;


public class filechooserframe extends JFrame
{
	
	
	JLabel label;
	JFileChooser chooser;
	
	public filechooserframe()
	{
		setTitle("filechoosertest");
		
		JMenuBar menubar=new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menu=new JMenu("file");
		
		menubar.add(menu);
		
		JMenuItem openitem=new JMenuItem("open");
		menu.add(openitem);
		
		openitem.addActionListener(new fileopenlistener());
		
		//FileNameExtensionFilter filter=new FileNameExtensionFilter(
		//	"imageFiles","gif","jpg","jpeg");
		
		chooser=new JFileChooser();
		
		//chooser.setFileFilter(filter);
		
		
		
		openitem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		
		
		
		
		menu.add(new AbstractAction("exit")
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
			
			
		});
		label=new JLabel();
		add(label);
		
		
	}

private class fileopenlistener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		chooser.setCurrentDirectory(new File("C:/gc/multithreading/bank.java"));
		File[] ff=new File[2];
		//ff[0]=new File("synchbanktest.java");ff[1]=new File("bank.java");
		//chooser.setSelectedFiles(ff);
		chooser.setMultiSelectionEnabled(true);
		int result=chooser.showOpenDialog(filechooserframe.this);
		if(result==JFileChooser.APPROVE_OPTION)
		{
			String name=chooser.getSelectedFile().getPath();
				System.out.println("sssssss ");
			label.setIcon(new ImageIcon(name));
			//label.setText("aaaaaaaa");
		}
	}
}
}

class imagepreviewer extends JLabel
{
	public imagepreviewer(JFileChooser chooser)
	{
	setPreferredSize(new Dimension(100,100));
	setBorder(BorderFactory.createEtchedBorder());
	
	chooser.addPropertyChangeListener(new PropertyChangeListener()
	{
		public void propertyChange(PropertyChangeEvent e)
		{
			if(e.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
			{
				File f=(File)e.getNewValue();
				if(f==null)
				{
					setIcon(null);
					return;
				}
				ImageIcon icon=new ImageIcon(f.getPath());
				if(icon.getIconWidth()>getWidth()) 
				icon=new ImageIcon(icon.getImage().getScaledInstance(
					getWidth(),-1,Image.SCALE_DEFAULT));
					
							setIcon(icon);
			}
		}
	}
	);
	}
	
}