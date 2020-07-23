import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
//import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;


// change max no of allowed collections
public class MainThread {
	public static void main(String[] args) {
		System.out.println("enter no of threads");
		Scanner scanner=new Scanner(System.in);
		int noOfThreads=scanner.nextInt();
		
		try
		{
			
			  URL root = new URL("http://cdn1b.video.pornhub.phncdn.com/videos/201301/13/8917311/480P_600K_8917311.mp4?rs=150&ri=1800&s=1365630502&e=1365632302&h=634d9c103b5b6ead263a7078012cb655&ms=0&OBT_fname=480P_600K_8917311.mp4%3Frs%3D150%26ri%3D1800%26s%3D1365630502%26e%3D1365632302%26h%3D634d9c103b5b6ead263a7078012cb655%26ms%3D0");
			  URLConnection uc = root.openConnection( );
			  
		      String contentType = uc.getContentType( );
		      System.out.println(contentType);
		      int contentLength = uc.getContentLength( );
		      int lengthOfThreads=contentLength/noOfThreads;
		      int lengthOfThisThread=contentLength-noOfThreads*lengthOfThreads;
		      System.out.println("noOfThreads is "+noOfThreads);
		      System.out.println("contentLength is "+contentLength);
		      //System.out.println("writing data");
		      System.out.println("lengthOfThisThread  is "+lengthOfThisThread);
		      InputStream raw = uc.getInputStream( );
		      InputStream in  = new BufferedInputStream(raw);
		      FileOutputStream fout = new FileOutputStream("d:/hi1.flv");
		      FileChannel channel=fout.getChannel();
		
		      PictureObject object=new PictureObject(noOfThreads);
		      Picture picture=new Picture(object,lengthOfThreads);
		      for(int i=0;i<noOfThreads;i++)
		      {
		    	  Thread thread=new Thread(new FileCopierThread(i*lengthOfThreads,channel,lengthOfThreads,object,i,picture));
		    	  thread.start();
		      }
		    
		     
		      in.close();
		     
		      
		      System.out.println("over");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
