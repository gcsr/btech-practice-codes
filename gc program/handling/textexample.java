import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;


public class textexample extends Applet
{
private TextField tfield=new TextField(25);
private TextArea tarea=new TextArea(10,20);
public void init()
{
add(tarea);
add(tfield);

tfield.addTextListener(new debugtextlistener());
tarea.addTextListener(new debugtextlistener());
}
class debugtextlistener implements TextListener{
public void textValueChanged(TextEvent e)
{
Object obj=e.getSource();
System.out.println(obj.toString());
}
}
}