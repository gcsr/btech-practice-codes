import java.awt.*;
import java.applet.*;


public class fractal extends Applet
{
int a=0;
int x=10;int y=10;int r=20;int s=40;

public void paint(Graphics gc)
{
gc.drawLine(x,y,r,s);
put(x,y,r,s);
}
void put(int x,int y,int r,int s)
{

}
}