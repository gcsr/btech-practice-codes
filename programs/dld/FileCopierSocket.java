import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.ReadableByteChannel;



public class FileCopierSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try
		{
			InetAddress address=InetAddress.getByName("www.oreilly.com");
			String host=address.toString();
			String hostAddress=host.substring(host.indexOf("/")+1);
			System.out.println(hostAddress);
		}
		catch(UnknownHostException ex)
		{
			ex.printStackTrace();
			
		}
		// TODO Auto-generated method stub

	}

}
