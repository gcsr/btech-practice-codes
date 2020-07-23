import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test2 {
        
   
    public static void main(String[] args) {
        try {

		  InetAddress local = InetAddress.getByName("127.0.0.1");

  			NetworkInterface ni = NetworkInterface.getByInetAddress(local);

  			if (ni == null) {

    			System.err.println("That's weird. No local loopback address.");

  				}
			System.out.println(ni);  				

			}

			catch (SocketException ex) {

  					System.err.println("Could not list sockets." );

					}

			catch (UnknownHostException ex) {

  					System.err.println("That's weird. No local loopback address.");

					}

    }
}
