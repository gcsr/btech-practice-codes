
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class hostel extends JFrame
{
	JButton parent,canteen,warden;
	
	public hostel()
	{
		super("hostel");
		setSize(300,100);
		setVisible(true);
		setLayout(new FlowLayout());
		
		parent=new JButton("parent");
		canteen=new JButton("canteen");
		warden=new JButton("warden");
		
		add(parent);
		add(canteen);
		add(warden);
		
		addWindowListener(new WindowAdapter()
		{
			public void WindowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		canteen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new CanteenUI();
			}
		});
		
		parent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new parentUI();
			}
		});
		
		warden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new WardenUI();
			}
		});
		
		
	}
	public static void main(String[] gcs)
	{
		new hostel();
	}
	
}