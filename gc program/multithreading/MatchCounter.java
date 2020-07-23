package multithreading;

import java.util.concurrent.BlockingQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.io.FileInputStream;
import java.util.concurrent.ExecutorService;

public class MatchCounter implements Callable<Integer>
{
	private  File directory;
	String keyword;
	private int count;
	private ExecutorService pool;
	
	public MatchCounter(File directory,String keyword,ExecutorService pool)
	{
		this.directory=directory;
		this.keyword=keyword;
		this.pool=pool;
	}
	public Integer call()
	{
		count=0;
		try{
		File[] files=directory.listFiles();
		ArrayList<Future<Integer>> results=new ArrayList<Future<Integer>>();
		
		for(File file:files){
			if(file.isDirectory())
			{
				MatchCounter counter=new MatchCounter(file,keyword,pool);
				Future<Integer> result=pool.submit(counter);
				results.add(result);
			}
			else
			{
				if(search(file))count++;
			}		
		}	
		for(Future<Integer> result:results)
		{
			System.out.println("for loop");
			try
			{
				count+=result.get();
			}
			catch(ExecutionException e)
			{
				e.printStackTrace();
			}
			
		}			
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
			return count;
	}
	public boolean search(File file)throws IOException
	{
		
		try{
		boolean found=false;
		Scanner in=new Scanner(new FileInputStream(file));
		while(!found&&in.hasNextLine())
		{
			String line=in.nextLine();
			if(line.contains(keyword))
				found=true;
		}
		in.close();
		return found;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
}