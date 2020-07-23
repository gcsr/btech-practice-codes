import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;


// change max no of allowed collections
public class MainThread2{
	PictureObject object;

	long sizeOfFile;
	int noOfThreads=1;
	long sizeOfFinaThread;
	String videoFile,urlFieldText,toFieldText,threadFile;
    String videoType="";
    String saveTo="";
    long[] threadPosition;
    long sizeOfThread;
    Thread[] threads;
	public MainThread2(String urlFieldText,String toFieldText,String threadFieldText)
	{
		this.urlFieldText=urlFieldText;
		this.toFieldText=toFieldText;
		//this.threadFieldText=threadFieldText;
		noOfThreads=Integer.parseInt(threadFieldText);
	}
	public void createThreadFile()throws Exception
	{
		System.out.println("in createThread File method");
		threadFile=toFieldText+"Thread.txt";
	    //String videoFile=saveTo.substring(0,saveTo.lastIndexOf("."))+".txt";
		File tf=new File(threadFile);
		
		if(tf.exists())
		{
				new DataReading().readData(noOfThreads, threadPosition,threadFile); 
		}
		else
		{
				new DataCreating().createData(noOfThreads,sizeOfThread,threadFile);
				for(int i=0;i<noOfThreads;i++)
				{
					threadPosition[i]=i*sizeOfThread;
				}
					     
		}
		
	}
	
	
	public void createMainFile()throws Exception
	{	
		System.out.println("in createMainFile");
		String videoFile=toFieldText+".txt";
        
        File f=new File(videoFile);
        if(f.exists())
        {
        	
        		System.out.println("main file already exists");
        		System.out.println("main file is "+videoFile);
        		Scanner input=new Scanner(new File(videoFile));
        		String contentType=input.next();
        		System.out.println("contentType is "+contentType);
        		videoType=input.next();
        		System.out.println("videotype is "+videoType);
        		noOfThreads=input.nextInt();
        		threadPosition=new long[noOfThreads];
        		System.out.println("no Of Threads is "+noOfThreads);
        		sizeOfFile = input.nextLong();
        		//System.out.println("videotype is "+videoType);
  		      	sizeOfThread=input.nextLong();
  		        sizeOfFinaThread=sizeOfFile-noOfThreads*sizeOfThread;
        		input.close();
        		System.out.println("got main file values");
        	
        }
        else
       	{
        	
        			System.out.println("creating main file");
        			URL root=new URL(urlFieldText);
        			System.out.println(urlFieldText);
        			URLConnection uc = root.openConnection( );
        			String contentType = uc.getContentType( );
        			threadPosition=new long[noOfThreads];
        			sizeOfFile = uc.getContentLengthLong( );
      		        sizeOfThread=sizeOfFile/noOfThreads;
      		        sizeOfFinaThread=sizeOfFile-noOfThreads*sizeOfThread;
      		        System.out.println("size of file i  "+sizeOfFile);
/*        			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	            	Connection con = DriverManager.getConnection("jdbc:odbc:simple","system","gc");
	            	Statement stmnt = con.createStatement();
	            	String query = "select videoType from VideoLink where contentType="+"'"+contentType+"'";
	            	ResultSet rs = stmnt.executeQuery( query );
	            
	            	if( rs.next() )
	            	{
	            		videoType=rs.getString(1);
	            		System.out.println("videoType is "+videoType);
	            	
	            	}
	            	else
	            	{
	            		videoType=JOptionPane.showInputDialog("enter videotype for contenttype "+contentType);
	            		stmnt.executeUpdate("insert into VideoLink values('"+contentType+"','"+videoType+"')");
	            		System.out.println("Created table in given database...");
	            	}
	            
	            	
	            	stmnt.close();
	            	con.close();*/
				videoType="avi";
	            	System.out.println("vachanu");
	            	CreateFile.createFile(videoFile, contentType, videoType, noOfThreads, sizeOfFile,sizeOfThread);
	               	System.out.println("main file created");				        			        	
        		
       	}	
        
        
	   
        
	}
	
	public void runThreads()throws Exception
	{
		
		
		  System.out.println("in runThreads method");
		  //FileOutputStream fout = new FileOutputStream(toFieldText+"."+videoType,true);
		  System.out.println("video type is "+videoType);
		  System.out.println("created video file");
	      //FileChannel channel=fout.getChannel();
	      System.out.println("no of threads is "+noOfThreads);
	      PictureObject object=new PictureObject(noOfThreads);
	      threads=new Thread[noOfThreads];
	      //Picture picture=new Picture(object,lengthOfThreads);
	      System.out.println("url is "+urlFieldText);
	      for(int i=0;i<noOfThreads;i++)
	      {
	    	  threads[i]=new Thread(new FileCopierThread3(urlFieldText,threadPosition,toFieldText+"."+videoType,object,i,sizeOfThread,threadFile));
	    	  threads[i].start();
	      }
	      System.out.println("exiting runThreads method");
	    
		
		
	    
	
	}
	
}
