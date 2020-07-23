import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;


public class mouseevent2 extends Applet
{
public void init()
{
addMouseListener(
new MouseAdapter()
{
public void mouseEntered(MouseEvent e)
{
System.out.println("mouse entered");
}
public void mouseExited(MouseEvent e)
{
System.out.println("mouse excited");
}
public void mouseClicked(MouseEvent e)
{
System.out.println("mouse clicked");
int mods=e.getModifiers();
System.out.println(mods);
System.out.println(InputEvent.BUTTON1_MASK);
System.out.println(InputEvent.BUTTON2_MASK);
System.out.println(InputEvent.BUTTON3_MASK);

if(((mods)&(InputEvent.BUTTON2_MASK))!=0)
System.out.println("button 2");
else if(((mods)&(InputEvent.BUTTON3_MASK))!=0)
System.out.println("button 3");
else if(((mods)&(InputEvent.BUTTON1_MASK))!=0)
System.out.println("button 1");
}
}
);
}

}