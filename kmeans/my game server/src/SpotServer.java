import java.awt.Graphics.*;
import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class SpotServer extends JFrame {
  
    final int MAXSPOTS = 20;
    int xspots[] = new int[MAXSPOTS];  
    int yspots[] = new int[MAXSPOTS]; 
    Color cspots[] = new Color[MAXSPOTS];
    int currspots = 0;
    Color theColor;
    TextField tf;

    ServerSocket server;
    Socket connection;
    ObjectOutputStream output;
    ObjectInputStream input;

    SpotServer() {
        super("Server");
    
        // set up frame
        theColor = new Color((float)0.0, (float)0.0, (float)1.0);
        tf = new TextField("   ", 100); // create a blank text field
        tf.setEditable( false ); 
        getContentPane().add(tf, BorderLayout.SOUTH );
        setSize(500,500);
        setVisible(true);
                
        // attach mouselistener to frame
        SpotMouseListener sml = new SpotMouseListener();
        addMouseListener(sml);

        // attach window listener to window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {  
                System.exit(0); 
            }    
        });
    }

    // paint spots on frame
    public void paint (Graphics g) {
        super.paint(g);
        for(int i = 0; i < currspots; i++) {
            g.setColor(cspots[i]);
            g.fillOval(xspots[i] - 10,yspots[i] - 10, 20, 20);
        }
    }

    
    public class SpotMouseListener implements MouseListener {
        // create spot and send its coordinates and color to client
        public void mouseClicked(MouseEvent e) { 
            if(currspots < MAXSPOTS) {
                xspots[currspots] = e.getX();
                yspots[currspots] = e.getY();
                sendData(); 
                cspots[currspots] = theColor;
                currspots++;
                repaint();
            } else {
                System.out.println("Too may Spots");
            }
        }

        public void mouseEntered(MouseEvent e) {  }
        public void mouseExited(MouseEvent e) {  }
        public void mousePressed(MouseEvent e) {  }
        public void mouseReleased(MouseEvent e) { }
    }

    // set up and run server 
    public void runServer() {
        
        // set up server to receive and process connections from client
        try {
            // Step 1: Create a ServerSocket.
            server = new ServerSocket( 12345, 100 );
            while ( true ) {
                try {
                    // Step 2: Wait for a connection.
                    System.out.println("Waiting for connection from client\n");
                    connection = server.accept(); 
                    System.out.println("Obtained connection from client\n");
                    // Step 3: Get input & output streams.
                    // set up output stream for objects
                    output = new ObjectOutputStream( connection.getOutputStream() );
                    output.flush(); // flush output buffer to send header information
                    // set up input stream for objects
                    input = new ObjectInputStream( connection.getInputStream() );

                    // Step 4: Process connection.
                    String message = null;
                    // process messages sent from client
                    do { 
                        // read message and display it
                        try {
                            message = ( String ) input.readObject();
                            System.out.println("Message from Client:" + message);
                            tf.setText("Message from Client:" + message);
                        }
                        // catch problems reading from client
                        catch ( ClassNotFoundException classNotFoundException ) {
                            System.out.println("\nUnknown object received\n");
                        }
                        catch ( IOException invalidIO) {
                            System.out.println("\nInvalid IO operation");
                        }
                    } while ( !message.equals( "CLIENT>>> TERMINATE" ) );
                }

                // process EOFException when client closes connection 
                catch ( EOFException eofException ) {
                    System.err.println( "Server terminated connection" );
                }
                finally {
                    closeConnection();   // Step 5: Close connection.
                }
            } // end while
        } // end try

        // process problems with I/O
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        }   
    } // end method runServer
   
    // send message to client
    private void sendData( ) {
        // convert color to string
        String dotColor;
        if(theColor.equals(Color.RED))  dotColor = new String("RED"); 
        else dotColor = new String("BLUE"); 
        // send text object to client
        try {
            output.writeObject( "SERVER>>> " + "x = " + xspots[currspots] + "  y = " + yspots[currspots] + "  Color = " + dotColor);
            output.flush();
            System.out.println("Message to client: SERVER>>> " + "x = " + xspots[currspots] + "  y = " + yspots[currspots] + "  Color = " + dotColor);
            tf.setText("Message to client: SERVER>>> " + "x = " + xspots[currspots] + "  y = " + yspots[currspots] + "  Color = " + dotColor);
        }
        // process problems sending object
        catch ( IOException ioException ) {
            System.out.println( "\nError writing object from Server" );

        }
    }

    // close streams and socket
    private void closeConnection() {
        System.out.println("\nTerminating connection\n");      
        try {
            output.close();
            input.close();
            connection.close();
        }
        catch( IOException ioException ) {
            ioException.printStackTrace();
        }
    }
  
    // create and run server
    public static void main(String args[]) {
        SpotServer s = new SpotServer();
        s.runServer();
    }  
}

