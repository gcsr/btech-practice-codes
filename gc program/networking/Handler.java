import java.net.URLStreamHandler;
import java.net.URLConnection;
import java.io.IOException;
import java.net.URL;

public class Handler extends URLStreamHandler
{
	public int getDefaultPort()
	{
		return 13;
	}
	
	protected URLConnection openConnection(URL url)throws IOException
	{
		return new DayTimeURLConnection(url);
	}
}