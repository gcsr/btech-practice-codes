import java.applet.Applet;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;

public class itapp extends Applet{

private Image im;
public void init()
{
URL codebase=getCodeBase();
System.out.println(codebase);

im=getImage(codebase,"i2.jpg");
}
public void paint(Graphics gc)
{
gc.drawImage(im,0,0,this);
}
}