package gui;


import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;




public class textareaframe extends JFrame
{
public textareaframe()
{
setTitle("textcomponentframe");



JTextArea tarea=new JTextArea(8,40);
tarea.setTabSize(10);
add(tarea);
//JScrollPane spane=new JScrollPane(tarea);


}
}