import java.io.*;

import java.net.*;




public class server 
{
	public static void main(String[] gcs)throws IOException
	{
		Socket client;
		ServerSocket server=new ServerSocket(8189);
		
		System.out.println(socket);
		connection=server.accept();
		ObjectOutputStream output=new ObjectOutputStream(connection.getOutputStream());
		ObjectInputStream input=new ObjectInputStream(connection.getInputStream());
		server.close();
		
	}
}