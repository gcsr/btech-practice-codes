import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.io.IOException;

public class HttpClient
{
	public static void main(String[] gcs)
	{
		try{
			SocketAddress address=new InetSocketAddress("127.0.0.1",80);
			SocketChannel channel=SocketChannel.open(address);
			
			ByteBuffer buffer=ByteBuffer.allocate(74);
			
			WritableByteChannel out=Channels.newChannel(System.out);
			
			while(channel.read(buffer)!=-1)
			{
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}