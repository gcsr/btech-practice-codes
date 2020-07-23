import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

public class PooledWebLog
{
	
	BufferedReader in;
	BufferedWriter out;
	private int numberOfThreads;
	private List entries=Collections.synchronizedList(new LinkedList());
	private boolean finished=false;
	public PooledWebLog(InputStream in,OutputStream out,int noofThreads)
	{
		this.in=new BufferedReader(new InputStreamReader(in));
		this.out=new BufferedWriter(new OutputStreamWriter(out));
		numberOfThreads=noofThreads;
		
	}
	public boolean isFinished( ) {

    return this.finished; 

  }


	public void processLogFile()
	{
		for(int i=0;i<numberOfThreads;i++)
		{
			Thread t=new LookupThread(entries,this);
			t.start();
		}
		try{
			String entry=in.readLine();
			while(entry!=null)
			{
				if(entries.size()>numberOfThreads)
				{
					try{
					Thread.sleep((long)1000.0/numberOfThreads);
					}
					catch(InterruptedException e)
					{}
						continue;
				}
				synchronized (entries)
				{
					entries.add(0,entry);
					entries.notifyAll();
				}
				entry=in.readLine();
				Thread.yield();
				
			}
		}
		catch(IOException e)
		{
		}
	}
	public void log(String entry)throws IOException
	{
		out.write(entry+System.getProperty("line.seperator","\r\n"));
		out.flush();
	}
	public static void main(String[] gcs)
	{
		try{
			PooledWebLog tw=new PooledWebLog(new FileInputStream(""),System.out,100);
			tw.processLogFile();
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}