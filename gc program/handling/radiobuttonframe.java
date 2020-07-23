package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.*;
import javax.swing.ButtonGroup;




public class radiobuttonframe extends JFrame
{
JLabel label;
JPanel bpanel;
ButtonGroup group;

public radiobuttonframe()
{
setTitle("checkboxtest");

label=new JLabel("the quick brown fox jumps over lazy dog");
label.setFont(new Font("serif",Font.PLAIN,12));
add(label,BorderLayout.CENTER);
bpanel=new JPanel();

group=new ButtonGroup();

addradiobutton("small",8);
addradiobutton("medium",12);
addradiobutton("large",18);
addradiobutton("extralarge",36);



add(bpanel,BorderLayout.SOUTH);
}
public void addradiobutton(String name,final int size)
{
JRadioButton button=new JRadioButton(name,false);
group.add(button);
bpanel.add(button);

ActionListener listener=new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
label.setFont(new Font("serif",Font.PLAIN,size));
}
};

button.addActionListener(listener);
}
}