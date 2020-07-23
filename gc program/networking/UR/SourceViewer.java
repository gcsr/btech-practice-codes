import java.net.*;

import java.io.*;



public class SourceViewer {



  public static void main (String[] args) {


	String[] gcs={"c:/gc/networking/UR/RelativeURLTest.html"
	};

    if  (gcs.length > 0) {

      try {

        //Open the URL for reading

        URL u = new URL(gcs[0]);

        InputStream in = u.openStream( );

        // buffer the input to increase performance 

        in = new BufferedInputStream(in);       

        // chain the InputStream to a Reader

        Reader r = new InputStreamReader(in);

        int c;

        while ((c = r.read( )) != -1) {

          System.out.print((char) c);

        } 

      }

      catch (MalformedURLException ex) {

        System.out.println(gcs[0] + " is not a parseable URL");

      }

      catch (IOException ex) {

        System.out.println(ex);

      }



    } //  end if



  } // end main



}  // end SourceViewer

