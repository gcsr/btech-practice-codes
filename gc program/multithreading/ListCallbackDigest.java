import java.io.*;

import java.security.*;

import java.util.*;



public class ListCallbackDigest implements Runnable {


	
  private File input;

  List listenerList = new Vector( );



  public ListCallbackDigest(File input) {
  	System.out.println("listcallbackdigest constructor");

   this.input = input;

  }

 

  public synchronized void addDigestListener(DigestListener l) {
  	System.out.println("add digestlistener");

    listenerList.add(l);

  }

  

  public synchronized void removeDigestListener(DigestListener l) {
  	System.out.println("remove digestlistener");

    listenerList.remove(l);

  } 

    

  private synchronized  void sendDigest(byte[] digest) {
  	System.out.println("send digest");



    ListIterator iterator = listenerList.listIterator( );

    while (iterator.hasNext( )) {

      DigestListener dl = (DigestListener) iterator.next( );

      dl.digestCalculated(digest);

    }



  }  



  public void run( ) {
  	System.out.println("run");



    try {

      FileInputStream in = new FileInputStream(input);

      MessageDigest sha = MessageDigest.getInstance("SHA");

      DigestInputStream din = new DigestInputStream(in, sha);

      int b;

      while ((b = din.read( )) != -1) ;

      din.close( );

      byte[] digest = sha.digest( );

      this.sendDigest(digest);

    }

    catch (IOException ex) {

      System.err.println(ex);

    }

    catch (NoSuchAlgorithmException ex) {

      System.err.println(ex);

    }

    

  }



}
