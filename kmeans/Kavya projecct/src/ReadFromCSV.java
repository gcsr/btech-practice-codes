
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromCSV {
	
	
	public static ArrayList<ArrayList<String>> readFromCSV(String fileName){
		
		ArrayList<ArrayList<String>> maps = new ArrayList<ArrayList<String>>();
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		String line1="";
		String middlePart="";


		try {

			String split[]=null;
			String dayname="";
			br = new BufferedReader(new FileReader(fileName));
			int i=0;
			
			ArrayList<String> temp=null;
			line=br.readLine();
			while ((line = br.readLine()) != null) {

				temp=new ArrayList<String>();
				line=getLine(line);
				split=line.split(cvsSplitBy);
				
				//if(!split[0].equals("1"))
					//continue;
				for(String s:split)
					temp.add(s);
				maps.add(temp);
			}
			//System.out.println("out of while loop");

		
		} catch (FileNotFoundException e) {
			System.out.println(line);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(line);
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(line);
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return maps;
	}
	
	public static String getLine(String str){
		String copy="";
		 boolean inQuotes = false;
		for(int i=0; i<str.length(); ++i){
			if (str.charAt(i)=='"'){
				inQuotes = !inQuotes;
				continue;
			}
			if (str.charAt(i)==',' && inQuotes)
				copy += "";
			else
				copy += str.charAt(i);
		}
		return copy;
		
	
	}
	
}

/*System.out.println(split.length);
i++;
if(i==1){
	line1=line;
	continue;
	
}
	


while(split.length<35){
	i++;
	line+=br.readLine();
	split=line.split(cvsSplitBy);
}
	
if(line.contains("[")){
	String firstPart=line.substring(0,line.indexOf('[')+1);
	String nextPart=line.substring(line.lastIndexOf(']'));
	middlePart=line.substring(line.indexOf('[')+1,line.lastIndexOf(']'));
	middlePart=middlePart.replace(',', ' ');
	line=firstPart+middlePart+nextPart;
	split=line.split(cvsSplitBy);
}
if(split.length!=35){
	System.out.println(split[25]);
	System.out.println(line);
	System.out.println(split.length);
	System.out.println("error");
}

dayname=split[25];
//dayname=split[19];


if(maps.containsKey(dayname)){
	maps.get(dayname).add(line);
	//apps.get(dayname).add(middlePart);
}else{
	ArrayList<String> aList=new ArrayList<String>();
	//ArrayList<String> appList=new ArrayList<String>();
	aList.add(line);
	//appList.add(middlePart);
	maps.put(dayname, aList);
	//apps.put(dayname, appList);
	
}

//System.out.println(i);


//writeToFiles(maps);
*/
