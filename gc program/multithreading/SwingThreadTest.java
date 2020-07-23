import javax.swing.JFrame;
import multithreading.SwingThreadFrame;

public class SwingThreadTest
{
	public static void main(String[] gcs)
	{
		SwingThreadFrame frame=new SwingThreadFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
		
	}
}