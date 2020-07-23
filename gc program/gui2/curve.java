import java.applet.*;
import java.awt.*;

public class curve extends Applet
{
public void paint(Graphics gc)
{
//for(int x=1;x<1200;x++)
//gc.drawOval((x-1),f(x-1),x,f(x));
//gc.drawLine(19,f(x-1),20,f(x));
//gc.drawLine(1,1,25,25);
//setForeground(Color.WHITE);
setBackground(new Color(58,85,190));
for(int x=1;x<1200;x++)
{
gc.setColor(Color.WHITE);
for(int i=0;i<x;i++)
gc.drawLine(((x-1)),(f(x-1,i)),(x),(f(x,i)));
//gc.drawLine((600+(x-1)),(400+f(x-1)),(600+x),(400+f(x)));
}
//System.out.println("s");
}
int f(int x,int i)
{
int p=1;
while(i>0)
{
p=p*x;
i--;
}

return ((x*x*x*x*x*x*x*x*x)%1200);
}
}