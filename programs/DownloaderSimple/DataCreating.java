import java.io.*;
public class DataCreating {

	/**
	 * @param args
	 */
	public static void main(String[] gcs)
	{
		//createData(3,400,"d:/desi.dat");
	}
	public void createData(int noOfThreads,long threadSize,String threadFile) {
		try
		{
			FileOutputStream fos = new FileOutputStream(threadFile);
			DataOutputStream dos = new DataOutputStream(fos);  
			for(int i=0;i<noOfThreads;i++)
			{
				System.out.println("writing to thread file");
				dos.writeLong((long)i*threadSize);
			}
			dos.close();
	        fos.close();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
