package multithreading;

import java.io.*;



public class ListCallbackUserInterface implements DigestListener {

  

  private File input;

  private byte[] digest;

  

  public ListCallbackUserInterface(File input) {
  	System.out.println("listcallbackuserinterface constructor");

    this.input = input;

  }

  

  public void calculateDigest( ) {
  	System.out.println("calculate digest");

    ListCallbackDigest cb = new ListCallbackDigest(input);

    cb.addDigestListener(this);

    Thread t = new Thread(cb);

    t.start( ); 

  }

  

  public void digestCalculated(byte[] digest) {  
  	System.out.println("digest calculated");

    this.digest = digest;

    System.out.println(this);

  }

  

  public String toString( ) {
  	System.out.println("to string");

    String result = input.getName( ) + ": ";

    if (digest != null) {

      for (int i = 0; i < digest.length; i++) {

        result += digest[i] + " ";

      }  

    }

    else {

      result += "digest not available";

    }

    return result;

  }

  

  public static void main(String[] args) {
	
	System.out.println("main");
  String[] gcs={"ReadMe.txt","Bank.java"
  };

    for (int i = 0; i < gcs.length; i++) {    

      // Calculate the digest

      File f = new File(gcs[i]);

      ListCallbackUserInterface d

       = new ListCallbackUserInterface(f);

      d.calculateDigest( );

    } 

    

  }



}
