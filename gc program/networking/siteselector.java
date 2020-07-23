import javax.swing.JApplet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JList;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.net.URL;
import java.net.MalformedURLException;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.applet.AppletContext;


public class siteselector extends JApplet
{
	private HashMap sites;
	private Vector sitenames;
	private JList sitechooser;
	
	public void init()
	{
		sites=new HashMap();
		
		sitenames=new Vector();
		getsitesfromhtmlparameters();
		Container container=getContentPane();
		container.add(new JLabel("choose a site to browse"),BorderLayout.NORTH);
		sitechooser=new JList(sitenames);
		sitechooser.addListSelectionListener(
			new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				String object=(String)sitechooser.getSelectedValue();
				URL url=getClass().getResource("cc.GIF");
				AppletContext browser=getAppletContext();
				System.out.println(url);
				//browser.showDocument(url);
			}
			
		});
		container.add(new JScrollPane(sitechooser),BorderLayout.CENTER);
	}
	private void getsitesfromhtmlparameters()
	{
		String title,location;
		//URL url;
		int counter=0;
		title=getParameter("title"+counter);
		while(title!=null)
		{
			location=getParameter("location"+counter);
			
			
				System.out.println(location);
			
				sites.put(title,location);
				sitenames.add(location);
			
			
			++counter;
			title=getParameter("title"+counter);
		}
		
	}

}
