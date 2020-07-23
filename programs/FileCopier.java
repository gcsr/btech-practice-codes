import java.io.InputStream;
import java.io.BufferedInputStream;

import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URLConnection;
import java.io.FileOutputStream;

public class FileCopier {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    



	        try {

	          URL root = new URL("http://localhost:8080/p.flv");
	          //http://www.fsishare.com/mmsclips/fsi.1469.village-bhabi.3gp

	          saveBinaryFile(root);

	        }

	        catch (MalformedURLException ex) {

	          System.err.println( " is not URL I understand.");

	        }

	        catch (IOException ex) {

	          ex.printStackTrace();

	        }





	    } // end main





	    public static void saveBinaryFile(URL u) throws IOException {

	    
	    	
	      URLConnection uc = u.openConnection( );

	      String contentType = uc.getContentType( );
	      System.out.println(contentType);

	      int contentLength = uc.getContentLength( );

	     // if (contentType.startsWith("text/") || contentLength == -1 ) {
//
//	        throw new IOException("This is not a binary file.");
//
	//      }



	      InputStream raw = uc.getInputStream( );

	      InputStream in  = new BufferedInputStream(raw);
	      FileOutputStream fout = new FileOutputStream("d:/hi1.wmv");

	      byte[] data = new byte[contentLength];

	      int bytesRead = 0;

	      int offset = 0;
	      System.out.println("writing data");

	      while (offset < contentLength) {

	         bytesRead = in.read(data, offset, data.length-offset);

	         if (bytesRead == -1) break;

	         offset += bytesRead;

	      }

	      in.close( );

	      

	      if (offset != contentLength) {

	        throw new IOException("Only read " + offset 

	         + " bytes; Expected " + contentLength + " bytes");

	      }



//	      String filename = u.getFile( );

	      //filename = filename.substring("d:/hi.flv");

	      

	      fout.write(data);

	      fout.flush( );

	      fout.close( );
	      	System.out.println("over");
	    

	    } 

}
