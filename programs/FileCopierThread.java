import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
public class FileCopierThread implements Runnable {
	int pos;
	FileChannel channel;	
	public FileCopierThread(int pos,FileChannel channel)
	{
		this.pos=pos;
		this.channel=channel;
	}
	public void run() {
		try
		{
			 URL root = new URL("http://cdn1b.video.pornhub.phncdn.com/videos/201301/13/8917311/480P_600K_8917311.mp4?rs=150&ri=1800&s=1365630502&e=1365632302&h=634d9c103b5b6ead263a7078012cb655&ms=0&OBT_fname=480P_600K_8917311.mp4%3Frs%3D150%26ri%3D1800%26s%3D1365630502%26e%3D1365632302%26h%3D634d9c103b5b6ead263a7078012cb655%26ms%3D0");
			 URLConnection uc = root.openConnection( );
		      String contentType = uc.getContentType( );
		      System.out.println(contentType);
		      int contentLength = uc.getContentLength( );
		      InputStream raw = uc.getInputStream( );
		      InputStream in  = new BufferedInputStream(raw);
		      byte[] data = new byte[100000];
		      in.skip(pos);
		      int bytesRead = 0;		      
		      int offset = 0;
		      System.out.println("writing data");
		      while (offset < contentLength) {
		    	  //System.out.print("writing");
		         bytesRead = in.read(data, offset, data.length-offset);
		         if (bytesRead == -1) break;
		         offset += bytesRead;
		      }
		      channel.position(10000);
		      ByteBuffer buffer=ByteBuffer.wrap(data);
		      synchronized(channel)
		      {
		    	  while(buffer.hasRemaining())channel.write(buffer);
		      }
		      in.close( );
		      System.out.println("over");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	}

}
