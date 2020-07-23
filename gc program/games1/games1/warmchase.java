
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class warmchase extends JFrame implements WindowListener
{

	JTextField box,time;
	
	
	public static void main(String[] gcs)
	{
		int fps=80;
		if(gcs.length!=0)
		fps=Integer.parseInt(gcs[0]);
		
		long period=(long)1000.0/fps;
		
	   warmchase app=new warmchase(period);
	   app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   app.setSize(600,600);
	   app.setVisible(true);
	   
		System.out.println("fps "+fps+"  period  "+period);
		
	}
	public warmchase(long period)
	{
		
		super("warmchase");

		addWindowListener(this);
		wormpanel app=new wormpanel(this);
		add(app);
		
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