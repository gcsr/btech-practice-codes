import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
//to be worked with simpleClient1;

class InputThread extends Thread
{
	InputStream in;
	InputThread(InputStream in)
	{
		this.in=in;
		
	}
	public void run()
	{
		try{
		int c;
		while((c=in.read())!=-1)
			System.out.println((char)c);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


class OutputThread extends Thread
{
	OutputStream out;
	OutputStreamWriter writer;
	String line;
	OutputThread(OutputStream out)
	{
		this.out=out;
		writer=new OutputStreamWriter(this.out);
		
	}
	public void run()
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			while(true){
			line=sc.nextLine();
			System.out.println("writing");
			writer.write(line);
			writer.flush();
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		try{
			writer.close();
		}
		catch(Exception e)
		{
		}
			
			
	}	
}




public class ClientTester
{
	public static void main(String[] gcs)
	{
		try{
			ServerSocket server=new ServerSocket(80);
			
			while(true)
			{
				Socket socket=server.accept();
				
				//Thread input=new InputThread(socket.getInputStream());
				//input.start();
				
				Thread output=new OutputThread(socket.getOutputStream());
				output.start();
				
				try{
					//input.join();
				    output.join();
				}
				catch(InterruptedException e)
				{
					
				}
				finally{
					socket.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}