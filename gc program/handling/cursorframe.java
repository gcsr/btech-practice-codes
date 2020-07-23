package gui;

import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;

public class cursorframe extends JFrame
{
public cursorframe()
{
setTitle("cursortest");

/*Cursor[] cs;
//int p=Cursor.getcursorCount();
System.out.println(Cursor.HAND_CURSOR);
//cs=Cursor.getPredefinedCursors();
*/


Toolkit tk=Toolkit.getDefaultToolkit();
Image image=tk.getImage("cursor.jpg");
final Cursor dcursor=tk.createCustomCursor(image,new Point(10,10),"me");



addMouseListener(new MouseAdapter()
{
public void mousePressed(MouseEvent e)
{
System.out.println(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
setCursor(dcursor);

}
}
);
}
public void paintComponent(Graphics gc)
{

}
}