import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
public class makepiono3 extends JFrame implements KeyListener
{
	String s="";
	int n=0;
	int p=1;
	JTextField x;
	playsequence ss;
	
	public static void main(String[] gcs)
	{
		new makepiono3();
	}
	public makepiono3()
	{
		setLayout(new BorderLayout());
		setFocusable(true);
		requestFocus();		
		addKeyListener(this);		
		setSize(150,150);
		setVisible(true);
		ss=new playsequence();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		

	public void keyPressed(KeyEvent e)
		{
			final	int keyCode=e.getKeyCode();		
				switch(keyCode)
				{

				case KeyEvent.VK_A:
									System.out.println("Gc");
									s="B"+n;
									ss.playsequenceok(s,5);
									break;
				case KeyEvent.VK_S:
									s="B"+n;
									break;
				case KeyEvent.VK_D:
									s="C"+n;
									break;																																								
				}
	}
	public void keyReleased(KeyEvent e)
	{
	}
	
	public void keyTyped(KeyEvent e)
	{
	}

}