import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class consumeexample extends Applet
{
public void init()
{
Button button=new Button("can't click");

button.addMouseListener(new consumebuttonlistener(this));
addKeyListener(new consumekeylistener(this));
requestFocus();
add(button);
}
class consumebuttonlistener extends MouseAdapter
{
private consumeexample applet;
public consumebuttonlistener(consumeexample applet)
{
this.applet=applet;
}
public void mousePressed(MouseEvent e)
{
applet.showStatus("consuming button press");
e.consume();
System.out.println("this is gc");
}
}
class consumekeylistener extends KeyAdapter
{
private consumeexample applet;
public consumekeylistener(consumeexample applet)
{
System.out.println("this is a");
this.applet=applet;
}
public void keyPressed(KeyEvent e)
{
applet.showStatus("consuming a key");
char key=e.getKeyChar();
e.consume();
//if(key=='a')
//{
//e.consume();
//}
}
}
}