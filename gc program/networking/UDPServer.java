import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;

public abstract class UDPServer extends Thread
{
	int bufSize;
	DatagramSocket socket;
	
	public UDPServer(int port)throws SocketException
	{
		this(port,8192);
	}
	public UDPServer(int port,int bufSize)throws SocketException
	{
		this.bufSize=bufSize;
		socket=new DatagramSocket(port);
	}
	public void run()
	{
		byte[] buffer=new byte[bufSize];
		while(true)
		{
			DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
			try{
				socket.receive(packet);
				System.out.println(packet.getData());
				this.respond(packet);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	public abstract void respond(DatagramPacket packet);
}