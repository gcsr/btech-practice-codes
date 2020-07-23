
package p1;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GridBagFrame extends JFrame
{
private GridBagLayout layout;
private GridBagConstraints constraints;

public GridBagFrame()
{
super("Grid bag layout");

layout=new GridBagLayout();
setLayout(layout);
constraints=new GridBagConstraints();

JTextArea textarea1=new JTextArea("text area 1",5,10);
JTextArea textarea2=new JTextArea("text area 2",2,2);


String names[]={"iron","steel","brass"};
JComboBox combobox=new JComboBox(names);

JTextField textfield=new JTextField("textfield");
JButton button1=new JButton("button1");
JButton button2=new JButton("button2");
JButton button3=new JButton("button3");

constraints.fill=GridBagConstraints.BOTH;
addcomponent(textarea1,0,0,1,3);

constraints.fill=GridBagConstraints.HORIZONTAL;
addcomponent(button1,0,1,2,1);

addcomponent(combobox,2,1,2,1);

constraints.weightx=1000;
constraints.weighty=0;
constraints.fill=GridBagConstraints.BOTH;
addcomponent(button2,1,1,1,1);

constraints.weightx=0;
constraints.weighty=0;
addcomponent(button3,1,2,1,1);

addcomponent(textfield,3,0,2,1);

addcomponent(textarea2,3,2,1,1);


}
private void addcomponent(Component component,int row,int column,int width,int height)
{
constraints.gridx=column;
constraints.gridy=row;
constraints.gridwidth=width;
constraints.gridheight=height;

layout.setConstraints(component,constraints);
add(component);
}

}