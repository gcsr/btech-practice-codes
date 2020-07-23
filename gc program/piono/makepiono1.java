import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
public class makepiono1 extends JFrame implements KeyListener
{
	String s="";
	int n=0;
	int p=1;
	JTextField x;
	playsequence1 ss;
	
	public static void main(String[] gcs)
	{
		new makepiono1();
	}
	public makepiono1()
	{
		setLayout(new BorderLayout());
		setFocusable(true);
		requestFocus();		
		addKeyListener(this);		
		setSize(150,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
	private class RunnableObject extends Thread
	{
		String pt;
		int kj;
		public RunnableObject(String h,int o)
		{
			pt=h;
			kj=o;		
		}
		public void run()
		{
			
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					new RunnableObject(pt,kj).start();
		
				}
			});

		}		
		
	}	
	public void keyPressed(KeyEvent e)
		{
			final	int keyCode=e.getKeyCode();		
					
			
							
				
				switch(keyCode)
				{

				case KeyEvent.VK_A:
									System.out.println("Gc");
									s="B"+n;
									new playsequence(s,5);
									break;
				case KeyEvent.VK_S:
									s="B"+n;
									new RunnableObject(s,5);
									break;
				case KeyEvent.VK_D:
									s="C"+n;
									new RunnableObject(s,5);
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