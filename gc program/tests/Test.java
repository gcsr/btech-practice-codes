import java.io.*;
import java.lang.Process;
public class Test {
  public static void main(String args[]) {
    new Test("C:/Program Files/VideoLAN/VLC/vlc.exe");
  }

  public Test(String path) {
    File f = new File(path);
    if (!(f.exists()&&f.isFile())) {
      System.out.println("Incorrect path or not a file");
      return;
    }
    Runtime rt = Runtime.getRuntime();
    try {
      Process proc = rt.exec(path);
      StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), false);
      StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), false);
      errorGobbler.start();
      outputGobbler.start();
      System.out.println("\n"+proc.waitFor());
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
  }
  class StreamGobbler extends Thread {
    InputStream is;
    boolean discard;
    StreamGobbler(InputStream is, boolean discard) {
      this.is = is;
      this.discard = discard;
    }

    public void run() {
      try {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line=null;
        while ( (line = br.readLine()) != null)
          if(!discard)
            System.out.println(line);    
        }
      catch (IOException ioe) {
        ioe.printStackTrace();  
      }
    }
  }
}
