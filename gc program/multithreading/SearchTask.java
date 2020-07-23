package multithreading;
import java.util.concurrent.BlockingQueue;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.File;

public class SearchTask implements Runnable
{
	BlockingQueue<File> queue;
	String keyword;
	
	public SearchTask(BlockingQueue<File> queue,String keyword)
	{
		this.queue=queue;
		this.keyword=keyword;
		
	}
	public void run()
	{
		try{
			boolean done=false;
			while(!done)
			{
				File file=queue.take();
				if(file==FileEnumerationTask.DUMMY)
				{
					queue.put(file);
					done=true;
				}
				else
				search(file);	
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public void search(File file)throws IOException
	{
		System.out.println(file.getName());
		System.out.println(Thread.currentThread());
		Scanner in=new Scanner(new FileInputStream(file));
		int lineNumber=0;
		while(in.hasNextLine())
		{
			lineNumber++;
			String line=in.nextLine();
			
			if(line.contains(keyword))
				System.out.printf("%s:%d:%s\n",file.getPath(),lineNumber,line);
		}
		in.close();
	}
	
}