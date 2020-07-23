import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;

/**

 */
public class FileHandler implements Runnable{

	ObjectOutputStream output=null;
	ObjectInputStream input=null;
	
    protected Socket clientSocket = null;
    protected String serverText   = null;

    public FileHandler(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
        	
        	output=new ObjectOutputStream(clientSocket.getOutputStream());
			input=new ObjectInputStream(clientSocket.getInputStream());
			
			while(true)
			{
				MyClass obj=(MyClass)input.readObject();
				//System.out.println("red message as "+obj.no);
				if(obj.no==10)
				{
					break;
					/*this.show(false);
					input.close();
					output.close();
					clientSocket.close();*/
				}
				
				else if(obj.no==0){		
					obj=(MyClass)input.readObject();
					//System.out.println("red obj.no "+obj.no);
					System.out.println("read  data");
					if((obj.data!=null)){						
						byte[] hashBytes=getHash(obj.data);
						System.out.println("sending hash");
						output.writeObject(new MyClass(2,hashBytes,null,"hash value"));
					}
					
				}
			
				//output.writeObject(gcs);
			}
			System.out.println("closing connection");
			input.close();
			output.close();
			clientSocket.close();
			
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
    
    public byte[] getHash(byte[] password) {
		byte[] salt=new byte[]{3,3,3,3,3,3,3,3};
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);
			byte[] bt=digest.digest(password);
			return bt;
		}catch(Exception ex){
			return null;
		}
	 }
}