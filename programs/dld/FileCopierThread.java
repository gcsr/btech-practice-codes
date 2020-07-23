//import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
//import java.net.
public class FileCopierThread implements Runnable {
	int pos;
	FileChannel channel;
	int length;
	PictureObject object;
	int threadNumber;
	Picture picture;
	public FileCopierThread(int pos,FileChannel channel,int length,PictureObject object,int threadNumber,Picture picture)
	{
		this.picture=picture;
		this.pos=pos;
		this.channel=channel;
		this.length=length;
		this.object=object;
		this.threadNumber=threadNumber;
	}
	public void run() {
		try
		{
			System.out.println("in thread "+threadNumber);
			 URL root = new URL("" +
			 		"http://cdn1b.video.pornhub.phncdn.com/videos/201301/13/8917311/480P_600K_8917311.mp4?rs=150&ri=1800&s=1365629293&e=1365631093&h=cc252b43e8dc8bd4ea72694e18d261c6&ms=0&OBT_fname=480P_600K_8917311.mp4%3Frs%3D150%26ri%3D1800%26s%3D1365629293%26e%3D1365631093%26h%3Dcc252b43e8dc8bd4ea72694e18d261c6%26ms%3D0");
			 URLConnection uc = root.openConnection( );
		      String contentType = uc.getContentType( );
		      System.out.println(contentType);
		      //int contentLength = uc.getContentLength( );
		      InputStream raw = uc.getInputStream( );
		      InputStream in  = new BufferedInputStream(raw);
		      byte[] data = new byte[length];
		      in.skip(pos);
		      int bytesRead = 0;		      
		      int offset = 0;
		      System.out.println("connection created");
		      System.out.println("writing data");
		      while (offset < length) {
		    	 // System.out.print("writing");
		         bytesRead = in.read(data, offset, data.length-offset);
		         if (bytesRead == -1) break;
		         offset += bytesRead;
		         object.cmpl[threadNumber]=offset;
		         picture.repaint();
		         
		      }
		     
		      ByteBuffer buffer=ByteBuffer.wrap(data);
		      synchronized(channel)
		      {
		    	  channel.position(pos);
		    	  while(buffer.hasRemaining())channel.write(buffer);
		      }
		      in.close( );
		      object.threadsCompleted++;
		      if(object.threadsCompleted==object.noOfThreads)
		      {	 
		    	  channel.close();
		    	  System.out.println("over");
		      }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	}

}
