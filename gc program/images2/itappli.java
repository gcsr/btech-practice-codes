import java.awt.*;

import java.awt.event.*;

public class itappli extends Frame
{
Insets insets;
Image im;
public static  void main(String gcs[])
{
itappli app=new itappli();
app.show();
}
public itappli()
{
super("image test");
im=Toolkit.getDefaultToolkit().getImage("i2.jpg");
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
dispose();
System.exit(0);
}
}
);
}

public void addNotify()
{
super.addNotify();
insets=getInsets();
setBounds(100,100,500+insets.left,421+insets.top);
}
/*public void paint(Graphics gc)
{
gc.drawImage(im,24,24,this);
}*/
}