import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.regex.*;
public class SourceViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
		// 	TODO Auto-generated method stub
			URL u=new URL("http://www.youtube.com/watch?v=W7VvzpDj7FA");
			//int s=u.
			URLConnection uc=u.openConnection();
			InputStream raw=uc.getInputStream();
			
			InputStream buffer=new BufferedInputStream(raw);
			Reader r=new InputStreamReader(buffer);
			int c;
			String p="";
			while((c=r.read())!=-1)
			{
				p+=(char)c;
				
			}
			Pattern patt=Pattern.compile(".*-flv");
			Matcher m=patt.matcher(p);
			
			while(m.find())
			{
				int start=m.start(0);
				int end=m.end(0);
				
				System.out.println(p.substring(start,end));
			}
			System.out.print("over");
		}
		catch(MalformedURLException ex)
		{
			System.out.println("wrong url");
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			//System.out.println()
		}
		

	}

}
