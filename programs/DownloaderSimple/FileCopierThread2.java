import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopierThread2 implements Runnable {
	//
	FileChannel channel;
	long sizeOfThread;
	PictureObject object;
	int threadNumber;
	//Picture picture;
	long thisThreadPosition;
	long toLength;
	InputStream in;
	
	byte[] data=new byte[4096*5];
	int bytesRead=0;
	String threadFile;
	long[] threadPosition;
	int sizeOfThisThread;
	String url;
	long totalBytesRead;
	int offset=0;
	public FileCopierThread2(String url,long[] threadPosition,FileChannel channel,PictureObject object,int threadNumber,long sizeOfThread,String threadFile)
	{
		this.url=url;
		this.threadPosition=threadPosition;
		thisThreadPosition=threadPosition[threadNumber];
		this.channel=channel;
		this.sizeOfThread=sizeOfThread;
		this.toLength=(threadNumber+1)*sizeOfThread;
		this.object=object;
		this.threadNumber=threadNumber;
		this.threadFile=threadFile;
		sizeOfThisThread=(int)(toLength-thisThreadPosition);
		bytesRead=0;
		
		try
		{
			System.out.println("in thread "+threadNumber);
			 URL root = new URL(url);
			 URLConnection uc = root.openConnection( );
			 long thisThreadEndPosition=(toLength-1);
			 String byteRange = thisThreadPosition + "-" + thisThreadEndPosition;
			 System.out.println(byteRange);
			 uc.setRequestProperty("Range", "bytes=" + byteRange);
			 uc.connect();
			 InputStream raw = uc.getInputStream( );
			 in  = new BufferedInputStream(raw);
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
				  while(sizeOfThisThread>0)
			      {

					  //if(sizeOfThread>bytesRead)
			    	  bytesRead=in.read(data);
			    	  //System.out.println("bytes read is "+bytesRead);
			    	 // if(bytesRead<0)
			    		//  break;
			    	  ByteBuffer buffer=ByteBuffer.wrap(data,0,bytesRead);
			    	  sizeOfThisThread-=bytesRead;
			    	  totalBytesRead+=bytesRead;
			    	 // offset+=bytesRead;
			    	  synchronized(channel)
			    	  {
			    		  channel.position(thisThreadPosition);
			    		  while(buffer.hasRemaining())channel.write(buffer);
			    		  threadPosition[threadNumber]+=bytesRead;
			    		  thisThreadPosition+=bytesRead;
			    		  FileOutputStream fos = new FileOutputStream(threadFile);
			    		  DataOutputStream dos = new DataOutputStream(fos);
							for(int i=0;i<threadPosition.length;i++)
								dos.writeLong(threadPosition[i]);
							dos.close();
							fos.close();
						
			    	  }
			    	  //System.out.println(sizeOfThisThread);
			    	  
			      }
				  in.close();
				  System.out.println("total bytes Read is "+totalBytesRead);
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
