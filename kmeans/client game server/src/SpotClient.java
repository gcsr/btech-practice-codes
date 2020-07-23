import java.awt.Graphics.*;
import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class SpotClient extends JFrame {
  
    final int MAXSPOTS = 20;
    int xspots[] = new int[MAXSPOTS];  
    int yspots[] = new int[MAXSPOTS]; 
    Color cspots[] = new Color[MAXSPOTS];
    int currspots = 0;
    Color theColor;
    TextField tf;
    Socket client;
    ObjectOutputStream output;
    ObjectInputStream input;

    SpotClient() {
        super("Client");

        // set up medubar
        ColorClientMenu colorMenu = new ColorClientMenu(this);
        JMenuBar mb = new JMenuBar();
        mb.add(colorMenu); 
        setJMenuBar(mb);
        
        // set up frame
        theColor = new Color((float)0.0, (float)0.0, (float)1.0);
        tf = new TextField("   ", 100); // create a blank text field
        tf.setEditable( false ); 
        getContentPane().add(tf, BorderLayout.SOUTH );
        setSize(500,500);
        setVisible(true);
        
        // set up mouse listener for frame
        SpotMouseListener sml = new SpotMouseListener();
        addMouseListener(sml);
        // set up Window listener for Window
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
        // create spot and send its coordinates and color to server
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

    // connect to server and process messages from server
    private void runClient() {
        // connect to server, get streams, process connection
        try {
            // Step 1: create Socket to make connection to server
            client = new Socket( InetAddress.getByName("127.0.0.1"), 12345 );
            while ( true ) {
                try {
                    // Step 2: Get input & output streams.
                    // set up output stream for objects
                    output = new ObjectOutputStream( client.getOutputStream() );
                    output.flush(); // flush output buffer to send header information
                    // set up input stream for objects
                    input = new ObjectInputStream( client.getInputStream() );

                    // Step 3: Process connection.
                    String message = null;
                    do { // process messages sent from server
                        // read message and display it
                        try {
                            message = ( String ) input.readObject();
                            System.out.println("Message from Server:" + message);
                            tf.setText("Message from Server:" + message);
                        }
                        // catch problems reading from server
                        catch ( ClassNotFoundException classNotFoundException ) {
                            System.out.println("\nUnknown object received\n");
                        }
                    } while ( !message.equals( "SERVER>>> TERMINATE" ) );
                }
                // process EOFException when server closes connection 
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
        String dotColor;
        if(theColor.equals(Color.RED)) dotColor = new String("RED"); 
        else dotColor = new String("BLUE"); 
        // send object to client
        try {
            output.writeObject( "CLIENT>>> " + "x = " + xspots[currspots] + "  y = " + yspots[currspots] + "  Color = " + dotColor);
            output.flush();
            System.out.println("Message to server: CLIENT>>> " + "x = " + xspots[currspots] + "  y = " + yspots[currspots] + "  Color = " + dotColor);
            tf.setText("Message to server: SERVER>>> " + "x = " + xspots[currspots] + "  y = " + yspots[currspots] + "  Color = " + dotColor);
        }
        // process problems sending object
        catch ( IOException ioException ) {
            System.out.println( "\nError writing object from Client" );
        }
    }

    // close streams and socket
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
  
    // create and run client 
    public static void main(String args[]) {
        SpotClient c = new SpotClient();
        c.runClient();
    }  
}
