
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
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest implements Callable<String>
{
	private  File[] files;
	private int count;
	private ExecutorService pool;
	
	String digest;
	public Digest(File[] directory,ExecutorService pool)
	{
		this.files=directory;
		this.pool=pool;
	}
	public Digest(File directory,ExecutorService pool)
	{
		files=new File[1];
		files[0]=directory;
		this.pool=pool;
	}
	public String call()
	{
		digest="";
		try{
		ArrayList<Future<String>> results=new ArrayList<Future<String>>();
		
		for(File file:files){
				/*Digest counter=new Digest(file,pool);
				Future<String> result=pool.submit(counter);
				results.add(result);*/
				find(file);
		}	
		/*for(Future<String> result:results)
		{
			try
			{
				digest+=result.get();
			}
		catch(ExecutionException e)
			{
				e.printStackTrace();
			}
			
		}*/			
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}	
			return digest;
	}
	public void find(File f)throws IOException
	{
		try{
		
		FileInputStream in=new FileInputStream(f);
		MessageDigest sha=MessageDigest.getInstance("SHA");
		DigestInputStream din=new DigestInputStream(in,sha);
		int b;

      	while ((b = din.read( )) != -1) ;

      	din.close( );

      	byte[] digests = sha.digest( );

	   	String res =f.toString( );

       	res+=(": ");

      	for (int i = 0; i < digests.length; i++) {

        res+=(digests[i] + " ");
      	}
		
		digest+=res+"\n";
		

    	}

    	catch (IOException ex) {

      	System.err.println(ex);

    	}

    	catch (NoSuchAlgorithmException ex) {

      	System.err.println(ex);

    	}
		
	
	}
	
}