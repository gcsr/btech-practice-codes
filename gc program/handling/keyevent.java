import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class keyevent extends Applet
{
public void init()
{
TextField tf=new TextField(10);
add(tf);
tf.addKeyListener(new KeyListener()
{
public void keyPressed(KeyEvent e)
{
System.out.println("key pressed");
report(e);
}
public void keyReleased(KeyEvent e)
{
System.out.println("key released");
report(e);
}
public void keyTyped(KeyEvent e)
{
System.out.println("key typed");
report(e);
}
public void report(KeyEvent e)
{
int kcode=e.getKeyCode();
char kchar=e.getKeyChar();
String mods=e.getKeyModifiersText(kcode);
String txt=e.getKeyText(kcode);

//if(kcode!=KeyEvent.KEY_UNDEFINED)
//System.out.println(kcode+"  ");

if(kcode!=KeyEvent.CHAR_UNDEFINED)
System.out.println(kchar+"  ");

System.out.println("modifiers: "+mods);

if(e.isActionKey())
System.out.println("action");

}
}
);
}
}