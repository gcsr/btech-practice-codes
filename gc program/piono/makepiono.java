import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class makepiono extends JFrame
{
	String s="";
	int n=0;
	int p=1;
	JTextField x;
	playsequence pop;
	
	public static void main(String[] gcs)
	{
		new makepiono();
	}
	public makepiono()
	{
		setLayout(new BorderLayout());
		pop=new playsequence();
		setFocusable(true);
		requestFocus();
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				System.out.println("key pressed");
				p++;
			}
			
			public void keyReleased(KeyEvent e)
			{
				final	int keyCode=e.getKeyCode();		
					
				System.out.println(p);	
				SwingUtilities.invokeLater(
					new Runnable()
					{
					public void run()
					{	
							
				
					
				switch(keyCode)
				{
				case KeyEvent.VK_A:
									s="A"+n;
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_S:
									s="B"+n;
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_D:
									s="C"+n;
									pop.playsequenceok(s,p);
									p=0;
									break;
									
				case KeyEvent.VK_F:
									s="D"+n;
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_J:
									s="E"+n;
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_K:
									s="F"+n;
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_L:
									s="G"+n;
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_W:
									s="A"+n+"#";
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_E:
				
									s="B"+n+"#";
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_R:
									s="C"+n+"#";
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_T:
				
									s="D"+n+"#";
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_U:
									s="E"+n+"#";
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_I:
									s="F"+n+"#";	
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_O:
				
									s="G"+n+"#";
									pop.playsequenceok(s,p);
									p=0;
									break;
				case KeyEvent.VK_H:
									n=2;
									break;
				case KeyEvent.VK_V:
									n=3;
									break;
				case KeyEvent.VK_B:
									n=4;
									break;
				case KeyEvent.VK_N:
									n=5;
									break;	
				case KeyEvent.VK_M:
									n=6;
									break;
				case KeyEvent.VK_0:
									n=0;
									break;
				case KeyEvent.VK_G:
									n=1;
									break;
				case KeyEvent.VK_8:
									n=8;
									break;
				case KeyEvent.VK_9:
									n=9;
									break;
				case KeyEvent.VK_7:
									n=7;
									break;
				case KeyEvent.VK_F1:
									p=1;
									x.setText(String.valueOf(p));
									break;		
				case KeyEvent.VK_F2:
									p=2;
									x.setText(String.valueOf(p));
									break;											
				case KeyEvent.VK_F3:
									p=3;
									x.setText(String.valueOf(p));
									break;																																																													
				case KeyEvent.VK_F4:
									p=4;
									x.setText(String.valueOf(p));
									break;	
				case KeyEvent.VK_F5:
									p=5;
									x.setText(String.valueOf(p));
									break;	
				case KeyEvent.VK_F6:
									p=6;
									x.setText(String.valueOf(p));
									break;	
				case KeyEvent.VK_F7:
									p=7;
									x.setText(String.valueOf(p));
									break;	
				case KeyEvent.VK_F8:
									p=8;
									x.setText(String.valueOf(p));
									break;	
				case KeyEvent.VK_F9:
									p=9;
									x.setText(String.valueOf(p));
									break;																																									
				}
					}
					
				}	);
		
		
			}
			
		});
		
		setSize(150,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}