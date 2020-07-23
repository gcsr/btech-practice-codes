import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;


public class itemexample extends Frame
{
private Checkbox box=new Checkbox("check me");
private Choice choice=new Choice();
private List list=new List();

public static void main(String gcs[])
{
itemexample f=new itemexample();
f.setBounds(200,200,200,200);
f.show();
}
public itemexample()
{
super("item example");

MenuBar mbar=new MenuBar();
Menu m=new Menu("era");
setLayout(new FlowLayout());
setMenuBar(mbar);
add(box);
mbar.add(m);
MenuItem po=new MenuItem("manava");
m.add(po);

list.add("list 2");
list.add("list 3");
list.add("list 4");
list.add("list 5");
list.add("list 1");
add(list);

choice.add("choice 1");
choice.add("choice 2");
choice.add("choice 3");
choice.add("choice 4");
choice.add("choice 5");
add(choice);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
Window window=(Window)e.getSource();
window.dispose();
System.exit(0);
}
}
);
}
}