import javax.swing.JFrame;
import java.io.IOException;
import java.util.Map;
import java.net.URL;
import java.net.URLConnection;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.io.InputStream;
import java.net.HttpURLConnection;



public class PostTestFrame extends JFrame{
	public static String doPost(String urlString,Map<String,String> nameValuePairs)throws IOException
	{
		URL url=new URL(urlString);
		URLConnection connection=url.openConnection();
		
		connection.setDoOutput(true);
		PrintWriter out=new PrintWriter(connection.getOutputStream());
		
		boolean first=true;
		
		for(Map.Entry<String,String> pair:nameValuePairs.entrySet())
		{
			if(first) first=false;
			
			else
				out.print('&');
				
				String name=pair.getKey();
				String value=pair.getValue();
				out.print(name);
				out.print(URLEncoder.encode(value,"UTF-8"));
		}
		out.close();
		Scanner in;
		
		StringBuilder response=new StringBuilder();
		try
		{
			in=new Scanner(connection.getInputStream());
		}
		catch(IOException e)
		{
			if(!(connection instanceof HttpURLConnection))throw e;
			
			InputStream err=((HttpURLConnection)connection).getErrorStream();
			
			if(err==null)
				throw e;
			in=new Scanner(err);	
		}
		while(in.hasNextLine())
		{
			response.append(in.nextLine());
			response.append("\n");
		}
		
		
		in.close();
		return response.toString();
		
		
	}
	
	public PostTestFrame()
	{
		setTitle("Post Test");
		
		JPanel northPanel=new JPanel();
		add(northPanel,BorderLayout.NORTH);
		
		final JComboBox combo=new JComboBox();
		
		for(int i=0;i<countries.length;i+=2)
		{
			combo.addItem(countries[i]);
		}
		northPanel.add(combo);
		
		
		final JTextArea result=new JTextArea();
		add(new JScrollPane(result));
		
		JButton getButton=new JButton("Get");
		northPanel.add(getButton);
		getButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						final String SERVER_URL="http://www.census.gov/cgi-bin/ipc/idbsprd";
						result.setText("");
						
						Map<String,String> post=new HashMap<String,String>();
						post.put("tbl","001");
						post.put("cty",countries[2*combo.getSelectedIndex()+1]);
						post.put("optyr","latest checked");
						
						try{
							result.setText(doPost(SERVER_URL,post));
						}
						catch(IOException e)
						{
							result.setText("");
							
						}
					}
				}).start();
			}
		});
		
	
	}
	private static  String[] countries={"India","IN","Pakistan","PK"};
}