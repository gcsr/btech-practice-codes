import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class FileServer {
	 static int    serverPort   = 30000;
	 static ServerSocket serverSocket = null;
	 static boolean  isStopped    = false;
	

	 public static void main(String[] gcs){
        openServerSocket();
        System.out.println("server started");
	    while(! isStopped){
	    	Socket clientSocket = null;
	    	try {
	    		clientSocket = serverSocket.accept();
	    		new Thread(
	    	            new FileHandler(
	    	            clientSocket, "Multithreaded Server")
	    	    ).start();
	    	} catch (IOException e) {
	        
	    	}
	    }
	 }


	
    private static void openServerSocket() {
    	try {
	         serverSocket = new ServerSocket(serverPort);
	    } catch (IOException e) {
	            throw new RuntimeException("Cannot open port 30000", e);
	    }
	}	
}
