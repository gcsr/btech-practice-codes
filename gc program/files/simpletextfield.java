import javax.swing.*;
import java.awt.event.*;

public class simpletextfield extends JFrame
{
	public static void main(String[] gcs)
	{
		new simpletextfield();
	}
	public simpletextfield()
	{
		JTextField ss=new JTextField();
		ss.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("ss");
			}
			
		});
		add(ss);
		setSize(10,100);
		setVisible(true);
	}
}