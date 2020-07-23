import java.io.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class FileChannel1 {
	public static void main(String[] args) {
		try
		{
		// 	TODO Auto-generated method stub
			//http://www.fsishare.com/mmsclips/fsi.1475.class-room.3gp
			//http://www.fsishare.com/mmsclips/fsi.1475.class-room.wmv
			//http://88.208.12.6/key=0rUIg1VnsP6%2cend=1363441199/data=18446744072444432678/reftag=5412162/buffer=450K/speed=83200/s/xh/229253_south_indian_porn_movie.flv
			//http://vs37.hardsextube.com/content/0d722e1368ce9bae8336d7dbd0c677c7/51444e45/2013/02/02/2013-02-02-HardSexTube-Kamininewvideo.mpg.mp4?mp4mod=1&start=0&OBT_fname=2013-02-02-HardSexTube-Kamininewvideo.mov
			URL url=new URL("http://vs37.hardsextube.com/content/0d722e1368ce9bae8336d7dbd0c677c7/51444e45/2013/02/02/2013-02-02-HardSexTube-Kamininewvideo.mpg.mp4?mp4mod=1&start=0&OBT_fname=2013-02-02-HardSexTube-Kamininewvideo.mov");
			String host=url.getHost();
			int port=url.getPort();
			String file=url.getFile();
			
			
			System.out.println("host is "+host+" port is "+port+" file is "+file);
			
			
			
			SocketAddress address=new InetSocketAddress(host,80);
			
			
			SocketChannel channel=SocketChannel.open(address);
			String request = "GET " + file + " HTTP/1.1\r\n"
		     + "User-Agent: HTTPGrab\r\n"
		     + "Accept: video/*\r\n"
		     + "Connection: close\r\n"
		     + "Host: " + host + "\r\n"
		     + "\r\n";
			
			
			ByteBuffer header = ByteBuffer.wrap(request.getBytes("US-ASCII"));
		    channel.write(header);

			FileOutputStream fout = new FileOutputStream("d:/hi1.flv");
			
			FileChannel fileChannel=fout.getChannel();
			
			ByteBuffer buffer=ByteBuffer.allocate(8192);
			channel.read(buffer);
			buffer.flip();
			
			char ss=' ';
			char qq=' ';
			char pp=' ';
			while(true)
			{
				char rr=qq;
				qq=pp;
				pp=ss;
				ss=(char)buffer.get();
			
				
				if(rr=='\r' && qq=='\n' && pp=='\r' && ss=='\n')
					break;
				System.out.print(ss);
			
			}
			System.out.println(buffer.position());
			
			
			//channel.
			
			
			fileChannel.write(buffer);
			buffer.clear();
			int p=0;
			//channel.
			while(channel.read(buffer)!=-1&&p!=50)
			{
				
			
				buffer.flip();
				fileChannel.write(buffer);
				buffer.clear();
				p++;
			}
			
			fileChannel.close();
			channel.close();
			System.out.println("over");
			
			
		}
		catch(MalformedURLException ex)
		{
			System.out.println("ill formed url");
		}
		
		catch(IOException ex)
		{
			ex.printStackTrace();
		}

	}

}
