import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
public class readserverfile extends JFrame
{
	JTextField enterfield;
	JEditorPane contentsarea;
	public readserverfile()
	{
		super("simple web server");
		
		Container container=getContentPane();
	    
	    enterfield=new JTextField("enter the url files here");
	    contentsarea=new JEditorPane();
	    contentsarea.setEditable(false);
	    
	    contentsarea.addHyperlinkListener(new HyperlinkListener()
	    {
	    	public void hyperlinkUpdate(HyperlinkEvent e)
	    	{
	    		if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED);
	    		
	    		
	    	}
	    });
	    
	    container.add(enterfield,BorderLayout.NORTH);
	    container.add(new JScrollPane(contentsarea),BorderLayout.CENTER);
	    
		
	}
}