import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class SourceViewer {
	
	public static void main(String[] gcs){
		/*try {

	        //Open the URLConnection for reading

	       /* URL u = new URL("https://www.google.co.in/search?tbm=isch&q=apple&oq=apple");

	        URLConnection uc = u.openConnection( );

	        InputStream raw = uc.getInputStream( );

	        InputStream buffer = new BufferedInputStream(raw);       

	        // chain the InputStream to a Reader

	        Reader r = new InputStreamReader(buffer);

	        int c;

	        while ((c = r.read( )) != -1) {

	          System.out.print((char) c);

	        } 

	      }

	      catch (MalformedURLException ex) {

	          

	        }

	        catch (IOException ex) {

	          System.err.println(ex);

	        }*/
			
			try {
			    String text = "https://www.google.co.in/search?tbm=isch&q=apple&oq=apple";
			    URL url = new URL(text);
			    URLConnection conn = url.openConnection();
			    // fake request coming from browser
			    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
			   /*InputStream raw = conn.getInputStream( );

			    InputStream buffer = new BufferedInputStream(raw);       

		        // chain the InputStream to a Reader

		        Reader r = new InputStreamReader(buffer);

		        int c;

		        while ((c = r.read( )) != -1) {

		          System.out.print((char) c);

		        }*/ 


			   BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			    String f = in.readLine();
			    in.close();
			    System.out.println(f);
			} catch (Exception e) {
			    e.printStackTrace();
			}

	}

}
