package zip;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipReader {
	public static void main(String[] gcs){
		
		String[] requiredStrings;
		try{
			ZipFile zipFile = new ZipFile("E:/old laptop/d drive/softwares/tekvergence/sample4.xps");			
			ZipEntry startPartEntry = zipFile.getEntry("Documents/1/Pages/5.fpage");
			
			
			InputStream stream = zipFile.getInputStream(startPartEntry);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader in = new BufferedReader(reader);
			
			String fll="";

			String readed;
			while ((readed = in.readLine()) != null) {
				fll+=readed;
			    //System.out.println(readed);
			}
			
			String[] results=fll.split("\t");
			
			ArrayList<String> finale=new ArrayList<String>();			
			
			for(String s:results){
				if(s.startsWith("<Glyphs"))
					finale.add(s);
			}
			
			Iterator iterator=finale.iterator();
			requiredStrings=new String[finale.size()];
			int i=0;
			String f;
			while(iterator.hasNext()){
				f=((String)iterator.next());
				f=f.substring(f.indexOf("UnicodeString")+15,f.indexOf("/>")-2);
				//System.out.println(f);
				requiredStrings[i]=f;
				i++;
			}
			
			for(i=0;i<requiredStrings.length;i++){
				System.out.println(i);
				System.out.println(requiredStrings[i]);
			}
			
			zipFile.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
