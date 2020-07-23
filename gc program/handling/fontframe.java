package gui;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.font.FontRenderContext;
import javax.swing.JPanel;


public class fontframe extends JFrame
{
public fontframe()
{
setTitle("fonttest");
fontcomponent cp=new fontcomponent();
add(cp);
}
}

class fontcomponent extends JPanel
{
public void paintComponent(Graphics gc)
{
Graphics2D g=(Graphics2D)gc;

g.setFont(new Font("serif",Font.BOLD,36));

FontRenderContext context=g.getFontRenderContext();
System.out.println(context);
System.out.println(java.awt.font.FontRenderContext@0);
}

}




