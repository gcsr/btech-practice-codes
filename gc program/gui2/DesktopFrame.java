package p1;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;



public class DesktopFrame extends JFrame
{

private JDesktopPane desktop;

public DesktopFrame()
{
super("using desktoppane");


JMenuBar bar=new JMenuBar();
JMenu addmenu=new JMenu("add");
JMenuItem newframe=new JMenuItem("internal frame");

addmenu.add(newframe);
bar.add(addmenu);
setJMenuBar(bar);

desktop=new JDesktopPane();
add(desktop);
newframe.addActionListener(
new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
JInternalFrame frame=new JInternalFrame("internal frame",false,false,false,false);
//3 close 4 maxi  5 min 2 size changes
myjpanel panel=new myjpanel();
frame.add(panel,BorderLayout.CENTER);
frame.pack();

desktop.add(frame);
frame.setVisible(true);
}
}
);



}
}

class myjpanel extends JPanel
{
private static Random generator=new Random();
private ImageIcon picture;
private String images[]={"s1.JPG","s2.JPG","s3.JPG"};

public myjpanel()
{
int rnumber=generator.nextInt(3);
picture=new ImageIcon(images[rnumber]);
}

public void paintComponent(Graphics gc)
{
System.out.println("ss");
super.paintComponent(gc);
picture.paintIcon(this,gc,0,0);
}

public Dimension getPreferredSize()
{
return new Dimension(picture.getIconWidth(),picture.getIconHeight());
}
}