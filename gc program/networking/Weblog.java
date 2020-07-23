import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Date;

public class Weblog
{
	public static void main(String[] gcs)
	{
		Date start=new Date();
		try{
			FileInputStream fin=new FileInputStream("web.txt");
			Reader in=new InputStreamReader(fin);
			SafeBufferReader bin=new SafeBufferReader(in);
			String entry=null;
			
			while((entry=bin.readLine())!=null)
			{
				int index=entry.indexOf(' ',0);
				String ip=entry.substring(0,index);
				String theRest=entry.substring(index,entry.length());
				
				try{
					InetAddress address=InetAddress.getByName(ip);
					System.out.println(address.getHostName()+theRest);
				}
				catch(UnknownHostException e)
				{
					System.out.println(entry);
				}
				
				
			}
		}
		catch(IOException e)
		{
			System.out.println("Exception: "+e);
		}
		
		Date end=new Date();
		
		long  elapsedTime=(end.getTime()-start.getTime())/100;
		System.out.println("Elapsed Time: "+elapsedTime+" seconds");
		
		
	}
}