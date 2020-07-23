import java.net.SocketException;
import java.net.DatagramPacket;


public class FastUDPDiscardServer extends UDPServer
{
	public FastUDPDiscardServer()throws SocketException
	{
		super(9);
	}
	public static void main(String[] gcs)
	{
		try{
			UDPServer server=new FastUDPDiscardServer();
			server.start();
		}
		catch(SocketException e)
		{
			e.printStackTrace();
		}

	}
	public void respond(DatagramPacket packet)
	{
		
	}
}