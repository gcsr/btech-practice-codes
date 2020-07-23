package yaswanth;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFiles {
	public static void main(String[] gcs){
byte[] buffer = new byte[1024];
    	
    	try{
    		
    		FileOutputStream fos = new FileOutputStream("D:\\desktops\\desktop 3\\yashwanth app\\year sorted\\"+"fullData.zip");
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		ZipEntry ze= new ZipEntry("fullData.txt");
    		zos.putNextEntry(ze);
    		FileInputStream in = new FileInputStream("D:\\desktops\\desktop 3\\yashwanth app\\year\\"+"fullData.txt");
   	   
    		int len;
    		while ((len = in.read(buffer)) > 0) {
    			zos.write(buffer, 0, len);
    		}

    		in.close();
    		zos.closeEntry();
           
    		//remember close it
    		zos.close();
          
    		System.out.println("Done");

    	}catch(IOException ex){
    	   ex.printStackTrace();
    	}
    }
	
}
