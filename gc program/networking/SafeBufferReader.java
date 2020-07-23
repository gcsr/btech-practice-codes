



import java.io.*;



public class SafeBufferReader extends BufferedReader {



  public SafeBufferReader(Reader in) {

    this(in, 1024);

  }



  public SafeBufferReader(Reader in, int bufferSize) {

    super(in, bufferSize);

  }



  private boolean lookingForLineFeed = false;

  

  public String readLine( ) throws IOException {

    StringBuffer sb = new StringBuffer("");

    while (true) {

      int c = this.read( );

      if (c == -1) { // end of stream

        if (sb.equals("")) return null;

        return sb.toString( );

      }

      else if (c == '\n') {

        if (lookingForLineFeed) {

          lookingForLineFeed = false;

          continue;

        }

        else {

          return sb.toString( );

        }

      }

      else if (c == '\r') {

        lookingForLineFeed = true;

        return sb.toString( );

      }

      else {

        lookingForLineFeed = false;

        sb.append((char) c);

      }

    }

  }



}
