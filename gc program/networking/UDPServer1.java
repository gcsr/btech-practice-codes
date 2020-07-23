import java.net.*;
import java.util.Date;
public class UDPServer1
{
	int bufSize;
	DatagramSocket socket;
	
	public static void main(String[] gcs)
	{
		int bufSize=8192;
		try{
		DatagramSocket socket=new DatagramSocket(9);
	
		byte[] buffer=new byte[bufSize];
			DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
			
				socket.receive(packet);
				System.out.println("in respond");
				Date date=new Date();
				String response=date.toString()+"\r\n";
				byte[] data=response.getBytes("ASCII");
				DatagramPacket outGoing=new DatagramPacket(data,data.length,packet.getAddress(),packet.getPort());
			
				socket.send(outGoing);
				System.out.println("sending data");
			}
			catch(SocketException e)
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
}