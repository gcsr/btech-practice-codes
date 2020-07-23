import java.applet.*;
import java.awt.*;

public class curve2 extends Applet
{
public void paint(Graphics gc)
{
int i=1;
setBackground(new Color(58,85,190));
setForeground(Color.WHITE);
for(int x=1;x<1200;x++)
{
gc.drawLine(((x-1)),(f((x-1),i)),(x),(f(x,i)));
}
repaint();
}
int f(int x,int i)
{
int p=1;
while(i>0)
{
p=p*x;
i--;
}

return p%1200;
}
}