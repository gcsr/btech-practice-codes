package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class calculatorframe extends JFrame
{
public calculatorframe()
{
setTitle("calculator");
calculatorpanel panel=new calculatorpanel();
add(panel);

}
}


class calculatorpanel extends JPanel
{
JButton display,on,off;
JPanel panel;
boolean start=true;
double result;
String lastcommand="ss";


calculatorpanel()
{
setLayout(new BorderLayout());

display=new JButton("0");
on=new JButton("0");
off=new JButton("");

add(display,BorderLayout.NORTH);
add(on,BorderLayout.EAST);
add(off,BorderLayout.WEST);
on.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
display.setText("0");
}
}
);

off.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
display.setText("");
}
}
);


ActionListener insert=new InsertAction();
ActionListener command=new CommandAction();

panel=new JPanel();
panel.setLayout(new GridLayout(4,4));
addButton("7",insert);
addButton("8",insert);
addButton("9",insert);
addButton("/",command);

addButton("4",insert);
addButton("5",insert);
addButton("6",insert);
addButton("*",command);


addButton("1",insert);
addButton("2",insert);
addButton("3",insert);
addButton("-",command);

addButton("0",insert);
addButton(".",insert);
addButton("=",command);
addButton("+",command);




add(panel,BorderLayout.CENTER);
}
void addButton(String ll,ActionListener listener)
{
JButton button=new JButton(ll);
button.addActionListener(listener);
panel.add(button);
}

void addButton(String ll)
{
JButton button=new JButton(ll);
panel.add(button);
}


class InsertAction implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
String input=e.getActionCommand();
if(start)
{
display.setText(""+input);
start=false;
System.out.println("start");
}
else
display.setText(display.getText()+input);
}
}

class CommandAction implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
System.out.println(start);


String command=e.getActionCommand();
if(start)
{

if(command.equals("-"))
{
display.setText("-");
start=false;
}
else
lastcommand=command;
}

else
{
System.out.println(lastcommand);
calculate(Double.parseDouble(display.getText()));
lastcommand=command;
start=true;
}
}
}

public void calculate(double x)
{
System.out.println("calculate");
System.out.println(result);
try
{
if(lastcommand.equals("+"))result+=x;
else if(lastcommand.equals("-"))result-=x;
else if(lastcommand.equals("8"))result*=x;
else if(lastcommand.equals("/"))result/=x;
else if(lastcommand.equals("="))result=x;
}
catch(Exception e)
{
System.out.println("choodube");
e.printStackTrace();
}
System.out.println("calculated");

display.setText(""+result);
}
}