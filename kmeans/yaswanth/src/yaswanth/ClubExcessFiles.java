package yaswanth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClubExcessFiles {
	public static void main(String[] gcs){
		File folder=new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\");
		String fullData="";
		HashMap<String, ArrayList<Record>> filestoSave=new HashMap<String,ArrayList<Record>>();		
		
		String[] list=folder.list();
		StringBuilder sb=new StringBuilder();
		
		for(int i=2013;i<2016;i++){
			BufferedReader br = null;
			String sCurrentLine="";
			try {			

				br = new BufferedReader(new FileReader(folder+"\\"+i+".txt"));
				
				while ((sCurrentLine = br.readLine()) != null) {
					sb.append(sCurrentLine+"\r\n");
						//fullData+=sCurrentLine+"\r\n";
					 					
				}
				br.close();
				System.out.println(folder+"\\"+i+".txt");

			} catch (IOException e) {
				System.out.println(folder+"\\"+i+".txt");
				System.out.println(sCurrentLine);
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		save(sb);
		
			
	}		//System.out.println(i);
	
	public static void save(StringBuilder sb){
		
		try{
			File file =new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\8.txt");
    		
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		//true = append file
    		
    		  FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
    	      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	      bufferWritter.write(sb.toString());
    	      bufferWritter.close();	           	    	    
    	      System.out.println("fullData is written");    	      
    		
	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
  	
	}
		
	

}
