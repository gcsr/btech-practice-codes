

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class checkerClient {
    
	private static final long serialVersionUID = 1L;
    Socket client;
    ObjectOutputStream output;
    ObjectInputStream input;
    Board checkerBoard;

    private void runClient() throws InterruptedException {
        try {
            System.out.println("Client: connecting to server\n");
            client = new Socket( InetAddress.getByName("127.0.0.1"), 12345 );
            while ( true ) {
                try {
                    output = new ObjectOutputStream( client.getOutputStream() );
                    output.flush();
                    input = new ObjectInputStream( client.getInputStream() );

                    String message = null;

                    startGame(); //statGame
                    System.out.println("After new in client");
                    do {
                        try {
                            System.out.println("Inside try After new in client");
                            message = ( String ) input.readObject();
                            System.out.println("Inside try Before parse in client");
                            parseMessage(message);
                            System.out.println("Message from Server: "+message);
                        }
                        catch ( ClassNotFoundException e ) {
                            System.out.println("\nUnknown object received: "+e);
                        }
                    } while ( !message.equals("end") );
                }
                catch ( EOFException e ) {
                    System.err.println( "Server terminated connection: ");
                }

                finally {
                    closeConnection();
                }
            }
        }
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        }
    }


    private void sendData(String message) {
        try {
            output.writeObject(message);
            output.flush();
            System.out.println(message);
        }
        catch(IOException ioException) {
            System.out.println( "\nError writing object from Client" );
        }
    }

    private void closeConnection() {
        System.out.println("\nTerminating connection\n");
        try {
            output.close();
            input.close();
            client.close();
        }
        catch( IOException ioException ) {
            ioException.printStackTrace();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        checkerClient c = new checkerClient();
        c.runClient();
    }


    private void startGame()
    {
    	sendData("new");
    }
    
    private void parseMessage(String message) throws InterruptedException
    {
    	System.out.println(message+" client");
    	String result[] = message.split("\\s");

    	if(result[0].equals("move"))
    	{
    		if(result.length==5){
    			int fromX = Integer.parseInt(result[1]);
    			int fromY = Integer.parseInt(result[2]);
    			int toX = Integer.parseInt(result[3]);
    			int toY = Integer.parseInt(result[4]);	

    			if(checkerBoard.makeMove(fromX, fromY, toX, toY)){
    				System.out.println(message+" client");
    				String data = checkerBoard.getMove();
    				sendData(data);
    			}else{
    				sendData("end");
    			}
    		}	
    	}else if(result[0].equals("delete")){

    	}else if(result[0].equals("continue")){

    	}else if(result[0].equals("new")){
            checkerBoard = new Board();
            checkerBoard.startGame();
            String data = checkerBoard.getMove();
            sendData(data);
    	}else if(result[0].equals("end")){
            
    	}
    }

}
