import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class SourceFetcher {
	public static void main(String[] gcs){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter URL: ");
		String url=scanner.nextLine();
		try {

			  URL u  = new URL(url);

			  InputStream in = u.openStream( );

			  int c;

			  while ((c = in.read( )) != -1) System.out.write(c);

			}

			catch (IOException ex) {

			  System.err.println(ex);

			}


	}
}
