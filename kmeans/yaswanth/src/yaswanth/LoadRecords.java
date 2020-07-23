package yaswanth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoadRecords {
	
	public static void main(String[] gcs){
		File folder=new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\fullData.txt");
		String fullData="";
		//HashMap<String, ArrayList<Record>> filestoSave=new HashMap<String,ArrayList<Record>>();		
		
		//String[] list=folder.list();
		StringBuilder sb=new StringBuilder();
		int i=0;
		//for(String s:list){
			BufferedReader br = null;
			String sCurrentLine="";
			try {			

				br = new BufferedReader(new FileReader(folder));
				
				while ((sCurrentLine = br.readLine()) != null) {
					sb.append(sCurrentLine+"\r\n");
						//fullData+=sCurrentLine+"\r\n";
					 					
				}
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


}
