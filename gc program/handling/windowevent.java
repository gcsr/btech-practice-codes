import java.awt.event.*;
import java.awt.*;

public class windowevent extends Frame
{
public windowevent()
{
super("window event class");
}
public static void main(String gcs[])
{
System.out.println("window");
final Frame f=new windowevent();
f.setBounds(100,100,250,150);
f.setVisible(true);
f.addWindowListener(new WindowListener()
{
public void windowActivated(WindowEvent e)
{
System.out.println("window activated");
}
public void windowClosed(WindowEvent e)
{
System.out.println("window closed");
System.exit(0);
}
public void windowClosing(WindowEvent e)
{
System.out.println("windowclosing");
Window w=e.getWindow();
w.dispose();
}
public void windowDeactivated(WindowEvent e)
{
System.out.println("windowdeactivated");
}
public void windowDeiconified(WindowEvent e)
{
System.out.println("window deiconified");
}
public void windowOpened(WindowEvent e)
{
System.out.println("window opened");
}
public void windowIconified(WindowEvent e)
{
System.out.println("window iconified");
}

}
);
}
}