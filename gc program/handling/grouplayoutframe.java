package gui;


import javax.swing.JFrame;
import java.awt.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
public class gridbagframe extends JFrame
{
	JComboBox face,size;
	JCheckBox bold,italic;
	JTextArea sample;
	public gridbagframe()
	{
		setTitle("grid bag test");
		GroupLayout layout=new GroupLayout(getContentPane();
		setLayout(layout);
		JLabel flabel=new JLabel("face:");
		face=new JComboBox(new String[]{"serif","sanserif","monospaced","dialog","dialoginput"
		});
		JLabel slabel=new JLabel("size:");
		size=new JComboBox(new String[]{"8","10","12","15","18","24","36","48"});
		bold=new JCheckBox("bold");
		italic=new JCheckBox("italic");
		sample=new JTextArea();
		JScrollPane pane=new JScrollPane(sample);
		sample.setText("the quick brown fox jumps over lazy dog");
		sample.setEditable(false);
		sample.setLineWrap(true);
		sample.setBorder(BorderFactory.createEtchedBorder());
		
		
		
	}
}