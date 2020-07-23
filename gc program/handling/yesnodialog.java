import java.awt.*;
import java.awt.event.*;

class yesnodialog extends Dialog implements ActionListener
{
private Button yes=new Button("yes");
private Button no=new Button("no");

private boolean answer;

public yesnodialog(String title,String msg,boolean ismodal)
{
super(new Frame(),title,ismodal);

Panel buttonpanel=new Panel();
Panel labelpanel=new Panel();

buttonpanel.add(yes);
buttonpanel.add(no);

labelpanel.add(new Label(msg));
add(labelpanel,"Center");
add(buttonpanel,"South");

yes.addActionListener(this);
no.addActionListener(this);

pack();
}
public boolean getAnswer()
{
return answer;
}
public void actionPerformed(ActionEvent e)
{
Button button=(Button)e.getSource();
if(button==yes)
yesbuttonactivated(e);
else if(button==no)
nobuttonactivated(e);
}
public void yesbuttonactivated(ActionEvent e)
{
answer=true;
dispose();
}
public void nobuttonactivated(ActionEvent e)
{
answer=false;
dispose();
}
}





