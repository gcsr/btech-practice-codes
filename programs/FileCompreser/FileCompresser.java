import java.io.*;

public class FileCompresser {
  public static void main(String[] args) {
    
    try {
      copy("C:/downloads/ss.ob!", "D:/output.ob!");
    }
    catch (IOException ex) {
      System.err.println(ex);
    }
  }
  public static void copy(String inFile, String outFile)
   throws IOException {

    FileInputStream  fin = null;
    FileOutputStream fout = null;
    try {
      fin  = new FileInputStream(inFile);
      fout = new FileOutputStream(outFile);
      StreamCopier.copy(fin, fout);
    }
    finally {
      try {
        if (fin != null) fin.close( );
      }
      catch (IOException ex) {
      }
      try {
        if (fout != null) fout.close( );
       }
      catch (IOException ex) { }
    }
  }
}


