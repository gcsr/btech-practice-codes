import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ExitingFrame extends JFrame
{
	public ExitingFrame()
	{
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new ExitOnClose());
		buildGui();
		doLayout();
		validate();
	}
	protected abstract void buildGui();
	
	private class ExitOnClose extends WindowAdapter
	{
		public void windowClosed(WindowEvent e)
		{
			System.exit(0);
		}
	}
	
}