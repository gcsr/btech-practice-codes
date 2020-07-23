import java.net.*;

import java.io.*;



public class SocketInfo {



  public static void main(String[] args) {



    try{

        Socket theSocket = new Socket("localhost", 80);

        System.out.println("Connected to " + theSocket.getInetAddress( ) 

         + " on port "  + theSocket.getPort( ) + " from port " 

         + theSocket.getLocalPort( ) + " of " 

         + theSocket.getLocalAddress( ));
    }
      
      catch (UnknownHostException ex) {

        System.err.println("I can't find " +" localhost");

      }

      catch (SocketException ex) {

        System.err.println("Could not connect to " + "localhost");

      }

      catch (IOException ex) {

        System.err.println(ex);

      }



  }  // end main



}  // end SocketInfo
