import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;


public class EncodingAwareSourceViewer
{
	public static void main(String[] gcs)
	{
		InputStreamReader reader;
		
		try{
			String encoding="ISO-8859-1";
			URL u=new URL("http://localhost:8080");
			URLConnection urc=u.openConnection();
			String contentType=urc.getContentType();
			
			int charset=contentType.indexOf("charset=");
			
			if(charset!=-1)
				encoding=contentType.substring(charset+8);
			InputStream in=new BufferedInputStream(urc.getInputStream());
			reader=new InputStreamReader(in);
			
			int c;
			
			while((c=reader.read())!=-1)
				System.out.print((char)c);
			

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