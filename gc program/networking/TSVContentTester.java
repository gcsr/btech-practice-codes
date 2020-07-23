
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import java.util.Enumeration;
import java.net.MalformedURLException;

public class TSVContentTester
{
	private static void test(URL u)throws IOException
	{
		Object content=u.getContent();
		
		Vector v=(Vector)content;
		for(Enumeration e=v.elements();e.hasMoreElements();)
		{
			String[] sa=(String[])e.nextElement();
			for(int i=0;i<sa.length;i++)
				System.out.print(sa+"\t");
			System.out.println();
		}
		
	}
	public static void main(String[] gcs)
	{
		String pkgs=System.getProperty("java.content.handler.pkgs","");
		System.out.println(pkgs);
		if (!pkgs.equals("")) {

      pkgs = pkgs + "|";

    }

    pkgs += "com.macfaq.net.www.content";      

    System.setProperty("java.content.handler.pkgs", pkgs);   



    for (int i = 0; i < gcs.length; i++) {

      try {

        URL u = new URL(gcs[i]);

        test(u);

      }

      catch (MalformedURLException ex) {

        System.err.println(gcs[i] + " is not a good URL"); 

      }

      catch (Exception ex) {

        ex.printStackTrace( );

      }


	}
	}
}