import java.io.*;

import java.util.*;

import java.util.zip.*;



public class GZipThreadClass extends Thread {

	private int i=0;

  private List pool;

  private static int filesCompressed = 0;



  public GZipThreadClass(List pool, int h) {
  	i=h;

    this.pool = pool;

  }

  

  private static synchronized void incrementFilesCompressed( ) {

    filesCompressed++;

  }



  public void run( ) {

  
  try{
	  Thread.sleep(1000);
  }catch(InterruptedException ire){
  }
    while (filesCompressed != GZipAllFiles.

                              getNumberOfFilesToBeCompressed( )) {

          	System.out.println("first while  ");
      File input = null;
      synchronized (pool) {         

        while (pool.isEmpty( )) {
			System.out.println(filesCompressed+" "+GZipAllFiles.

                                 getNumberOfFilesToBeCompressed( ));

          if (filesCompressed == GZipAllFiles.

                                 getNumberOfFilesToBeCompressed( )) {

            System.out.println("Thread ending");

            return;

          }

          try {

            pool.wait( );

          }

          catch (InterruptedException ex) {

          }

        }



        input = (File) pool.remove(pool.size( )-1); 

        incrementFilesCompressed( );



      }

    

      // don't compress an already compressed file
	  

      if (!(input.getName( ).endsWith(".java") || input.getName( ).endsWith(".html") || (input.getName( ).endsWith(".jsp")))) {       

        try {

			if(!input.isDirectory())
			input.delete();
			  /*InputStream in = new FileInputStream(input);

          in = new BufferedInputStream(in);

          

          File output = new File(input.getParent( ), input.getName( ) + ".gz");

          if (!output.exists( )) { // Don't overwrite an existing file

            OutputStream out = new FileOutputStream(output);

            out = new GZIPOutputStream(out);

            out = new BufferedOutputStream(out);

            int b;

            while ((b = in.read( )) != -1) out.write(b);

            out.flush( );

            out.close( );

            in.close( );

          }*/

        }

        catch (Exception ex) {

          System.out.println(ex);

        }

        

      } // end if 

  

    } // end while

    

          	System.out.println("while not entered  ");

  } // end run



} 
