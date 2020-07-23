import java.awt.Color;
import java.awt.*;
import java.applet.Applet;


public class colors extends Applet
{
public void paint(Graphics gc)
{
Color g=new Color(187,241,251);
gc.setColor(g);

gc.fillRect(10,10,400,400);
setBackground(Color.black);

}
}