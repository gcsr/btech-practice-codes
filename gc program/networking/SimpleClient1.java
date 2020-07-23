import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.InputStream;

//to be worked with simpleClient1;







public class SimpleClient1
{
	public static void main(String[] gcs)
	{
		try{
			
				Socket socket=new Socket("127.0.0.1",80);
				InputStream in;
				in=socket.getInputStream();
				System.out.println("connected");
				while(true)
				{
					System.out.println("inside");
				try{
					int c;
					while((c=in.read())!=-1)
					System.out.print((char)c);
					
					System.out.println();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				
				}
				socket.close();		
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}