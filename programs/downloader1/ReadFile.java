import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class ReadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input;
				try
		        {
		           input = new Scanner( new File( "clients.txt" ) );
		           
		           VideoFormat record=new VideoFormat();
		           while(input.hasNext())
		           {
		        	   System.out.println(input.next());
		        	   System.out.println(input.next());
		        	   System.out.println(input.nextInt());
		        	   System.out.println(input.nextLong());
		        	   System.out.println(input.nextLong());
		        	   
		        	   
		        	   
		       		   //record.setSizeOfThread(input.nextLong());
		           }
		           System.out.println("this is in formmater");
		           //System.out.println(record.getContentType()+" "+record.getVideoType()+" "+record.getNoOfThreads());
		           input.close();
		        } // end try
		        catch ( FileNotFoundException fileNotFoundException )
		        {
		           System.err.println( "Error opening file." );
		           System.exit( 1 );
		        } // end 
		// TODO Auto-generated method stub

	}

}
