import java.net.*;
import java.util.Enumeration;


public class Test4
{
	public static void main(String[] gcs) throws SocketException
	{
		try{
		NetworkInterface eth0 = NetworkInterface.getByName("eth0");

		Enumeration addresses = eth0.getInetAddresses( );

		while (addresses.hasMoreElements( )) {
			
		System.out.println("I");
  		System.out.println(addresses.nextElement( ));  }
		}
		catch(SocketException e)
		{
			System.out.println("exception ra");
		}
  }
}