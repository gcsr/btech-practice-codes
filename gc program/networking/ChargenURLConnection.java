import java.net.URLConnection;
import java.net.Socket;
import java.net.URL;

public class ChargenURLConnection extends URLConnection
{
	private Socket connection=null;
	private static final int DEFAULT_PORT=19;
	
	public ChargenURLConnection(URL u)
	{
		supet(u);
	}
	public synchronized InputStream getInputStream()throws IOException
	{
		if(!connected)connect();
		
		return new FiniteInputStream(this.connection.getInputStream());
	
	}
	public String getContentType()
	{
		return "text/html";
	}
	public synchronized void connect()throws IOException
	{
		int port=url.getPort();
		if(port<=0||port>65535)
			port=DEFAULT_PORT;
		this.connection=new Socket(url.getHost(),port);
		this.connected=true;
	}
	
		
}
