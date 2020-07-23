package yaswanth;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by gc on 2015-09-29.
 */
public class FetchRecordsFromFile {

    static int id = 1;


    public ArrayList<Record> fasterFetchRecords(File f) throws FileNotFoundException {
      
        BufferedReader bufferedReader = null;
        String line="";
        String previousLine="";

        ArrayList<Record> records=new ArrayList<Record>();
        try {
            int i = 0;
       
            bufferedReader =  new BufferedReader(new FileReader(f));
            Record record = new Record();
            while ((line = bufferedReader.readLine()) != null) {
                    record = new Record();
                    record.setCitationYear(Integer.parseInt(line));
                    record.setCourt(bufferedReader.readLine());
                    record.setCompany(bufferedReader.readLine());
                    previousLine=bufferedReader.readLine();
                    record.setCitation(previousLine);
                    record.setData(bufferedReader.readLine());
                    record.setId(id);
                    records.add(record);
                    id++;
            }

            if (bufferedReader != null)
                bufferedReader.close();
           

        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            System.out.println(line);
            System.out.println(previousLine);
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
              
            } catch (Exception exe) {

            }
        }
        System.gc();
        
        return records;

    }
}
