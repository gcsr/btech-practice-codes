
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class warmchase1 extends JFrame implements WindowListener
{

	public static void main(String[] gcs)
	{
	   warmchase1 app=new warmchase1();
	   app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   app.setSize(600,600);
	   app.setVisible(true);
	   
		
		
	}
	public warmchase1()
	{
		
		super("warmchase1");
		
		
		addWindowListener(this);
		
		wormpanel1 wp=new wormpanel1(this);
		add(wp);
		
	}
	
	public void windowActivated(WindowEvent e)
	{
		//wp.resumeGame();
	}
	public void windowDeactivated(WindowEvent e)
	{
		//wp.pauseGame();
	}
	public void windowIconified(WindowEvent e)
	{
		System.out.println("era");//wp.pauseGame();
	}
	public void windowDeiconified(WindowEvent e)
	{
		//wp.resumeGame();
	}
	public void windowClosing(WindowEvent e)
	{
	    //wc.stopGame();
	}
	public void windowClosed(WindowEvent e)
	{
		System.out.println("windowclosed");
		//wc.stopGame();
	}
	public void windowOpened(WindowEvent e)
	{
		//wc.startGame();
	}
	
}