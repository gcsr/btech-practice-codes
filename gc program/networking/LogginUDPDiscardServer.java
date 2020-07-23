import java.net.SocketException;
import java.net.DatagramPacket;
import java.io.UnsupportedEncodingException;

public class LogginUDPDiscardServer extends UDPServer
{
	public LogginUDPDiscardServer()throws SocketException
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
		byte[] data=new byte[packet.getLength()];	
		System.arraycopy(packet.getData(),0,data,0,packet.getLength());
		try{
			String s=new String(data,"8859_1");
			System.out.println("System "+packet.getAddress()+" at port no" +packet.getPort()+" says "+s );
		}	
			catch(UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
	}
}