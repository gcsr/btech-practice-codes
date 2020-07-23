package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.awt.event.*;





public class checkboxframe extends JFrame
{
JLabel label;
JCheckBox bold,italic;

public checkboxframe()
{
setTitle("checkboxtest");

label=new JLabel("the quick brown fox jumps over lazy dog");
label.setFont(new Font("serif",Font.PLAIN,12));
add(label,BorderLayout.CENTER);
JPanel bpanel=new JPanel();

bold=new JCheckBox("bold");
italic=new JCheckBox("italic");

bold.addActionListener(listener);
italic.addActionListener(listener);

bpanel.add(bold);
bpanel.add(italic);

add(bpanel,BorderLayout.SOUTH);
}
ActionListener listener=new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
int mode=0;
if(bold.isSelected())mode+=Font.BOLD;
if(italic.isSelected())mode+=Font.ITALIC;
label.setFont(new Font("serif",mode,12));
}
};
}