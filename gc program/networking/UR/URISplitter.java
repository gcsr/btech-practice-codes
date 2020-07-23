import java.net.*;



public class URISplitter {



  public static void main(String args[]) {
  		String[] gcs={"http://www.ncsa.uiuc.edu/demoweb/html-primer.html#A1.3.3.3" ,

 					"ftp://mp3:mp3@138.247.121.61:21000/c%3a/" ,                

 					"http://www.oreilly.com","http://www.ibiblio.org/nywc/compositions.phtml?category=Piano",

					" http://admin@www.blackstar.com:8080"
                                      
	};




    for (int i = 0; i < gcs.length; i++) {

      try {

        URI u = new URI(gcs[i]);

        System.out.println("The URI is " + u);

        if (u.isOpaque( )) {

          System.out.println("This is an opaque URI."); 

          System.out.println("The scheme is " + u.getScheme( ));        

          System.out.println("The scheme specific part is " 

           + u.getSchemeSpecificPart( ));        

          System.out.println("The fragment ID is " + u.getFragment( ));        

        }

        else {

          System.out.println("This is a hierarchical URI."); 

          System.out.println("The scheme is " + u.getScheme( ));        

          try {       

            u = u.parseServerAuthority( );

            System.out.println("The host is " + u.getUserInfo( ));        

            System.out.println("The user info is " + u.getUserInfo( ));        

            System.out.println("The port is " + u.getPort( ));        

          }

          catch (URISyntaxException ex) {

            // Must be a registry based authority

            System.out.println("The authority is " + u.getAuthority( ));        

          }

          System.out.println("The path is " + u.getPath( ));        

          System.out.println("The query string is " + u.getQuery( ));        

          System.out.println("The fragment ID is " + u.getFragment( )); 

        } // end else       

      }  // end try

      catch (URISyntaxException ex) {

        System.err.println(gcs[i] + " does not seem to be a URI.");

      }

      System.out.println( );

    }  // end for



  }  // end main



}  // end URISplitt
