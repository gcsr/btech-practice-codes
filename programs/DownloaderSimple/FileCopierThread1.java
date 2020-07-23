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
	long sizeOfThread;
	PictureObject object;
	int threadNumber;
	//Picture picture;
	long thisThreadPosition;
	long toLength;
	InputStream in;
	
	byte[] data;
	int bytesRead=0;
	String threadFile;
	long[] threadPosition;
	String url;
	public FileCopierThread1(String url,long[] threadPosition,FileChannel channel,PictureObject object,int threadNumber,long sizeOfThread,String threadFile)
	{
		this.url=url;
		this.threadPosition=threadPosition;
		thisThreadPosition=threadPosition[threadNumber];
		this.channel=channel;
		//length=(toLength-pos);
		this.sizeOfThread=sizeOfThread;
		this.toLength=(threadNumber+1)*sizeOfThread;
		this.object=object;
		this.threadNumber=threadNumber;
		this.threadFile=threadFile;
		data=new byte[4096*5];
		
		try
		{
			System.out.println("in thread "+threadNumber);
			 URL root = new URL(url);
			 URLConnection uc = root.openConnection( );

				/*String byteRange = mStartByte + "-" + mEndByte;
				conn.setRequestProperty("Range", "bytes=" + byteRange);
				System.out.println("bytes=" + byteRange);

				 */
			 //uc.setRequestProperty("Range", arg1)
		     /* String contentType = uc.getContentType( );
		      System.out.println(contentType);
		      //int contentLength = uc.getContentLength( );*/
		     
			 //long thisThreadEndPosition=thisThreadPosition+sizeOfThread;
			 
			 //String byteRange = thisThreadPosition + "-" + thisThreadEndPosition;
			 //uc.setRequestProperty("Range", "bytes=" + byteRange);
			 uc.connect();
			 
			 InputStream raw = uc.getInputStream( );
		     
		      
		      in  = new BufferedInputStream(raw);
		      
		      
		      //System.out.println(thisThreadPosition+" thread no "+threadNumber);
		      
		      //in.pos=thisThreadPosition;
		      //in.mark((int)thisThreadPosition);
		      //in.skip(thisThreadPosition);
		      System.out.println("connection created");
		      System.out.println("writing data");
		      //System.out.println(byteRange);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	public void run() {
		
			try
			{  
				//System.out.println("in run");
				//System.out.println("to length is "+toLength);
				long loopLength=toLength-data.length;
				while(thisThreadPosition<loopLength)
			      {
					//int pre=bytesRead;
			    	  bytesRead=in.read(data);
			    	  
			    	  ByteBuffer buffer=ByteBuffer.wrap(data,0,bytesRead);
			    	  
			    	  synchronized(channel)
			    	  {
			    		  
			    		  channel.position(thisThreadPosition);
			    		  //System.out.println("bytes red is "+bytesRead);
			    		  //System.out.println("pos is "+thisThreadPosition);
			    		  while(buffer.hasRemaining())channel.write(buffer);
			    		  //threadPosition[threadNumber]+=bytesRead;
			    		  //channel.
			    		//  long s=channel.write(data,0,bytesRead);
			    		 // System.out.println("bytes red is "+bytesRead);
			    		  
			    		  threadPosition[threadNumber]+=bytesRead;
			    		  thisThreadPosition+=bytesRead;
			    		 // pos+=bytesRead;
			    		  //File f=new File(threadFile);
			    		  
			    		  FileOutputStream fos = new FileOutputStream(threadFile);
			    		  DataOutputStream dos = new DataOutputStream(fos);
							
							for(int i=0;i<threadPosition.length;i++)
								dos.writeLong(threadPosition[i]);
							dos.close();
							fos.close();
							//System.out.println("bytes red is "+bytesRead);
							//System.out.println("pos is "+thisThreadPosition);
			    	  }
			    	  
			      }  
				
					byte[] last=new byte[(int)(toLength-thisThreadPosition)];
					
					int bytesRead = 0;		      
				      int offset = 0;
				      //System.out.println("connection created");
				      //System.out.println("writing data");
				      while (offset < last.length) {
				    	 // System.out.print("writing");
				         bytesRead = in.read(data, offset, data.length-offset);
				         if (bytesRead == -1) break;
				         offset += bytesRead;
				         				         
				      }
				      synchronized(channel)
			    	  {
			    		  ByteBuffer buffer=ByteBuffer.wrap(last);
			    		  channel.position(thisThreadPosition);
			    		  while(buffer.hasRemaining())channel.write(buffer);
			    		  
			    		 // System.out.println("bytes red is "+bytesRead);
			    		  
			    		  threadPosition[threadNumber]+=last.length;
			    		  thisThreadPosition+=last.length;
			    		 // pos+=bytesRead;
			    		  //File f=new File(threadFile);
			    		  
			    		  FileOutputStream fos = new FileOutputStream(threadFile);
			    		  DataOutputStream dos = new DataOutputStream(fos);
							
							for(int i=0;i<threadPosition.length;i++)
								dos.writeLong(threadPosition[i]);
							dos.close();
							fos.close();
							//System.out.println("bytes red is "+bytesRead);
							//System.out.println("pos is "+thisThreadPosition);
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
