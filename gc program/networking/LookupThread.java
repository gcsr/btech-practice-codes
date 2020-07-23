import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class LookupThread extends Thread
{
	private List entries;
	PooledWebLog log;
	public LookupThread(List entries,PooledWebLog log)
	{
		this.entries=entries;
		this.log=log;
	}
	public void run()
	{
		String entry;
		while(true)
		{
			synchronized (entries)
			{
				while(entries.size()==0)
				{
					if(log.isFinished())return;
					try{
						entries.wait();
					}
					catch(InterruptedException e)
					{
						
					}
				}
				entry=(String)entries.remove(entries.size()-1);
			}
			 	int index=entry.indexOf(' ',0);
				String ip=entry.substring(0,index);
				String theRest=entry.substring(index,entry.length());
				
				try{
					ip=InetAddress.getByName(ip).getHostName();
					System.out.println(ip+theRest);
				}
				catch(UnknownHostException e)
				{
					System.out.println(entry);
				}
				try{
					log.log(ip+theRest);
				}
				catch(IOException e)
				{
				}
				this.yield();
		}
	}
}