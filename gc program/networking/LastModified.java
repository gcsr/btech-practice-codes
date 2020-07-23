import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

public class LastModified
{
	public static void main(String[] gcs){
	try
	{
		URL url=new URL("http://localhost:8080/index.html");
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		connection.setRequestMethod("HEAD");
		System.out.println("was last modified at"+new Date(connection.getLastModified()));
	}
	catch(MalformedURLException e)
	{
		e.printStackTrace();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	}
}