import java.nio.ByteBuffer;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.channels.*;
import java.net.*;
import java.util.Set;
import java.util.Iterator;
import java.io.FileInputStream;


public class NonblockingSingleHttpServer
{
	private ByteBuffer contentBuffer;
	private int port=80;
	
	public static void main(String[] gcs)
	{
		try{
			FileInputStream fin=new FileInputStream("C:/gc/html/2.html");
			FileChannel in=fin.getChannel();
			
			ByteBuffer input=in.map(FileChannel.MapMode.READ_ONLY,0,in.size());
			NonblockingSingleHttpServer server=new NonblockingSingleHttpServer(input,"ASCII","text/html",80);
			server.run();
			
		}
		catch(Exception  e)
		{
			e.printStackTrace();
		}
	}
	public NonblockingSingleHttpServer(ByteBuffer data,String encoding,String MIMEType,int port)throws UnsupportedEncodingException
	{
		this.port=port;
		
		String header="HTTP/1.0 200 OK\r\n"
			+"Server:oneFile 2.0\r\n"
				+"Content-length: "+data.limit()+"\r\n"
					+"Content-Type: "+MIMEType+"\r\n\r\n";
		byte[] headerData=header.getBytes("ASCII");
		
		ByteBuffer buffer=ByteBuffer.allocate(data.limit()+headerData.length);
		
		buffer.put(headerData);
		buffer.put(data);
		
		buffer.flip();
		
		this.contentBuffer=buffer;
		
					
	}
	
	
	public void run()throws IOException
	{
			ServerSocketChannel serverChannel=ServerSocketChannel.open();
			ServerSocket ss=serverChannel.socket();
			InetSocketAddress address=new InetSocketAddress(port);
			ss.bind(address);
			serverChannel.configureBlocking(false);
			Selector selector=Selector.open();
			serverChannel.register(selector,SelectionKey.OP_ACCEPT);
		while(true){
			try{
				selector.select();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			Set readyKeys=selector.selectedKeys();
			Iterator iterator=readyKeys.iterator();
			while(iterator.hasNext()){
				SelectionKey key=(SelectionKey)iterator.next();
				iterator.remove();
				try{
					if(key.isAcceptable()){
						ServerSocketChannel server=(ServerSocketChannel)key.channel();
						SocketChannel client=server.accept();
						System.out.println("accepted connection from"+client);
						client.configureBlocking(false);
						SelectionKey key2=client.register(selector,SelectionKey.OP_WRITE);
						key2.attach(contentBuffer.duplicate());

						
					}
					/*else if(key.isReadable()){
						SocketChannel channel=(SocketChannel)key.channel();
						ByteBuffer buffer=ByteBuffer.allocate(4096);
						channel.read(buffer);
						key.interestOps(SelectionKey.OP_WRITE);

						key.attach(contentBuffer.duplicate());

						
					}*/
					else if(key.isWritable()){
						SocketChannel channel=(SocketChannel)key.channel();
						ByteBuffer buffer=(ByteBuffer)key.attachment();
						if(buffer.hasRemaining())
						{
							channel.write(buffer);
						}
						else
						{
							channel.close();
						}
						
					}
				}
				catch(IOException e){
					key.cancel();
					try{
						key.channel().close();
					}
					catch(IOException ex){}
					
				}
			}
		}
	}
}