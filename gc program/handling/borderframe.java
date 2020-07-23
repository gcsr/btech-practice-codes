package gui;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;



public class borderframe extends JFrame
{
JLabel label;
JPanel bpanel;
JPanel demopanel;

ButtonGroup group;

public borderframe()
{
setTitle("borderframetest");

demopanel=new JPanel();
bpanel=new JPanel();

group=new ButtonGroup();

bpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue),"gc",5,6));

addradiobutton("loweredlevel",BorderFactory.createLoweredBevelBorder());
addradiobutton("raised level",BorderFactory.createRaisedBevelBorder());
addradiobutton("Etched",BorderFactory.createEtchedBorder());
addradiobutton("line",BorderFactory.createLineBorder(Color.blue));
addradiobutton("matte",BorderFactory.createMatteBorder(2,2,2,2,Color.blue));
addradiobutton("empty",BorderFactory.createEmptyBorder());
addradiobutton("echedpara",BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.blue,Color.red));


setLayout(new GridLayout(2,1));

add(bpanel);
add(demopanel);

demopanel.setBorder(BorderFactory.createLoweredBevelBorder());
}
public void addradiobutton(String name,final Border b)
{
JRadioButton button=new JRadioButton(name,false);

button.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
demopanel.setBorder(b);
}
}
);

group.add(button);
bpanel.add(button);
}
}