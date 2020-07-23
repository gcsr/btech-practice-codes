import java.net.*;

import java.util.*;



public class InterfaceListener {



    public static void main(String[] args) throws Exception {

        

      Enumeration interfaces = NetworkInterface.getNetworkInterfaces( );

      while (interfaces.hasMoreElements( )) {

        NetworkInterface ni = (NetworkInterface) interfaces.nextElement( );

        System.out.println(ni);               

      }  

          

    }



}
