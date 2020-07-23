package yaswanth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SortByClass {
	public static void main(String[] gcs){
		FetchRecordsFromFile frff=new FetchRecordsFromFile();		
		String fileName="D:\\desktops\\desktop 3\\yashwanth app\\year\\fullCutCutAscii.txt";
		ArrayList<Record> records=null;
		try{
			records=frff.fasterFetchRecords(new File(fileName));
		}catch(Exception ex){
			
		}
		sortByYear(records);
	}
	
	private static void sortByYear(ArrayList<Record> records){
		HashMap<Integer,ArrayList<Record>> hash=new HashMap<Integer,ArrayList<Record>>();
		Iterator<Record> itr=records.iterator();
		int year=0;
		Record record;
		while(itr.hasNext()){
			record=itr.next();
			year=record.citationYear;
			ArrayList<Record> yearRecords=hash.get(year);
			if(yearRecords==null){
				yearRecords=new ArrayList<Record>();
				yearRecords.add(record);
				hash.put(year,yearRecords);
			}else{
				yearRecords.add(record);
			}
		}
		
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=2015;i>1960;i--){
			ArrayList<Record> yearRecords=hash.get(i);			
			if(yearRecords==null)
				continue;
			System.out.println(yearRecords.size());
			itr=yearRecords.iterator();
						
			while(itr.hasNext()){
				record=itr.next();				
				sb.append(record.citationYear+"\r\n");
				sb.append(record.court+"\r\n");
				sb.append(record.company+"\r\n");
				sb.append(record.citation+"\r\n");
				sb.append(record.data+"\r\n");
			}
		}
		
		save(sb);
		
	}
	
	public static void save(StringBuilder sb){
		
		try{
			File file =new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\fullCutCutAsciiSorted.txt");
			
			//if file doesnt exists, then create it
			if(!file.exists()){
				file.createNewFile();
			}
			
			//true = append file
			
			  FileWriter fileWritter = new FileWriter(file.getAbsolutePath());
		      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		      bufferWritter.write(sb.toString());
		      bufferWritter.close();	           	    	    
		      System.out.println("fullData is written");    	      
			
	        
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
