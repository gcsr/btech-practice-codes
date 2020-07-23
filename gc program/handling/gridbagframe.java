package gui;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

public class gridbagframe extends JFrame
{
	JComboBox face,size;
	JCheckBox bold,italic;
	JTextArea sample;
	public gridbagframe()
	{
		setTitle("grid bag test");
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		JLabel flabel=new JLabel("face:");
		face=new JComboBox(new String[]{"serif","sanserif","monospaced","dialog","dialoginput"
		});
		JLabel slabel=new JLabel("size:");
		size=new JComboBox(new String[]{"8","10","12","15","18","24","36","48"});
		bold=new JCheckBox("bold");
		italic=new JCheckBox("italic");
		sample=new JTextArea();
		sample.setText("the quick brown fox jumps over lazy dog");
		sample.setEditable(false);
		sample.setLineWrap(true);
		sample.setBorder(BorderFactory.createEtchedBorder());
		
		add(flabel,new gbc(0,0).setAnchor(gbc.EAST).setWeight(1,1));
		add(face,new gbc(1,0).setFill(gbc.HORIZONTAL).setWeight(100,0).setInsets(2));
		add(slabel,new gbc(0,1).setAnchor(gbc.EAST));
		add(size,new gbc(1,1).setAnchor(gbc.EAST).setWeight(100,100));
		add(bold,new gbc(0,2,2,1).setAnchor(gbc.CENTER).setWeight(100,100));
		add(italic,new gbc(0,3,2,1).setAnchor(gbc.CENTER).setWeight(100,100));
		add(sample,new gbc(2,0,1,4).setFill(gbc.BOTH).setWeight(100,100));
		
		
	}
}