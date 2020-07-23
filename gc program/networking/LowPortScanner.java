import java.net.*;

import java.io.*;

public class LowPortScanner {



  public static void main(String[] args) {

    

    String host = "localhost";



    if (args.length > 0) {

      host = args[0];

    }

    for (int i=1;i<1024;i++) {
      try {
      	System.out.println(i);
      	
    	InetAddress ss=InetAddress.getByName(host);


        Socket s = new Socket(ss,i,10);
        
        
        

        System.out.println("There is a server on port " + i + " of " 

         + host);
         s.close();

      }

      catch (UnknownHostException ex) {
      }

      catch (IOException ex) {
      }

    } // end for

  

  }  // end main

  

}  //
