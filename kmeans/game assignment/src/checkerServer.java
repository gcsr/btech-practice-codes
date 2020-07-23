
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class checkerServer {
	private static final long serialVersionUID = 1L;
    ServerSocket server;
    Socket connection;
    ObjectOutputStream output;
    ObjectInputStream input;
    Board checkerBoard;

    public checkerServer(){
    }

    public void runServer() {
        try {
            server = new ServerSocket( 12345, 100 );
            while ( true ) {
                try {
                    System.out.println("Waiting for connection from client\n");
                    connection = server.accept();
                    output = new ObjectOutputStream( connection.getOutputStream() );
                    output.flush();
                    input = new ObjectInputStream( connection.getInputStream() );

                    String message = null;
                    do {
                        try {
                            message = null;
                            message = ( String ) input.readObject();
                            parseMessage(message);
                            System.out.println("Message from Client:" + message);
                            //sendData("new");
                        }
                        catch ( ClassNotFoundException e ) {
                            System.out.println("\nUnknown object received: "+e);
                        }
                        catch ( IOException e) {
                            System.out.println("\nInvalid IO operation: "+e);
                        }
                    } while ( !message.equals("end") );
                }

                catch ( EOFException e ) {
                    System.err.println( "Server terminated connection "+e);
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

    private void sendData(String data) {
        try {
            output.writeObject(data);
            output.flush();
            System.out.println("Message to client: SERVER>>> "+data);
        }
        catch ( IOException e ) {
            System.out.println( "\nError writing object from Server: "+e );
        }
    }

	private void closeConnection() {
        try {
            output.close();
            input.close();
            connection.close();
        }
        catch( IOException ioException ) {
            ioException.printStackTrace();
        }
	}

    public static void main(String args[]) {
        checkerServer s = new checkerServer();
        s.runServer();
    }

    public void parseMessage(String message)
    {
    	System.out.println(message+" server");
    	String result[] = message.split("\\s");

    	if(result[0].equals("move"))
    	{
    		if(result.length==5){
    			int fromX = Integer.parseInt(result[1]);
    			int fromY = Integer.parseInt(result[2]);
    			int toX = Integer.parseInt(result[3]);
    			int toY = Integer.parseInt(result[4]);

    			if(checkerBoard.makeMove(fromX, fromY, toX, toY)){
    				String data = checkerBoard.generateMove();
    				sendData(data);
    			}
    		}

    	}else if(result[0].equals("delete")){

    	}else if(result[0].equals("continue")){

    	}else if(result[0].equals("new")){
    		checkerBoard = new Board();
            if(checkerBoard.startGame())
                sendData("new");
            else
                sendData("end");
    	}else if(result[0].equals("end")){
            
    	}
    }

}
	