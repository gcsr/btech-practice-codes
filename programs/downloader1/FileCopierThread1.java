//import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
//import java.net.
public class FileCopierThread1 implements Runnable {
	//
	FileChannel channel;
	long length;
	PictureObject object;
	int threadNumber;
	//Picture picture;
	long thisThreadPosition;
	long toLength;
	InputStream in;
	
	byte[] data=new byte[4096];
	int bytesRead;
	String threadFile;
	long[] threadPosition;
	String url;
	public FileCopierThread1(String url,long[] threadPosition,FileChannel channel,long toLength,PictureObject object,int threadNumber,String threadFile)
	{
		this.url=url;
		this.threadPosition=threadPosition;
		thisThreadPosition=threadPosition[threadNumber];
		this.channel=channel;
		//length=(toLength-pos);
		this.toLength=toLength;
		this.object=object;
		this.threadNumber=threadNumber;
		this.threadFile=threadFile;
		
		try
		{
			System.out.println("in thread "+threadNumber);
			 URL root = new URL(url);
			 URLConnection uc = root.openConnection( );
		     /* String contentType = uc.getContentType( );
		      System.out.println(contentType);
		      //int contentLength = uc.getContentLength( );*/
		      InputStream raw = uc.getInputStream( );
		      in  = new BufferedInputStream(raw);
		      //thisThreadPosition=threadPosition[threadNumber];
		      in.skip(thisThreadPosition);
		      System.out.println("connection created");
		      System.out.println("writing data");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	public void run() {
		
			try
			{  
				System.out.println("in run");
				while(thisThreadPosition<toLength)
			      {
					int pre=bytesRead;
			    	  bytesRead+=in.read(data);
			    	  
			    	  ByteBuffer buffer=ByteBuffer.wrap(data);
			    	  synchronized(channel)
			    	  {
			    		  channel.position(thisThreadPosition);
			    		  System.out.println("bytes red is "+bytesRead);
			    		  System.out.println("pos is "+thisThreadPosition);
			    		  while(buffer.hasRemaining())channel.write(buffer);
			    		  //threadPosition[threadNumber]+=bytesRead;
			    		  threadPosition[threadNumber]+=bytesRead-pre;
			    		  thisThreadPosition+=bytesRead-pre;
			    		 // pos+=bytesRead;
			    		  //File f=new File(threadFile);
			    		  
			    		  FileOutputStream fos = new FileOutputStream(threadFile);
			    		  DataOutputStream dos = new DataOutputStream(fos);
							
							for(int i=0;i<threadNumber;i++)
								dos.writeLong(threadPosition[i]);
							dos.close();
							fos.close();
							
			    	  }
			    	  
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
				
			}
	}

}
