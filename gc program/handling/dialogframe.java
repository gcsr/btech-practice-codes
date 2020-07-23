package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.JPanel;

public class dialogframe extends JFrame
{
	aboutdialog dialog;
	public dialogframe()
	{
		
		dialog=new aboutdialog(dialogframe.this);
		dialog.setVisible(true);
		
	}
}	
	class aboutdialog extends JDialog
	{
		public aboutdialog(JFrame owner)
		{
			super(owner,"aboutdialogtest",true);
			add(new JLabel("wwwwwwwwwwwwwwwwwwwww"));
			
			JButton ok=new JButton("ok");
			ok.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
				}
			});
			JPanel panel=new JPanel();
			panel.add(ok);
			add(panel,BorderLayout.SOUTH);
			setSize(450,350);
		}
	}
