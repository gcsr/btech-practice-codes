import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public class FTP {
	
	public static void main(String[] args) throws SocketException, IOException {
		
		
		String server = "172.25.1.138";
        int port = 3389;
        String user = "Sreekanth T";
        String pass = "123@google4";
 
        FTPClient ftpClient = new FTPClient();
        
        ftpClient.connect(server, port);
       
        ftpClient.login(user, pass);
        System.out.println("ftp connection established");
        
        FTPFile[] files = ftpClient.listFiles("172.25.1.138 $Crawl");
        for (FTPFile file :files) {
            String details = file.getName();
            System.out.println(details);
            if (file.isDirectory()) {
        
        
        
        System.out.println(files.toString());
        
	}
            ftpClient.logout();
            ftpClient.disconnect();
	
        }
}}