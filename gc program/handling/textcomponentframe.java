package gui;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.*;



public class textcomponentframe extends JFrame
{
public textcomponentframe()
{
setTitle("textcomponentframe");
JTextField tfield=new JTextField();
final JPasswordField pfield=new JPasswordField();

JPanel northpanel=new JPanel();

northpanel.setLayout(new GridLayout(2,2));
northpanel.add(new JLabel("username:",SwingConstants.RIGHT));
northpanel.add(tfield);
northpanel.add(new JLabel("password:",SwingConstants.RIGHT));
northpanel.add(pfield);
add(northpanel,BorderLayout.NORTH);

JButton button=new JButton("insert");

JTextArea tarea=new JTextArea(8,40);
JPanel southpanel=new JPanel();
JScrollPane spane=new JScrollPane(tarea);

add(spane,BorderLayout.CENTER);
southpanel.add(tarea);
southpanel.add(button);
button.addActionListener(
new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
System.out.println(pfield.getPassword());
}
}
);
add(southpanel,BorderLayout.SOUTH);
}
}