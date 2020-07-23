import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.IOException;
public class ChargenServer{
	public static void main(String[] gcs){
		int port=19;
		int k=0;
		System.out.println("Listening on port 19 for connections");
		byte[] rotation=new byte[95*2];
		for(byte i=' ';i<='~';i++){
			rotation[i-' ']=i;
			rotation[i+95-' ']=i;
		}
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
						SelectionKey key2=client.register(selector,SelectionKey.OP_WRITE);
						ByteBuffer buffer=ByteBuffer.allocate(74);
						buffer.put(rotation,0,72);
						buffer.put((byte)'\r');
						buffer.put((byte)'\n');
						buffer.flip();
						key2.attach(buffer);
					}
					else if(key.isWritable()){
						SocketChannel client=(SocketChannel)key.channel();
						ByteBuffer buffer=(ByteBuffer)key.attachment();
						if(!buffer.hasRemaining()){
							buffer.rewind();
							int first=buffer.get();
							buffer.rewind();
							int position=first-' '+1;
							buffer.put(rotation,position,72);
							buffer.put((byte)'\r');
							buffer.put((byte)'\n');
							buffer.flip();
						}
						client.write(buffer);
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