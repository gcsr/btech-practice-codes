import java.net.InetAddress;
import java.net.UnknownHostException;

public class inetadresstest
{
	public static void main(String[] gcs) throws UnknownHostException
	{
		InetAddress address=InetAddress.getLocalHost();
		System.out.println(address.getHostAddress());
		 System.out.println();
        	
	}
}