package yaswanth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DivideFiles {
	public static void main(String[] gcs){
		File folder=new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\");		
		String sCurrentLine="";	
		StringBuilder sb=new StringBuilder();
	
		BufferedReader br=null;
		
		try {			
			br = new BufferedReader(new FileReader(folder+"\\fullCutCutAsciiSorted.txt"));
			int k=0;int f=0;
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine+"\r\n");
				k++;
				if(k%5000==0){
					f++;
					save(f+"",sb);
					sb=new StringBuilder();
				}  			  	
			}
			
			br.close();
			f++;
			save(f+"",sb);
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
	
	public static void save(String fileName,StringBuilder sb){
		
		try{
			File file =new File("D:\\desktops\\desktop 3\\yashwanth app\\year sorted\\"+fileName+".txt");
    		
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
