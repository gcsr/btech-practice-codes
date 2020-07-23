package gui;

import javax.swing.JFrame;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Container;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextArea;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.ListCellRenderer;
public class listrendering extends JFrame
{
	JTextArea text;
	JList fontlist;
	JLabel label;
	String prefix="the quick brown";
	String suffix="jumps over the lazy dog";
	public listrendering()
	{
		setTitle("longlist");
		
		ArrayList<Font> fonts=new ArrayList<Font>();
		int SIZE=24;
		fonts.add(new Font("Serif",Font.PLAIN,SIZE));
		fonts.add(new Font("SansSerif",Font.PLAIN,SIZE));
		fonts.add(new Font("Monospaced",Font.PLAIN,SIZE));
		fonts.add(new Font("Dialog",Font.PLAIN,SIZE));
		fonts.add(new Font("DialogInput",Font.PLAIN,SIZE));
		fontlist=new JList(fonts.toArray());
		fontlist.setVisibleRowCount(4);
		fontlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontlist.setCellRenderer(new fontcellrenderer());
		
		JScrollPane scrollpane=new JScrollPane(fontlist);
		
		JPanel p=new JPanel();
		p.add(scrollpane);
		
		fontlist.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				Font font=(Font)fontlist.getSelectedValue();
				text.setFont(font);
			}
		});
		Container contentpane=getContentPane();
		text=new JTextArea("the quick brown fox jumps over the lazy dog");
		text.setFont((Font)fonts.get(0));
		contentpane.add(p,BorderLayout.NORTH);
		contentpane.add(text,BorderLayout.CENTER);
		
		
	}
	class fontcellrenderer extends JPanel implements ListCellRenderer
	{
		public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellhasfocus)
		{
			System.out.println("component getlistrenderercomponent");
			font=(Font)value;
			background=isSelected?list.getSelectionBackground():list.getBackground();
			foreground=isSelected?list.getSelectionForeground():list.getForeground();
			return this;
		}
		public void paintComponent(Graphics gc)
		{
			System.out.println("paintcomponent");
			String text=font.getFamily();
			FontMetrics fm=gc.getFontMetrics(font);
			gc.setColor(background);
			gc.fillRect(0,0,getWidth(),getHeight());
		    gc.setColor(foreground);
			gc.setFont(font);
			gc.drawString(text,0,fm.getAscent());
		}
		public Dimension getPreferredSize()
		{
			System.out.println("getpreferredsize");
			String text=font.getFamily();
			Graphics gc=getGraphics();
			FontMetrics fm=gc.getFontMetrics(font);
			return new Dimension(fm.stringWidth(text),fm.getHeight());
			
		}
		private Font font;
		private Color background,foreground;
	}

}