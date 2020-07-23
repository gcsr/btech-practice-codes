import java.net.*;



public class URLSplitter {



  public static void main(String args[]) {


	String[] gcs={"http://www.ncsa.uiuc.edu/demoweb/html-primer.html#A1.3.3.3" ,

 					"ftp://mp3:mp3@138.247.121.61:21000/c%3a/" ,                

 					"http://www.oreilly.com","http://www.ibiblio.org/nywc/compositions.phtml?category=Piano",

					" http://admin@www.blackstar.com:8080"
                                      
	};

    for (int i = 0; i < gcs.length; i++) {

      try {

        URL u = new URL(gcs[i]);

        System.out.println("The URL is " + u);

        System.out.println("The scheme is " + u.getProtocol( ));        

        System.out.println("The user info is " + u.getUserInfo( ));

        

        String host = u.getHost( );

        if (host != null) {

          int atSign = host.indexOf('@');  

          if (atSign != -1) host = host.substring(atSign+1);

          System.out.println("The host is " + host);   

        }

        else {          

          System.out.println("The host is null.");   

        }



        System.out.println("The port is " + u.getPort( ));

        System.out.println("The path is " + u.getPath( ));
        System.out.println("The file is " + u.getFile( ));

        System.out.println("The ref is " + u.getRef( ));

        System.out.println("The query string is " + u.getQuery( ));

      }  // end try

      catch (MalformedURLException ex) {

        System.err.println(args[i] + " is not a URL I understand.");

      }

      System.out.println( );

    }  // end for



  }  // end main



}  // end URLSplitter

