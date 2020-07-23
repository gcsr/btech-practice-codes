import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPTimeClient
{
	public static void main(String[] gcs)throws UnknownHostException
	{
		InetAddress host=InetAddress.getByName("localhost");
		try{
			UDPPoke poker=new UDPPoke(host,37);
			byte[] response=poker.poke();
			
			if(response==null)
			{
				System.out.println("no response with in allotted time");
				return;
			}
			else if(response.length==4)
			{
				System.out.println("unrecognised response format");
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}