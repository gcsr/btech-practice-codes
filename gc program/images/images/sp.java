package test;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class sp extends JFrame
{
JDesktopPane desktop;
public sp()
{
//JInternalFrame frame=new JInternalFrame("internal frame",false,false,false,false);
//3 close 4 maxi  5 min 2 size changes
ss panel=new ss();
add(panel);
//frame.pack();
//desktop=new JDesktopPane();
//add(desktop);
//desktop.add(frame);
//frame.setVisible(true);
}
}