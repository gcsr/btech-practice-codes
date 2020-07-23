import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;


public class actionevent extends Frame
{
Button button=new Button("Actionevent");
List list=new List();
TextField tfield=new TextField(25);
MenuItem mitem=new MenuItem("menuitem");
public static void main(String gcs[])
{
actionevent f=new actionevent();
f.setBounds(200,200,200,200);
f.show();
}
public actionevent()
{
super("action example");
MenuBar mbar=new MenuBar();
Menu menu=new Menu("menu");
menu.add(mitem);
mbar.add(menu);
setMenuBar(mbar);
list.add("item1");
list.add("item2");
list.add("item3");
list.add("item4");
list.add("item5");
setLayout(new FlowLayout());
add(button);
add(list);
add(tfield);	
list.addActionListener(new debugactionlistener());
button.addActionListener(new debugactionlistener());
tfield.addActionListener(new debugactionlistener());
mitem.addActionListener(new debugactionlistener());
addWindowListener(new actionwindowlistener());
}
class actionwindowlistener extends WindowAdapter
{
public void windowClosing(WindowEvent e)
{
Window window=(Window)e.getSource();
window.dispose();
System.exit(0);
}
}
class debugactionlistener implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
System.out.println("action event in "+e.getActionCommand());
}
}
}