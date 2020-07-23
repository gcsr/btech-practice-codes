package gui;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class jlist extends JFrame
{
	JPanel listpanel,buttonpanel;
	JList wordlist;
	private String prefix="the",suffix="fox jumps over the lazy dog";
	private ButtonGroup group;
	private JLabel label;
	
	public jlist()
	{
		setTitle("pora moddaga");
		String[] words={
			"quick","brown","hungry","wild","silent",
			"huge","private","abstract","static","final"
		};
		wordlist=new JList(words);
		wordlist.setVisibleRowCount(4);
		JScrollPane scrollpane=new JScrollPane(wordlist);
		listpanel=new JPanel();
		listpanel.add(scrollpane);
		add(listpanel,BorderLayout.NORTH);
		wordlist.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				
				Object[] values=wordlist.getSelectedValues();
				
				StringBuilder text=new StringBuilder(prefix);
				
				for(int i=0;i<values.length;i++)
				{
					String word=(String)values[i];
					text.append(word);
					text.append(" ");
				}
				text.append(suffix);
				label.setText(text.toString());
			}
		});
		
		buttonpanel=new JPanel();
		group=new ButtonGroup();
		makebutton("vertical",JList.VERTICAL);
		makebutton("verticalwrap",JList.VERTICAL_WRAP);
		makebutton("horizontalwrap",JList.HORIZONTAL_WRAP);
		
		label=new JLabel(prefix+suffix);
		add(label,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
	}
	
	private void makebutton(String label,final int orientation)
	{
		JRadioButton button=new JRadioButton(label);
		buttonpanel.add(button);
		group.add(button);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				wordlist.setLayoutOrientation(orientation);
				listpanel.revalidate();
			}
		});
		
		
		
	}
}