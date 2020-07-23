package p1;



import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class BoxLayoutFrame extends JFrame
{
public BoxLayoutFrame()
{
super("box layout frame");

Box horizontal1=Box.createHorizontalBox();
Box vertical1=Box.createVerticalBox();
Box horizontal2=Box.createHorizontalBox();
Box vertical2=Box.createVerticalBox();

for(int count=0;count<3;count++)
{
horizontal1.add(new JButton("button"+count));
}

for(int count=0;count<3;count++)
{
vertical1.add(Box.createVerticalStrut(25));
vertical1.add(new JButton("button"+count));
}
for(int count=0;count<3;count++)
{
horizontal2.add(Box.createHorizontalGlue());
horizontal2.add(new JButton("button"+count));
}


for(int count=0;count<3;count++)
{
vertical2.add(Box.createRigidArea(new Dimension(12,00)));
vertical2.add(new JButton("button"+count));
}

JPanel panel=new JPanel();
panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

for(int count=0;count<3;count++)
{
panel.add(Box.createGlue());
panel.add(new JButton("button"+count));

}


JTabbedPane tabs=new JTabbedPane(JTabbedPane.BOTTOM,JTabbedPane.SCROLL_TAB_LAYOUT);

tabs.addTab("horizontal1box",horizontal1);
tabs.addTab("vertical1box",vertical1);
tabs.addTab("horizontal2box",horizontal2);
tabs.addTab("vertical2box",vertical2);
tabs.addTab("vertical box with glue",panel);

add(tabs);
}
}