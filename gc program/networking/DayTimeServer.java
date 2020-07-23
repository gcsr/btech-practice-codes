
import java.net.ServerSocket;

import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.io.OutputStreamWriter;

public class DayTimeServer
{
	public static void main(String[] gcs)
	{
		Date date=new Date();
		
		try{
			ServerSocket server=new ServerSocket(13);
			Socket socket=server.accept();
			OutputStreamWriter writer=new OutputStreamWriter(socket.getOutputStream());
			
			writer.write((new Date()).toString());
			writer.close();
			socket.close();
			server.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
	}
}