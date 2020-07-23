
package p1;

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;


public class JtabbedPaneFrame extends JFrame
{
public JtabbedPaneFrame()
{
super("Jtabbedpanedemo");

JTabbedPane tabpane=new JTabbedPane();

JLabel label1=new JLabel("panel1",SwingConstants.CENTER);
JPanel panel1=new JPanel();
panel1.add(label1);

JLabel label2=new JLabel("panel2",SwingConstants.CENTER);
JPanel panel2=new JPanel();
panel2.setBackground(Color.YELLOW);
panel2.add(label2);

JLabel label3=new JLabel("panel3");
JPanel panel3=new JPanel();
panel3.setLayout(new BorderLayout());
panel3.add(new JButton("north"),BorderLayout.NORTH);
panel3.add(new JButton("south"),BorderLayout.SOUTH);
panel3.add(new JButton("east"),BorderLayout.EAST);
panel3.add(new JButton("west"),BorderLayout.WEST);
panel3.add(label3,BorderLayout.CENTER);



tabpane.addTab("tab one",null,panel1,"first panel");
tabpane.addTab("tab two",null,panel2,"second panel");
tabpane.addTab("tab three",null,panel3,"third panel");
add(tabpane);
}
}