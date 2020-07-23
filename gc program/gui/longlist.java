package gui;

import javax.swing.JFrame;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Container;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class longlist extends JFrame
{
	JList wordlist;
	JLabel label;
	String prefix="the quick brown";
	String suffix="jumps over the lazy dog";
	public longlist()
	{
		setTitle("longlist");
		wordlist=new JList(new wordlistmodel(3));
		wordlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		wordlist.setPrototypeCellValue("www");
		JScrollPane scrollpane=new JScrollPane(wordlist);
		
		JPanel p=new JPanel();
		p.add(scrollpane);
		Container contentpane=getContentPane();
		contentpane.add(p,BorderLayout.NORTH);
		label=new JLabel(prefix+"fox"+suffix);
		contentpane.add(label,BorderLayout.CENTER);
		
		
	}
	class wordlistmodel extends AbstractListModel
	{
		int length;
		
		char first='a';
		char last='z';
		
		public wordlistmodel(int n)
		{
			length=n;
		}
		public int getSize()
		{
			return (int)Math.pow(26,length);
		}
		public Object getElementAt(int n)
		{
			StringBuilder r=new StringBuilder();
			for(int i=0;i<length;i++)
			{
				char c=(char)(first+n%(last-first+1));
				r.insert(0,c);
				n=n/(last-first+1);
			}
			return r;
		}
		
	}
}