import java.io.*;

import java.util.*;



public class MainThread {

  
  
  public final static int THREAD_COUNT = 4;

  private static int filesToBeSearched = -1;



  public static void main(String[] args) {

	String[] gcs={"C:/gc/multithreading"
	  };


    Vector pool = new Vector( );

    SearcherThread[] threads = new SearcherThread[THREAD_COUNT];

    

    for (int i = 0; i < threads.length; i++) {

      threads[i] = new SearcherThread(pool,0); 

      threads[i].start( );

    }



    int totalFiles = 0;

    for (int i = 0; i < gcs.length; i++) {

      

      File f = new File(gcs[i]);

      if (f.exists( )) {

        if (f.isDirectory( )) {

          File[] files = f.listFiles( );

          for (int j = 0; j < files.length; j++) {

            if (!files[j].isDirectory( )) { // don't recurse directories

              totalFiles++;

              synchronized (pool) {

                pool.add(0, files[j]);

                pool.notifyAll( );

              }

            }

          }

        } 

        else {

          totalFiles++;

          synchronized (pool) {

            pool.add(0, f);

            pool.notifyAll( );

          }

        }

        

      } // end if

      

    } // end for

    

    filesToBeSearched = totalFiles;

    

    // make sure that any waiting thread knows that no 

    // more files will be added to the pool

    for (int i = 0; i < threads.length; i++) {

      threads[i].interrupt( );

    }



  }



  public static int getNumberOfFilesToBeSearched( ) {

    return filesToBeSearched;

  }



}
