import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class WebBrowserFrame extends JFrame
{
	JTextField urlField;
	JTextArea  codeHere;
	JButton click;
	public WebBrowserFrame()
	{
		setLayout(new BorderLayout());
		add(buildBottom(),BorderLayout.SOUTH);
		add(buildTextArea(),BorderLayout.CENTER);
		
	}
	public JComponent buildBottom()
	{
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));
	    urlField=new JTextField("enter ur url here");
	    click=new JButton("click here");
	    click.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		String url=urlField.getText();
	    		Socket webServer;
	    		try{
	    			webServer=new Socket(url,80);
	    			askForPage(webServer);
	    			receivePage(webServer);
	    			webServer.close();
	    			
	    		}catch(MalformedURLException ee)
	    		{
	    			codeHere.setText("invalid url");
		    	}
		    	catch(IOException e)
		    	{
		    		codeHere.setText("no server here");
		    	}
	    	}
	    });
	    panel.add(urlField);
	    panel.add(click);
	    
	    return panel;
	}
	public JComponent buildTextArea()
	{
		codeHere=new JTextArea();
		codeHere.setLineWrap(true);
		JScrollPane pane=new JScrollPane(codeHere);
		pane.setBorder(new EtchedBorder());
		
		return pane;
	}
	public void askForPage(Socket server)throws IOException
	{
		BufferedWriter request;
		request=new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
		request.write("GET/HTTP/1.0\n\n");
		request.flush();
	}
	public void receivePage(Socket server)throws IOException
	{
		BufferedReader webPage=null;
		webPage=new BufferedReader(new InputStreamReader(server.getInputStream()));
		String nextLine;
		while((nextLine=webPage.readLine())!=null)
		{
			codeHere.append(nextLine+"\n");
		}
		return;
	}
	
}