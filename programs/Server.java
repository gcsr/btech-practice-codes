import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;

public class Server {
	public static void main(String[] gcs)
	{
		ObjectOutputStream output=null;
		ObjectInputStream input=null;
		byte[] data=null;
		
		String s="";
		ServerSocket server=null;
		try
		{
			server=new ServerSocket(3444);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		Socket cs=null;
		try
		{
			FileInputStream fin=new FileInputStream("D:/gc personal/books/3.pdf");
			File file=new File("D:/gc personal/books/3.pdf");
			
			int contentLength=(int)file.length();
			
			
			int offset=0;
			data = new byte[contentLength];
		    int bytesRead = 0;
			while (offset < contentLength) {
				
			       bytesRead = fin.read(data, offset, data.length-offset);
			       if (bytesRead == -1) break;
			       offset += bytesRead;
			    }
			fin.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		try
		{
			cs=server.accept();
			System.out.println("connection established");
			output=new ObjectOutputStream(cs.getOutputStream());
			input=new ObjectInputStream(cs.getInputStream());
			
			
			output.writeObject(data);
			cs.close();
			server.close();
		}
		catch(Exception ex)
		{
			System.out.println("connection failed");
			ex.printStackTrace();
		}
		
	}

}