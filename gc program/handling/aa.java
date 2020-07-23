import java.applet.Applet;
import java.awt.Graphics;


public class aa extends Applet
{
public void paint(Graphics gc)
{
int y[]={100,200,300,250};
int x[]={10,20,30,40};
//gc.draw3DPolygon(x,y,4,true);
gc.draw3DRect(0,0,100,100,true);

}
}