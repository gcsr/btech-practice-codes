

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class LoadRecords {
	
	public static void main(String[] gcs){
		File folder=new File("data.txt");
		String fullData="";
		//HashMap<String, ArrayList<Record>> filestoSave=new HashMap<String,ArrayList<Record>>();		
		
		//String[] list=folder.list();
		StringBuilder sb=new StringBuilder();
		int i=0;
		//for(String s:list){
			BufferedReader br = null;
			String sCurrentLine="";
			String[][] ff;
			ArrayList<String[]> results=new ArrayList<String[]>();
			try {			

				br = new BufferedReader(new FileReader(folder));
				
				while ((sCurrentLine = br.readLine()) != null) {
					sCurrentLine=sCurrentLine.replaceAll("\"", "");
					results.add(sCurrentLine.split("\\t"));
					System.out.println(sCurrentLine);
					
					//sb.append(sCurrentLine+"\r\n");
						//fullData+=sCurrentLine+"\r\n";
					 					
				}
				
				Gson gson = new Gson();
				

				// 1. Java object to JSON, and save into a file
				gson.toJson(results, new FileWriter("data.json"));
				
				// 2. Java object to JSON, and assign to a String
				String jsonInString = gson.toJson(results);
				br.close();
				System.out.println("loaded files");

			} catch (IOException e) {
				System.out.println(folder);
				System.out.println(sCurrentLine);
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		//}
		
		
			
	}		//System.out.println(i);
	
	public static String[] getStringArray(String sCurrentLine){
		String[] app=sCurrentLine.split("\\t");
		return app;
	}


}
