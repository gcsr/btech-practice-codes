import java.net.URL;
import java.net.MalformedURLException;

public class test1
{
	public static void main(String[] gcs)
	{
		try{
			URL u = new URL("http://www.audubon.org/");
			System.out.println("yaah");
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
			
		}
		
	}
}