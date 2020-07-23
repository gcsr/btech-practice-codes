import java.net.*;

import java.io.*;




public class SecureSourceViewer {

	
  public static void main (String args[]) {
  		String[] gcs={"http://www.ncsa.uiuc.edu/demoweb/html-primer.html#A1.3.3.3" ,

 					"ftp://mp3:mp3@138.247.121.61:21000/c%3a/" ,                

 					"http://www.oreilly.com","http://www.ibiblio.org/nywc/compositions.phtml?category=Piano",

					" http://admin@www.blackstar.com:8080"
                                      
	};




    Authenticator.setDefault(new DialogueAuthenticator( ));



    for (int i = 0; i < gcs.length; i++) {

      

      try {

        //Open the URL for reading

        URL u = new URL(gcs[i]);

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

        System.err.println(gcs[0] + " is not a parseable URL");

      }

      catch (IOException ex) {

        System.err.println(ex);

      }

      

      // print a blank line to separate pages

      System.out.println( );

      

    } //  end for

  

    // Since we used the AWT, we have to explicitly exit.

    System.exit(0);



  } // end main



}  // end SecureSourceViewer
