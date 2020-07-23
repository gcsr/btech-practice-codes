


import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;



public abstract class NetworkBaseClass {
    public static final String DEFAULT_SERVER_NAME = "localhost";
    public static final int DEFAULT_SERVER_PORT = 2100;
    public static final int DEFAULT_SERVER_BACKLOG = 10;

    public void closeSocket(Socket socket) {
        if (null != socket) {
            try {
                socket.close();
            } catch (IOException exception) {
            }
        }
    }

    public void closeStream(InputStream stream) {
        if (null != stream) {
            try {
                stream.close();
            } catch (IOException exception) {
            }
        }
    }

    public void closeStream(OutputStream stream) {
        if (null != stream) {
            try {
                stream.flush();
                stream.close();
            } catch (IOException exception) {
            }
        }
    }
}
