import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class itembutton extends Applet
{
public void init()
{
final Button button=new Button("button");

Choice visible=new Choice(),addremove=new Choice();;	
Panel controls=new Panel();
visible.add("show");
visible.add("hide");

addremove.add("remove");
addremove.add("add");
controls.add(visible);
controls.add(addremove);
setLayout(new BorderLayout());
add(button,"Center");
add(controls,"North");
visible.addItemListener(new ItemListener()
{
public void itemStateChanged(ItemEvent e)
{
String s=((Choice)e.getSource()).getSelectedItem();
if(s.equals("hide"))
button.setVisible(false);
else
button.setVisible(true);
}
}
);

addremove.addItemListener(new ItemListener()
{
public void itemStateChanged(ItemEvent e)
{
String s=((Choice)e.getSource()).getSelectedItem();
if(s.equals("add"))
add(button,"Center");
else
remove(button);
}
}
);

button.addComponentListener(new ComponentListener()
{
public void componentResized(ComponentEvent e)
{
Component c=(Component)e.getSource();
System.out.println("button resized:  "+c.getSize());
}
public void componentShown(ComponentEvent e)
{
System.out.println("button shown");
}
public void componentHidden(ComponentEvent e)
{
System.out.println("button hidden");
}
public void componentMoved(ComponentEvent e)
{
Component c=(Component)e.getSource();
System.out.println("button moved:  "+c.getLocation());
}
}
);

addContainerListener(new ContainerListener()
{
public void componentAdded(ContainerEvent e)
{
Component c=e.getChild();
System.out.println("button added");
}
public void componentRemoved(ContainerEvent e)
{
Component c=e.getChild();
System.out.println("button removed");

}
}
);

}
public void paint(Graphics gc)
{
}
}