import java.net.URLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;

public class SourceViewer2
{
	public static void main(String[] gcs)
	{
		try{
		URL url=new URL("http://localhost:8080");
		URLConnection con=url.openConnection();
		
		InputStream buffer=new BufferedInputStream(con.getInputStream());
		InputStreamReader reader=new InputStreamReader(buffer);
		
		
		int c;
		while((c=reader.read())!=-1)
			System.out.print((char)c);
			
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
}