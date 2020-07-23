import java.net.URL;
import java.io.InputStream;

public class URLTest
{
	public static void main(String[] gcs)
	{
		try{
			URL ss=new URL("http://localhost:8080");
			InputStream is=ss.openStream();
			int c;

			  while ((c = is.read( )) != -1) System.out.write(c);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}