import java.applet.Applet;
import java.awt.*;
import  java.awt.event.*;


public class focus extends Applet
{
public void init()
{
Button button1=new Button("button1"),
button2=new Button("button2");
buttonfocuslistener bfs=new buttonfocuslistener();

button1.addFocusListener(bfs);
button2.addFocusListener(bfs);
add(button1);
add(button2);

}

class buttonfocuslistener implements FocusListener
{

public void focusGained(FocusEvent e)
{
report(e);
}
public void focusLost(FocusEvent e)
{
report(e);
}
public void report(FocusEvent e)
{
Button b=(Button)e.getComponent();
if(e.getID()==FocusEvent.FOCUS_GAINED)
System.out.println(b.getLabel()+"gained focus");
else
if(e.getID()==FocusEvent.FOCUS_LOST)
System.out.println(b.getLabel()+"lost focus");
if(e.isTemporary())
System.out.println("temporary");
else
System.out.println("not temporary");
}
}
public void paint(Graphics gc)
{
}
}