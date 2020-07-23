package yaswanth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressDirectory {
	public static void main(String[] gcs){
		FileOutputStream fos=null;
		try {	
			fos = new FileOutputStream("D:\\desktops\\desktop 3\\yashwanth app\\year sorted\\"+"newdata.zip");
			//File folder=new File("D:\\desktops\\desktop 3\\yashwanth app\\year sorted\\new data");
			ZipOutputStream zos = new ZipOutputStream(fos);
			

			String fullData="";
			HashMap<String, ArrayList<Record>> filestoSave=new HashMap<String,ArrayList<Record>>();		
			
			//String[] list=folder.list();
			StringBuilder sb=new StringBuilder();
			
			for(int i=1;i<90;i++){
				byte[] buffer = new byte[1024];
				ZipEntry ze= new ZipEntry(i+".txt");
				zos.putNextEntry(ze);
	    		FileInputStream in = new FileInputStream("D:\\desktops\\desktop 3\\yashwanth app\\year sorted\\newdata\\"+i+".txt");
	   	   
	    		int len;
	    		while ((len = in.read(buffer)) > 0) {
	    			zos.write(buffer, 0, len);
	    		}

	    		in.close();
	    		zos.closeEntry();
			}
			zos.close();
	           
	    } catch (IOException e) {
			
		} finally {
			
		}

	}
}
