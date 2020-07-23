import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class yesnodialogtest extends Applet
{
yesnodialog dialog=new yesnodialog("yes/no dialog","do you use adapters?",true);
Button lbutton=new Button("show dialog");
public void init()
{
add(lbutton);
lbutton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
Point loc=lbutton.getLocationOnScreen();
dialog.setLocation(loc.x+10,loc.y+10);
dialog.show();
if(dialog.getAnswer())
showStatus("yes");
else
showStatus("no");


}
}
);
} 
}