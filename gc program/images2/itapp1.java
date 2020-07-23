import java.applet.Applet;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;

public class itapp1 extends Applet{

private Image im;
public void init()
{
URL codebase=getCodeBase();
System.out.println(codebase);

im=getImage(codebase,"i2.jpg");
}
public void paint(Graphics gc)
{
System.out.println(gc.drawImage(im,0,0,this));
gc.drawImage(im,0,0,this);
}
}