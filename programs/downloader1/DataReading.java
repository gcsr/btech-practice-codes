import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.File;

public class DataReading {

	/**
	 * @param args
	 */
	
	public static void main(String[] gcs)
	{
		long[] ss=new long[3];
		readData(3,ss,"d:/desi.dat");
	}
	public static void readData(int noOfThreads,long[] threadPosition,String threadFile)
	{
		try
		{
			
		FileInputStream fis = new FileInputStream(threadFile);
		DataInputStream dis = new DataInputStream(fis);
		for(int i=0;i<noOfThreads;i++)
		{
			System.out.println("reading from thread file");
			threadPosition[i]=dis.readLong();
		
		}
			
		dis.close();
		fis.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
		

	}

}
