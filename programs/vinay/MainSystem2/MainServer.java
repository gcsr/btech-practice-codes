import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {

	public MainServer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] gcs) {
		ObjectOutputStream output=null;
		ObjectInputStream input=null;
		
		ServerSocket server=null;
		Socket cs=null;
		try
		{
			server=new ServerSocket(5000);
			cs=server.accept();
			System.out.println("connection established");
			output=new ObjectOutputStream(cs.getOutputStream());
			input=new ObjectInputStream(cs.getInputStream());
			
		
		
			while(true)
			{
				//output.writeObject(gcs);
				System.out.println("reading message");
				String red=(String)input.readObject();
				System.out.println("red message as "+red);
				if(red.equals("bye"))
				{
					output.writeObject(new MyClass(10,null,null,null));
					break;
				}
				else if(red.equals("dir"))
				{
					System.out.println("red dir");
					String s="available files are ";
					for(String p:gcs)
						s+=p+" ";
					output.writeObject(new MyClass(0,null,null,s));
				}		
				else if(red.startsWith("get"))
				{
					String s=red.substring(4);
					
					
					int number=0;
					
					for(String p:gcs)
					{
						System.out.println("gcs "+p+" filename "+s);
						number++;
						if(p.equals(s))
						{
							try
							{
								FileInputStream fin=new FileInputStream(p);
								File file=new File(p);
							
								int contentLength=(int)file.length();
								
								
								int offset=0;
								byte[] data = new byte[contentLength];
							    int bytesRead = 0;
								while (offset < contentLength) {
										
							       	bytesRead = fin.read(data, offset, data.length-offset);
							       	if (bytesRead == -1) break;
								       	offset += bytesRead;
						    	}
								fin.close();
								output.writeObject(new MyClass(0,data,p,null));
							}	
							catch(Exception ex)
							{
								ex.printStackTrace();
							}
							
							break;
						}
					}
					if(number==gcs.length)
						output.writeObject(new MyClass(1,null,null,null));
				}
				else
					output.writeObject(new MyClass(2,null,null,null));
			}
			input.close();
			output.close();
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