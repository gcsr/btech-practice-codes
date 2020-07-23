import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class Test4 extends JFrame
{
	String s="";
	int n=0;
	int p=1;
	JTextField x;
	
	public static void main(String[] gcs)
	{
		new Test4();
	}
	public Test4()
	{
		setLayout(new BorderLayout());
		

	
		
		setFocusable(true);
		requestFocus();
		addKeyListener(new KeyAdapter()
		{
			
			public void keyPressed(KeyEvent e)
			{
				
				System.out.println("keypressed");

			}

			public void keyReleased(KeyEvent e)
			{
				
				System.out.println("keReleased");

				System.out.println(e.getKeyCode());
				
			}
		});
			
		setSize(150,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}