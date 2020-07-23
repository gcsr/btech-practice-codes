import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable{
	String[] gcs;

	public Server(String[] gcs) {
		this.gcs=gcs;
		
	}
	public void run() {
		ObjectOutputStream output=null;
		ObjectInputStream input=null;
		
		ServerSocket server=null;
		Socket cs=null;
		try
		{
			server=new ServerSocket(30000);
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
					System.out.println("red message as and in bye block"+red);
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
				
				else if(red.startsWith("search"))
					{
						System.out.println("searching");
						int noBytes;
						String p=red.substring(7);
						System.out.println("filename is "+p);
						int number=0;
						for(String s:gcs)
						{  
							System.out.println("number is "+number);
							
							if(p.equals(s))				
								break;
							number++;
						}
						if(number>=gcs.length)
						{
							String s="file does not exist in server";
							output.writeObject(new MyClass(0,null,null,s));
						}
						else
						{
							String s="file exists in server";
							output.writeObject(new MyClass(0,null,null,s));
						}
							
					}
				else if(red.contains("block"))
				{
					System.out.println("command contains block");
					
					if(red.startsWith("blocks"))
					{
						System.out.println("command startsWith block");
						String s=red.substring(7);
						
						
						int number=0;
						
						for(String p:gcs)
						{
							System.out.println("gcs "+p+" filename "+s);
							number++;
							if(p.equals(s))
							{
								try
								{
									File file=new File(p);
									int contentLength=(int)file.length();
									
									int sp=(contentLength-1);
									
									int pp=sp/1024;
									
									int blocks=pp+1;
									
									//int blocks=((contentLength-1)/1024)+1;
									
									System.out.println(blocks);
									String noBlocks="0"+blocks;
									output.writeObject(new MyClass(0,null,p,noBlocks));
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
					
				}
				else if(red.startsWith("get"))
				{
					System.out.println("command startswith get");
					int spaceIndex1=red.indexOf(" ");
					int spaceIndex2=red.indexOf(" ",spaceIndex1+1);
					
					int spaceIndex3=red.indexOf(" ",spaceIndex2+1);
					
					if(red.lastIndexOf(" ")==spaceIndex1)
					{
						System.out.println("get filename");
					
						String s=red.substring(4);
			
						
						int number=0;
						
						for(String p:gcs)
						{
							System.out.println("gcs "+p+" filename "+s);
							
							if(p.equals(s))
							{
								try
								{
									System.out.println("inide if");
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
									break;
								}	
								catch(Exception ex)
								{
									ex.printStackTrace();
								}
								
								
							}
							else
							number++;
						}
						if(number>=gcs.length)
							output.writeObject(new MyClass(1,null,null,null));
					}
					else if((spaceIndex2>0)&&(spaceIndex3<=0))
					{
						System.out.println("get filename blockNO");
						int noBytes;
						
						String subString=red.substring(spaceIndex2+1);
							
						String p=red.substring(4,spaceIndex2);
						System.out.println("filename is "+p);
						int number=0;
						for(String s:gcs)
						{  
							System.out.println("number is "+number);
							
							if(p.equals(s))				
								break;
							number++;
						}
						if(number>=gcs.length)
							output.writeObject(new MyClass(1,null,null,null));
						
						FileInputStream fin=new FileInputStream(p);
						File file=new File(p);
						try
						{
							int blockNo=Integer.parseInt(subString);
							System.out.println("block no is "+blockNo);
							int contentLength=(int)file.length();
							
							if(blockNo>((contentLength-1)/1024+1))
									{
										throw new Exception(" blockNO greater than file length");
									}
							else
							{

								int offset=0;
								
								
								int remaining=0;
								int bytesRead = 0;
								
								if(blockNo<((contentLength-1)/1024+1))
									noBytes=1024;
								else
									noBytes=contentLength-(blockNo-1)*1024;
								
								byte[] data = new byte[1024];
								
								
								while (offset < contentLength) {
									
									
										
									bytesRead = fin.read(data, offset, noBytes-remaining);
									
									System.out.println("bytes read is "+bytesRead);
									if (bytesRead == -1) break;
									
											
									offset += bytesRead;
									
									remaining+=bytesRead;
									
									if(remaining>=noBytes)
										break;
								}
								fin.close();
								output.writeObject(new MyClass(0,data,p,null));
							}
							
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
							output.writeObject(new MyClass(3,null,null,null));
						}
					}
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