package gui;


import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.*;


public class comboboxframe extends JFrame
{
JLabel label;
JComboBox box;
public comboboxframe()
{
setTitle("comboboxframe");

label=new JLabel("the quick brown fox jumps over the lazy dog");
add(label,BorderLayout.CENTER);

String[] fontnames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

box=new JComboBox();
box.setEditable(true);

for(String s:fontnames)
box.addItem(s);

box.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
label.setFont(new Font((String)box.getSelectedItem(),Font.PLAIN,12));
}
}
);
JPanel panel=new JPanel();
panel.add(box);
add(panel,BorderLayout.SOUTH);
}
}


