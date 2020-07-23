package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;

public class masthan extends JFrame
{
	int id=0;
	int i=0;
	ImageIcon imicon;
	public masthan()
	{
		setTitle("dsfj");
		imicon=new ImageIcon("cc.JPG");
		JPanel pp=new JPanel();
		
		JButton b=new JButton("stop");
		pp.add(b);
		JButton s=new JButton("start");
		pp.add(s);
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				id++;
				repaint();
			}
		});
		s.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				id++;
				repaint();
			}
		});add(pp,BorderLayout.SOUTH);
		hs app=new hs();
		add(app,BorderLayout.CENTER);
	}
	
	
class hs extends JPanel
{
	
	public void paintComponent(Graphics gc)
	{
		try
		{
         
		super.paintComponent(gc);
		Graphics2D ss=(Graphics2D)gc;
			ss.setPaint(Color.cyan);
		imicon.paintIcon(this,ss,0,0);
		ss.drawPolygon(new int[]{0,100,0,100
		},new int[]{0,100,0,100
		},4);
		Thread.sleep(1000);
		float dashes[]={10};
		ss.setPaint(Color.red);
	
		i+=30;
		ss.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10,dashes,0));
		if(i>100)
		i=0;
		if(id%2==0)
		{
		ss.draw(new Line2D.Double(0,0,i,i));	
		repaint();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}


}