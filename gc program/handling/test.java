import java.applet.Applet;
import java.awt.*;

import java.awt.event.*;

public class test extends Applet
{
public void init()
{
Button eventsource=new Button("event source");
eventsource.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
String s=e.getActionCommand();
System.out.println(s);
System.out.println(e.getModifiers());
System.out.println(e.SHIFT_MASK);
}
}
);

eventsource.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
String s=e.getActionCommand();
System.out.println(s);
System.out.println(e.getModifiers());
System.out.println(e.SHIFT_MASK);
}
}
);
add(eventsource);
//ActionListener
//System.out.println(new ActionListener());
//eventsource.removeActionListener(eventsource.ActionListener());
}
}