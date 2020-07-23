import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.IOException;
public class EchoServer{
	public static void main(String[] gcs){
		int port=7;
		int k=0;
		System.out.println("Listening on port 7 for connections");
		ServerSocketChannel serverChannel;
		Selector selector;
		try{
			serverChannel=ServerSocketChannel.open();
			ServerSocket ss=serverChannel.socket();
			InetSocketAddress address=new InetSocketAddress(port);
			ss.bind(address);
			serverChannel.configureBlocking(false);
			selector=Selector.open();
			serverChannel.register(selector,SelectionKey.OP_ACCEPT);
		}
		catch(IOException e){
			e.printStackTrace();
			return;
		}
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
						SelectionKey key2=client.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ);
						ByteBuffer buffer=ByteBuffer.allocate(10);
						key2.attach(buffer);
					}
					else if(key.isReadable()){
						SocketChannel client=(SocketChannel)key.channel();
						ByteBuffer output=(ByteBuffer)key.attachment();
						client.read(output);
						
					}
					else if(key.isWritable()){
						SocketChannel client=(SocketChannel)key.channel();
						ByteBuffer buffer=(ByteBuffer)key.attachment();
						buffer.flip();
						client.write(buffer);
						buffer.compact();
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