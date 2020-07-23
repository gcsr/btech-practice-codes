import java.net.SocketException;
import java.net.DatagramPacket;
import java.util.Date;
import java.io.IOException;

public class UDPDayTimeServer extends UDPServer
{
	public UDPDayTimeServer()throws SocketException
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
			System.out.println("in respond");
			Date date=new Date();
			String response=date.toString()+"\r\n";
			byte[] data=response.getBytes("ASCII");
			DatagramPacket outGoing=new DatagramPacket(data,data.length,packet.getAddress(),packet.getPort());
			
			socket.send(outGoing);
			
			
		}		
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
}