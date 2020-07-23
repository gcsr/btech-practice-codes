package gui;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JPanel;



public class simpledialog extends JFrame
{
	public simpledialog()
	{
		ButtonGroup group=new ButtonGroup();
		JRadioButton ss=new JRadioButton("why");
		JRadioButton st=new JRadioButton("what");
		JRadioButton su=new JRadioButton("where");
		
		
		JPanel p=new JPanel();
		group.add(ss);
		group.add(st);
		group.add(su);
		ss.setSelected(true);
		p.add(ss);
		p.add(st);
		p.add(su);
		add(p);
		try
		{
	
	//	System.out.println(JOptionPane.class.getField("why").getInt(null));
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Object[] options={"era","pora","emy"};
		Object object="kkkkk";
		JOptionPane.showInternalInputDialog(this,(Object)(new ImageIcon("blue.gif")),"gc",1);//,new ImageIcon("blue.gif"),options,object);
		//JOptionPane.showInternalInputDialog(this,(Object)"era",(Object)"default");
		
	}
}