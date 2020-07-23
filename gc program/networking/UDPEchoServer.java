import java.net.SocketException;
import java.net.DatagramPacket;
import java.io.IOException;

public class UDPEchoServer extends UDPServer
{
	public UDPEchoServer()throws SocketException
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
		try{
			DatagramPacket outGoing=new DatagramPacket(packet.getData(),packet.getLength(),packet.getAddress(),packet.getPort());
			socket.send(outGoing);
			socket.send(outGoing);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}