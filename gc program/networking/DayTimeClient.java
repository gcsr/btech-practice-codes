import java.net.*;

import java.io.*;



public class DayTimeClient {



  public static void main(String[] args) {



    String hostname;



    if (args.length > 0) {

      hostname = args[0];

    }

    else {

      hostname = "127.0.0.1";

    }



    try {

      Socket theSocket = new Socket(hostname, 80);

      InputStream timeStream = theSocket.getInputStream( );

      StringBuffer time = new StringBuffer( );

      int c;

      while ((c = timeStream.read( )) != -1) time.append((char) c);

      String timeString = time.toString( ).trim( );  

      System.out.println("It is " + timeString + " at " + hostname);

    }  // end try

    catch (UnknownHostException ex) {

      ex.printStackTrace();

    }

    catch (IOException ex) {

      ex.printStackTrace();

    }



  }  // end main



} // end DaytimeClient
