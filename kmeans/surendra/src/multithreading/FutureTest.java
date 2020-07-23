package multithreading;

import multithreading.MatchCounter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.File;
import java.util.Scanner;
import multithreading.SearchTask;
public class FutureTest {
        
   
    public static void main(String[] gcs) {
    	Scanner in=new Scanner(System.in);
    	System.out.println("Enter Base Directory(e.g;. /usr/local/:)");
    	String directory=in.nextLine();
    	System.out.println("Enter Keyword");
    	String keyword=in.nextLine();
    	
		MatchCounter counter=new MatchCounter(new File(directory),keyword);    	
		FutureTask<Integer> task=new FutureTask<Integer>(counter);
		
		    	
    	Thread t=new Thread(task);
    	t.start();
    	
		try{
			System.out.println(task.get()+" matching files");
		}    	
			catch(ExecutionException e)
			{
				e.printStackTrace();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
    	
    }
    
    
   
}
