package yaswanth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OnlyData {
	public static void main(String[] gcs){
		File folder=new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\");
		StringBuilder sb=new StringBuilder();
		BufferedReader br = null;
		String sCurrentLine="";
		try {			

			br = new BufferedReader(new FileReader(folder+"\\fullData.txt"));
			int i=0;
			while ((sCurrentLine = br.readLine()) != null) {
				i++;
				if(i%5==0)
				sb.append(sCurrentLine+"\r\n");
					//fullData+=sCurrentLine+"\r\n";
				 					
			}
			br.close();
			System.out.println(folder+"\\"+i+".txt");
			save(sb);

		} catch (IOException e) {
			
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
			File file =new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\onlyData.txt");
    		
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
