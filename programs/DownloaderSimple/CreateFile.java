import java.io.*;
import java.util.*;

public class CreateFile {

	/**
	 * @param args
	 */
	
	
	public static void createFile(String fileName,String contentType,String videoType,int noOfThreads,
			long sizeOfFile,long SizeOfThread)
			{
				try
				{
					Formatter output=new Formatter(fileName);
					//VideoFormat record=new VideoFormat(contentType,videoType,noOfThreads,
						//	sizeOfFile,SizeOfThread);
					output.format("%s %s %d %d %d ",contentType,videoType,noOfThreads,sizeOfFile,SizeOfThread);
					output.close();
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}

			}
	public static void main(String[] args) {
		/*try
		{
			Formatter output=new Formatter("clients.txt");
			output.format("%s %s %d %d %d ","video/jkl","jkl",5,
					500,100);
			//output.format(contentType,videoType,noOfThreads,sizeOfFile,SizeOfThread);
			output.close();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}*/

		
	}

}
