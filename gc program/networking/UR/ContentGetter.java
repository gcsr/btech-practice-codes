import java.net.*;

import java.io.*;



public class ContentGetter {



  public static void main (String[] args) {
  	String[] gcs={"c:/gc/networking/UR/RelativeURLTest.html"
	};



    if  (gcs.length > 0) {



      //Open the URL for reading

      try {

        URL u = new URL(gcs[0]);

        try {

          Object o = u.getContent( );

          System.out.println("I got a " + o.getClass( ).getName( ));

        } // end try

        catch (IOException ex) {

          System.err.println(ex);

        }

      } // end try

      catch (MalformedURLException ex) {

        System.err.println(gcs[0] + " is not a parseable URL");

      }

    } //  end if



  } // end main



} 
