import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopierThread3 implements Runnable {
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
	String toFile;
	public FileCopierThread3(String url,long[] threadPosition,String toFile,PictureObject object,int threadNumber,long sizeOfThread,String threadFile)
	{
		this.url=url;
		this.threadPosition=threadPosition;
		thisThreadPosition=threadPosition[threadNumber];
		//this.channel=channel;
		this.sizeOfThread=sizeOfThread;
		this.toLength=(threadNumber+1)*sizeOfThread;
		this.object=object;
		this.threadNumber=threadNumber;
		this.threadFile=threadFile;
		sizeOfThisThread=(int)(toLength-thisThreadPosition);
		bytesRead=13;
		this.toFile=toFile;
		
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
			  FileOutputStream fout = new FileOutputStream(toFile);
			  //System.out.println("video type is "+videoType);
			  System.out.println("created video file");
		      channel=fout.getChannel();
		     // channel.lock(thisThreadPosition, sizeOfThisThread, true);
		      channel.position(thisThreadPosition);
			 //channel 
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
				bytesRead=in.read(data);
				  do
			      {
					  ByteBuffer buffer=ByteBuffer.wrap(data,0,bytesRead);
					  synchronized(channel)
					  {
						  channel.position(thisThreadPosition);
						  while(buffer.hasRemaining())channel.write(buffer);
						  thisThreadPosition+=bytesRead;
						  System.out.println("thread no "+threadNumber+" threadPosition "+thisThreadPosition+" channel pos "+channel.position());
					  }
			    	  threadPosition[threadNumber]+=bytesRead;
			    	  thisThreadPosition+=bytesRead;
			    	  FileOutputStream fos = new FileOutputStream(threadFile);
			    	  DataOutputStream dos = new DataOutputStream(fos);
					  for(int i=0;i<threadPosition.length;i++)
						  dos.writeLong(threadPosition[i]);
					  dos.close();
					  fos.close();
					  
					  
					  bytesRead=in.read(data);
					  
					}while(bytesRead>0);
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
