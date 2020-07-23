package yaswanth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NonAciitoAscii {
	public static void main(String[] gcs){
		File folder=new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\");		
		String sCurrentLine="";	
		StringBuilder sb=new StringBuilder();
		
		BufferedReader br=null;
		
		try {			
			br = new BufferedReader(new FileReader(folder+"\\finalFullCut.txt"));
			int k=0;int f=0;
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine=sCurrentLine.replaceAll("[^\\x20-\\x7e]", "");
				sb.append(sCurrentLine+"\r\n");			  	
		}
		
		
		save(sb);
	} catch (IOException e) {
		
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			}
	}


}

public static void save(StringBuilder sb){
	
	try{
		File file =new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\fullCutCutAscii.txt");
		
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
