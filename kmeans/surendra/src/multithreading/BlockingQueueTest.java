package multithreading;


import multithreading.FileEnumerationTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.File;
import java.util.Scanner;
import multithreading.SearchTask;
public class BlockingQueueTest {
        
   
    public static void main(String[] gcs) {
    	Scanner in=new Scanner(System.in);
    	System.out.println("Enter Base Directory(e.g;. /usr/local/:)");
    	String directory=in.nextLine();
    	System.out.println("Enter Keyword");
    	String keyword=in.nextLine();
    	
    	final int FILE_QUEUE_SIZE=10;
    	final int SEARCH_THREADS=10;
    	
    	
    	BlockingQueue<File> queue=new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
    	
    	FileEnumerationTask enumerator=new FileEnumerationTask(queue,new File(directory));
    	
    	new Thread(enumerator).start();
    	
    	for(int i=1;i<=SEARCH_THREADS;i++)
    		new Thread(new SearchTask(queue,keyword)).start();
    	
    	
    }
    
    
   
}
