package gui;


import javax.swing.JFrame;
import javax.swing.JButton;
public class  circlelayoutframe extends JFrame
{
public circlelayoutframe()
{
setTitle("user layout");
setLayout(new circlelayout());

add(new JButton("yellow"));
add(new JButton("blue"));
add(new JButton("red"));
add(new JButton("green"));
add(new JButton("orange"));
add(new JButton("fuchsia"));
add(new JButton("indigo"));

}
}