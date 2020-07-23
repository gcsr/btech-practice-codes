import java.io.*;

import java.util.*;



public class GZipAllFiles {

  
  
  public final static int THREAD_COUNT = 10;

  private static int filesToBeCompressed = -1;



  public static void main(String[] args) {

	String[] gcs={"E:/btech_codes/btech-practice-codes"
	  };


    Vector pool = new Vector( );

    GZipThreadClass[] threads = new GZipThreadClass[THREAD_COUNT];

    

    for (int i = 0; i < threads.length; i++) {

      threads[i] = new GZipThreadClass(pool,0); 

      threads[i].start( );

    }



    int totalFiles = 0;

    for (int i = 0; i < gcs.length; i++) {
      

      File f = new File(gcs[i]);

      if (f.exists( )) {

        addToPool(f,pool);

        

      } // end if

	
      

    } // end for

    

    //filesToBeCompressed = totalFiles;

    

    // make sure that any waiting thread knows that no 

    // more files will be added to the pool

	try{
	  Thread.sleep(100000);
  }catch(InterruptedException ire){
  }
    for (int i = 0; i < threads.length; i++) {

      threads[i].interrupt( );

    }
	



  }



  public static int getNumberOfFilesToBeCompressed( ) {

    return filesToBeCompressed;

  }
  
  public static void addToPool(File f,Vector pool){
	  if (f.isDirectory( )) {

//System.out.println("totalFiles  "+filesToBeCompressed);
          File[] files = f.listFiles( );

          for (int j = 0; j < files.length; j++) {

            if (!files[j].isDirectory( )) { // don't recurse directories

              filesToBeCompressed++;

              synchronized (pool) {

                pool.add(0, files[j]);
				//System.out.println("adding to pool totalFiles is directory "+filesToBeCompressed);

                pool.notifyAll( );

              }

            }else{
				addToPool(files[j],pool);
			}

          }

        } 

        else {

          filesToBeCompressed++;

          synchronized (pool) {

            pool.add(0, f);

            pool.notifyAll( );

          }

        }
	 
  }



}
