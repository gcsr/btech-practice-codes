import java.net.*;

import java.io.*;



public class AllHeaders {



  public static void main(String args[]) {



    

      try {

        URL u = new URL("http://localhost:8080/D:/telugu/jalsa.avi");

        URLConnection uc = u.openConnection( );

        for (int j = 1; ; j++) {

          String header = uc.getHeaderField(j);
          System.out.println("me");

          if (header == null) break;

          System.out.println(uc.getHeaderFieldKey(j) + ": " + header);


      }  
      }

      catch (MalformedURLException ex) {

        

      }

      catch (IOException ex) {

        System.err.println(ex);

      }

      System.out.println( );





  }  // end main



}  // end AllHeaders
