
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Jama.Matrix;


public class ReadFromCSV{
	
	
	public static void writeDataSets(String fileName,Matrix transformedData ){
		
		
		StringBuilder sb=new StringBuilder("");
		for(int r = 0; r < transformedData.getRowDimension(); r++){
            for(int c = 0; c < transformedData.getColumnDimension(); c++){
                sb.append(transformedData.get(r, c));
                if (c == transformedData.getColumnDimension()-1) continue;
                sb.append(",");
            }
           sb.append("\r\n");
        }
				
		save(fileName,sb);
		
		

	}
	
	public static void save(String fileName,StringBuilder sb){
		
		try{
			File file =new File(fileName+".csv");
    		
    		//if file doesnt exists, then create it
    		//if(!file.exists()){
    			//file.createNewFile();
    		//}
    		
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


	public static ArrayList<String> readFromCSV(String fileName){
		
		ArrayList<String> maps = new ArrayList<String>();
		
		BufferedReader br = null;
		
		String cvsSplitBy = ",";
		String str="";
	
		try {

			String split[]=null;
			String dayname="";
			br = new BufferedReader(new FileReader(fileName));
			int ip=0;
			
			while ((str = br.readLine()) != null) {

				//System.out.println(line);
				
				//split=line.split(cvsSplitBy);
				//System.out.println(split.length);
				
				if(ip==0){
					//line1=str;
					ip++;
					continue;
					
				}
				String copy="";
				 boolean inQuotes = false;
				for(int i=0; i<str.length(); ++i)
		        {
					if (str.charAt(i)=='"'){
						inQuotes = !inQuotes;
						continue;
					}
					if (str.charAt(i)==',' && inQuotes)
						copy += "";
					else
						copy += str.charAt(i);
		        }
				
				
				maps.add(copy);	
								

			}
			//System.out.println("out of while loop");

		
		} catch (FileNotFoundException e) {
			System.out.println(str);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(str);
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(str);
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		//writeToFiles("C://Users//gc//Desktop//markov//javaoutput//",maps,line1);
		//writeDataSets("C://Users//gc//Desktop//markov//javaoutput//",maps,line1);
		//writeTrainingAndTest("C://Users//gc//Desktop//markov//",maps,line1);
		return maps;
		
	}
	
		
}
