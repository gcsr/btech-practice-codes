import multithreading.MatchCounter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.File;
import java.util.Scanner;
import multithreading.SearchTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {
        
   
    public static void main(String[] gcs) {
    	Scanner in=new Scanner(System.in);
    	System.out.println("Enter Base Directory(e.g;. /usr/local/:)");
    	String directory=in.nextLine();
    	System.out.println("Enter Keyword");
    	String keyword=in.nextLine();
    	
    	ExecutorService pool=Executors.newCachedThreadPool();
    	
		MatchCounter counter=new MatchCounter(new File(directory),keyword,pool);    	
		Future<Integer> result=pool.submit(counter);
		
		try{
			System.out.println(result.get()+" matching files");
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
