
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MatchCounter implements Callable<ArrayList<String>>
{
	private  File directory;
	String keyword;
	ArrayList<String> result=new ArrayList<String>();
	private ExecutorService pool;
	String extension;
	
	public static void main(String[] gcs)throws Exception{
		String directory="E:\\old laptop\\d drive\\";
		File f=new File(directory);
		String keyword="sca";
		String extension="pdf";
		ExecutorService pool=Executors.newFixedThreadPool(10);
		MatchCounter mc=new MatchCounter(f,keyword,pool,extension);
		Future<ArrayList<String>> ff=pool.submit(mc);
		
		try{
			Thread.currentThread().sleep(30000);
		}catch(Exception ex){
			
		}
		
		pool.shutdown();
		save(ff.get());
	}
	
	public MatchCounter(File directory,String keyword,ExecutorService pool,String extension)
	{
		this.directory=directory;
		this.keyword=keyword;
		this.pool=pool;
		this.extension=extension;
	}
	public ArrayList<String> call()
	{
		System.out.println("called");
		
		try{
			File[] files=directory.listFiles();
			ArrayList<Future<ArrayList<String>>> results=new ArrayList<Future<ArrayList<String>>>();
			
			for(File file:files){
				if(file.isDirectory())
				{
					System.out.println(file.getAbsolutePath());
					MatchCounter counter=new MatchCounter(file,keyword,pool,extension);
					Future<ArrayList<String>> result=pool.submit(counter);
					results.add(result);
				}
				else
				{
					if(accept(file)){
						//System.out.println(file.getAbsolutePath());
						add(file);
					}
						
				}		
			}	
			for(Future<ArrayList<String>> thread:results)
			{
				result.addAll(thread.get());
				
			}	
			
			
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return result;
			
	}
	
	public static void save(ArrayList<String> str){		
		System.out.println("out called");
		Iterator<String> itr=str.iterator();
		StringBuilder sb=new StringBuilder();
		while(itr.hasNext()){
			sb.append(itr.next());
		}
		try{
			FileWriter fileWritter = new FileWriter("output.txt");
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(sb.toString());
			bufferWritter.close();	           	    	    
			//System.out.println("fullData is written");    	     
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void add(File f) throws Exception
	{
		
		//System.out.println("FileName:" + f.getAbsolutePath());

		//File filesize1 = new File(f.getAbsolutePath());

		long fileSize = f.length();

		// System.out.println("File size in KB is : " +
		// (double)fileSize/1024);

		// write code to fetch creation and modified date

		// put all these in a file

		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		File fileModifieddate = new File(f.getAbsolutePath());

		Path file1 = Paths.get(f.getAbsolutePath());

		String filename = file1.getFileName().toString();

		String LocationType = "Local";

		BasicFileAttributes attr = Files.readAttributes(file1,
				BasicFileAttributes.class);

		// System.out.println("creationTime: " + attr.creationTime());

		// System.out.println("Modification Date: "+
		// sdf.format(fileModifieddate.lastModified()));

		//System.out.println(f.getAbsolutePath());
		String kd=(filename + "\t" + f.getAbsolutePath() + "\t"
				+ LocationType + "\t" + attr.creationTime() + "\t"
				+ sdf.format(fileModifieddate.lastModified()) + "\t"
				+ fileSize + "\n");
		//System.out.println(kd);
		result.add(kd);

	}
	
	
	public boolean accept(File file) {

		
		if ( (((file.getName().toLowerCase().endsWith("doc"))
				|| (file.getName().toLowerCase().endsWith("pdf"))
				|| (file.getName().toLowerCase().endsWith("txt"))) && (file.getName().toLowerCase().contains(keyword)))) {
			return true;
		}

		return false;

	}
	
}