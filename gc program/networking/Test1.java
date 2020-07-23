import java.net.*;



public class Test1 {



  public static void main (String[] args) {



    try {

      InetAddress address = InetAddress.getByName("www.oreilly.com");

      System.out.println(address);

    }

    catch (UnknownHostException ex) {

      System.out.println("Could not find www.oreilly.com");

    }



  }



}
