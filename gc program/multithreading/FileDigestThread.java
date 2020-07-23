import java.io.*;

import java.security.*;



class DigestThread extends Thread {



  private File input;



  public DigestThread(File input) {

   this.input = input;

  }



  public void run( ) {

    try {

      FileInputStream in = new FileInputStream(input);

      MessageDigest sha = MessageDigest.getInstance("SHA");

      DigestInputStream din = new DigestInputStream(in, sha);

      int b;

      while ((b = din.read( )) != -1);
      	

      din.close( );

      byte[] digest = sha.digest( );
      System.out.println(digest.length) ;

      StringBuffer result = new StringBuffer(input.toString( ));

      result.append(": ");

      for (int i = 0; i < digest.length; i++) {

        result.append(digest[i] + " ");

      }

      System.out.println(result);

    }

    catch (IOException ex) {

      System.err.println(ex);

    }

    catch (NoSuchAlgorithmException ex) {

      System.err.println(ex);

    }

    

  }
}


public class FileDigestThread
{
  public static void main(String[] args) {

  
		  
		String[] gcs={"bank.java","AnimationFrame.java"};


    for (int i = 0; i < gcs.length; i++) {

      
      File f = new File(gcs[i]);

	System.out.println("thread");


      Thread t = new DigestThread(f);

      t.start( );

    }

  

  }



}
