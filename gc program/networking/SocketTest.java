/**
 * @(#)SocketTest.java
 *
 *
 * @author 
 * @version 1.00 2010/7/30
 */
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

public class SocketTest {
        
    /**
     * Creates a new instance of <code>SocketTest</code>.
     */
    public SocketTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	try
    	{
    		Socket socket=new Socket("time-A.timefreq.bldrdoc.gov",13);
    		try
    		{
    			InputStream inStream=socket.getInputStream();
    			Scanner in=new Scanner(System.in);
    			while(in.hasNextLine())
    			{
    				String line=in.nextLine();
    				System.out.println(line);
    			}
    		}
    		finally{
    			socket.close();
    		}
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
}
