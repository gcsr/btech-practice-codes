import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DigestTest {
        
   
    public static void main(String[] gcs) {
    	
    	
    	String[] ss={"Test.java","Digest.java"
    	};
    	
    	File[] sss=new File[2];
    	
    	sss[0]=new File(ss[0]);
    	sss[1]=new File(ss[1]);
    	
    	ExecutorService pool=Executors.newCachedThreadPool();
    	
		Digest counter=new Digest(sss,pool);    	
		Future<String> result=pool.submit(counter);
		
		try{
			System.out.println(result.get());
		}    	
			catch(ExecutionException e)
			{
				e.printStackTrace();
			}
		catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		pool.shutdown();

		
		int largestPoolSize=((ThreadPoolExecutor)pool).getLargestPoolSize();
		
		System.out.println("largest pool size= "+largestPoolSize);	
    	
    }
    
    
   
}
