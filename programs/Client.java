import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;



public class Client {
	public static void main(String[] gcs)
	{
		ObjectOutputStream output=null;
		ObjectInputStream input=null;
		Socket client;
		
		try
		{
			client=new Socket("127.0.0.1",3444);
			output=new ObjectOutputStream(client.getOutputStream());
			input=new ObjectInputStream(client.getInputStream());
			
			
			byte[] redData=(byte[])input.readObject();
			
			
			FileOutputStream fout=new FileOutputStream("3.pdf");
			fout.write(redData);
			fout.close();
			
			
			input.close();
			output.close();
			client.close();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}