import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class FileClient {
	public static void main(String[] gcs){
		ObjectInputStream input=null;
		ObjectOutputStream output=null;
		Socket client;
		boolean exitSatisfied=false;
		try
		{
			client=new Socket("127.0.0.1",30000);
			output=new ObjectOutputStream(client.getOutputStream());
			input=new ObjectInputStream(client.getInputStream());
			
			Scanner scanner=new Scanner(System.in);
			int count=0;
			while(!exitSatisfied){
				System.out.println(" Choose your option");
				System.out.println("-------------------------------------------------------------------");
				System.out.println("1 : send file");
				System.out.println("2 : shutdown");
				System.out.println("-------------------------------------------------------------------");
				
				count=scanner.nextInt();
				
				switch(count){
					case 1:
						output.writeObject(new MyClass(0,null,null,null));
						System.out.println("choose file");

						JFileChooser fileChooser=new JFileChooser();
				  		
				  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				  		int result=fileChooser.showOpenDialog(null);
				  		if(result==JFileChooser.CANCEL_OPTION)
				  					return;
				  				
				  				
				  		String path=(fileChooser.getSelectedFile().getAbsolutePath());
				  		try
						{
							FileInputStream fin=new FileInputStream(path);
							File file=new File(path);
						
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
							output.writeObject(new MyClass(0,data,null,null));
							MyClass hashResult=(MyClass)input.readObject();
							String hash=new String(hashResult.data,"UTF-8");
							
							System.out.println("hash is "+hash);
						
						}catch(Exception ex){
							
						}
				  		break;
					case 2:
						output.writeObject(new MyClass(10,null,null,null));
						exitSatisfied=true;
						break;						
						
					default:
						
						break;
						
				}
			}
			input.close();
			output.close();
			client.close();			
				
		}	
		catch(Exception ex){
			ex.printStackTrace();
		}
			
			
	}
	
}