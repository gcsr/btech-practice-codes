import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.io.IOException;

public class IntgenClient
{
	public static void main(String[] gc)
	{
		try{
			SocketAddress address=new InetSocketAddress("127.0.0.1",19);
			SocketChannel client=SocketChannel.open(address);
			
			ByteBuffer buffer=ByteBuffer.allocate(4);
			IntBuffer view=buffer.asIntBuffer();
			
			
			for(int expected=0;;expected++)
			{
				client.read(buffer);
				int actual=view.get();
				buffer.clear();
				view.rewind();
				
				if(actual!=expected)
				{
					System.err.println("extected is "+expected+"but get is "+actual);
					break;
				}
				System.out.println(actual);
			}
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}