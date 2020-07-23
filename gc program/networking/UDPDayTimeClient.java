import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDayTimeClient
{
	public static void main(String[] gcs)
	{
		try{
			InetAddress address=InetAddress.getByName("localhost");
			DatagramSocket socket=new DatagramSocket(0);
			
			
			socket.connect(address,9);
			String d="data";
			
			byte[] data=d.getBytes("ASCII");
			byte[] buffer=new byte[512];
			
			
			
			
			
			
			DatagramPacket packet=new DatagramPacket(data,data.length,address,9);
			socket.send(packet);
			
			System.out.println("sending data");

			DatagramPacket packet1=new DatagramPacket(buffer,buffer.length);
			socket.receive(packet1);
			String s=new String(buffer,"8859_1");
			System.out.println(s);
			
		
			
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}