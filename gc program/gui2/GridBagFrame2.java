
package p1;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JButton;



public class GridBagFrame2 extends JFrame
{
private GridBagLayout layout;
private GridBagConstraints constraints;

public GridBagFrame2()
{
super("grid bag layout 2");
layout=new GridBagLayout();
setLayout(layout);
constraints=new GridBagConstraints();

String metals[]={"copper","aluminium","silver"};
JComboBox combobox=new JComboBox(metals);

JTextField textfield=new JTextField("textfield");


String fonts[]={"serif","monospaced"};
JList list=new JList(fonts);


String names[]={"zero","one","two","three","four"};
JButton buttons[]=new JButton[names.length];

for(int count=0;count<buttons.length;count++)
buttons[count]=new JButton(names[count]);


constraints.weightx=1;
constraints.weighty=1;
constraints.fill=GridBagConstraints.BOTH;
constraints.gridwidth=1;
constraints.gridwidth=GridBagConstraints.REMAINDER;
addcomponent(textfield);


constraints.gridwidth=1;
addcomponent(buttons[0]);

constraints.gridwidth=GridBagConstraints.RELATIVE;
addcomponent(buttons[1]);

constraints.gridwidth=GridBagConstraints.REMAINDER;
addcomponent(buttons[2]);

constraints.weighty=0;
constraints.gridwidth=GridBagConstraints.REMAINDER;
addcomponent(combobox);

constraints.gridwidth=GridBagConstraints.REMAINDER;
addcomponent(buttons[3]);

constraints.gridwidth=GridBagConstraints.RELATIVE;
addcomponent(buttons[4]);

constraints.gridwidth=GridBagConstraints.REMAINDER;
addcomponent(list);

}

public void addcomponent(Component component)
{
layout.setConstraints(component,constraints);
add(component);
}
}