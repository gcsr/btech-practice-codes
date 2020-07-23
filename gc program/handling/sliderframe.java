package gui;



import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class sliderframe extends JFrame
{

JPanel sliderpanel;
public sliderframe()
{
setTitle("sliderframe");
sliderpanel=new JPanel();

final JTextField textfield=new JTextField();
JSlider slider=new JSlider();
addslider(slider,"palin");


listener=new ChangeListener()
{
public void stateChanged(ChangeEvent e)
{
JSlider source=(JSlider)e.getSource();
textfield.setText(""+source.getValue());
}
};




JSlider slider1=new JSlider();
slider1.setMajorTickSpacing(20);
slider1.setMinorTickSpacing(10);
addslider(slider1,"ticks");
slider1.setPaintTicks(true);


JSlider slider2=new JSlider();
slider2.setPaintTicks(true);
slider2.setSnapToTicks(true);
slider2.setMajorTickSpacing(20);
slider2.setMinorTickSpacing(10);
addslider(slider2,"ticks");


JSlider slider3=new JSlider();
slider3.setPaintTicks(true);
slider3.setSnapToTicks(true);
slider3.setMajorTickSpacing(20);
slider3.setMinorTickSpacing(10);
slider3.setPaintTrack(false);
addslider(slider3,"track");

JSlider slider4=new JSlider();
slider4.setPaintTicks(true);
slider4.setSnapToTicks(true);
slider4.setMajorTickSpacing(20);
slider4.setMinorTickSpacing(10);
slider4.setInverted(true);
addslider(slider4,"inverted");

slider=new JSlider();
slider.setPaintTicks(true);
slider.setSnapToTicks(false);
slider.setMajorTickSpacing(20);
slider.setMinorTickSpacing(10);
slider.setPaintLabels(true);
addslider(slider,"inverted");



sliderpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
add(sliderpanel,BorderLayout.CENTER);
add(textfield,BorderLayout.SOUTH);
}



public void addslider(JSlider s,String dis)
{
s.addChangeListener(listener);
JPanel panel=new JPanel();
panel.add(s);
panel.add(new JLabel(dis));
sliderpanel.add(panel);
}




ChangeListener listener;
}