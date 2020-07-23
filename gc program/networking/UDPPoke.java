import java.net.InetAddress;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;


public class UDPPoke
{
	DatagramPacket outGoing;
	DatagramSocket socket;
	private int bufSize;
	
	public UDPPoke(InetAddress address,int port)throws SocketException
	{
		this(address,port,8192,30000);
	}
	public UDPPoke(InetAddress host,int port,int bufSize,int timeOut)throws SocketException
	{
		outGoing=new DatagramPacket(new byte[1],1,host,port);
		this.bufSize=bufSize;
		
		socket=new DatagramSocket(0);
		
		socket.connect(host,port);
		socket.setSoTimeout(timeOut);
	}
	
	public byte[] poke()
	{
		byte[] response=null;
		try{
			socket.send(outGoing);
			DatagramPacket incoming=new DatagramPacket(new byte[bufSize],bufSize);
			socket.receive(incoming);
			int numBytes=incoming.getLength();
			System.arraycopy(incoming.getData(),0,response,0,numBytes);
		}		
			catch(IOException e)
			{
				
			}
			return response;
	}
	public static void main(String[] gcs)
	{
		InetAddress address;
		int port=0;
		
		
		try{
			
		    address=InetAddress.getByName("localhost");

			UDPPoke poker=new UDPPoke(address,port);
			byte[] response=poker.poke();
			if(response==null)
			{
				System.out.println("porare ");
				return;
			}
			String result;
				try{
					result=new String(response,"ASCII");
				}
				catch(UnsupportedEncodingException ex)
				{
					ex.printStackTrace();
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}