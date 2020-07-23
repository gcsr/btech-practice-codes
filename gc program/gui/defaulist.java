package gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;
public class defaulist extends JFrame
{
	
	public defaulist()
	{
		setTitle("defaultlist");
		DefaultListModel model=new DefaultListModel();
		model.addElement("dskjf");
		JList list=new JList(model);
		add(list);
		model.addElement("sdfjsdlkfj");
	}	
}		
		