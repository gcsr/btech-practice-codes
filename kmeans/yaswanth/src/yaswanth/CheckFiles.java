package yaswanth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;

public class CheckFiles {
	static ArrayList<Record> records=new ArrayList<Record>();
	public static void main(String[] gcs){
		 
         FetchRecordsFromFile frff=new FetchRecordsFromFile();
         BufferedReader reader = null;
         /*File root = android.os.Environment.getExternalStorageDirectory();
         String directory=root.getAbsolutePath()+"/tax-monitor/";*/
         try {
             for(int fileNumber=9;fileNumber>0 ;fileNumber--){
                 //reader = new BufferedReader(
                 //      new InputStreamR    eader(getAssets().open("filename.txt")));
                 File f=new File("D:\\desktops\\desktop 3\\yashwanth app\\new Data\\"+fileNumber+".txt");
                 frff.fasterFetchRecords(f);
                 //MainActivity.records.addAll
                 //progressed=(MainActivity.records.size()*100/97000);
                 //onProgressUpdate((int)progressed );
                 System.out.println("written records " +records.size());

             }
             
         }catch(FileNotFoundException ex){
        	 ex.printStackTrace();
         }
         catch(Exception ex){             
             ex.printStackTrace();
             System.out.println();
         }
	}
}
