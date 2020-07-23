
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
	
	public static HashMap<String,ArrayList<String>> apps=new HashMap<String, ArrayList<String>>();
	public static void main(String[] gcs){
		String csvFile = "C://Users//gc//Desktop//markov//U11.csv";
		// HashMap<String,ArrayList<String>> ss=getTestData();
		 HashMap<String,ArrayList<String>> test=ReadFromCSV.getTestData();
		 HashMap<String,ArrayList<String>> result=ReadFromCSV.readExcelData();
		 HashMap<String,ArrayList<TransitionObject>> finalResult=ReadFromCSV.fullCheck(result, test);
		 
		 for (Map.Entry<String, ArrayList<TransitionObject>> entry : finalResult.entrySet()) {
				String key=entry.getKey();
				System.out.println(key);
				ArrayList<TransitionObject> value=entry.getValue();
				Iterator<TransitionObject> itr=value.iterator();
				while(itr.hasNext()){
					TransitionObject temp=itr.next();
					System.out.println("individual object");
					System.out.println(temp.getCurrentElement());
					System.out.println(temp.getNextElement());
					System.out.println(temp.getFavour());
					System.out.println(temp.getOppose());
				}
			}
			
		//HashMap<String, List<String>> maps=readFromCSV(csvFile);
		//writeToFiles("C://Users//gc//Desktop//markov//javaoutput//",maps);
		//writeDataSets("C://Users//gc//Desktop//markov//javaoutput//",maps);
	}
	
	public static HashMap<String, ArrayList<String>> readFromCSV(String fileName){
		
		HashMap<String,ArrayList<String>> maps = new HashMap<String,ArrayList<String>>();
		
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
			while ((line = br.readLine()) != null) {

				//System.out.println(line);
				
				split=line.split(cvsSplitBy);
				//System.out.println(split.length);
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
		
		
		writeToFiles("C://Users//gc//Desktop//markov//javaoutput//",maps,line1);
		writeDataSets("C://Users//gc//Desktop//markov//javaoutput//",maps,line1);
		writeTrainingAndTest("C://Users//gc//Desktop//markov//",maps,line1);
		return maps;
		
	}
	
	public static void writeToFiles(String directory,HashMap<String, ArrayList<String>> maps,String line1){
		
		File f=new File(directory);
		if(!f.exists()){
			f.mkdir();
		}
		String fileName=null;//entry.getKey()+".CSV";
		List<String> fileDetails=null;//entry.getValue();
		Iterator<String> itr;//=fileDetails.iterator();
		String temp;
		for (Map.Entry<String, ArrayList<String>> entry : maps.entrySet()) {

			fileName=entry.getKey()+".CSV";
			fileDetails=entry.getValue();
			itr=fileDetails.iterator();
			StringBuffer content=new StringBuffer();
			content.append(line1+"\r\n");
			while(itr.hasNext()){
				temp=itr.next();
				content.append(temp+"\r\n");
			}
			
			try{
		  
				FileWriter fileWritter = new FileWriter(directory+fileName+".CSV");
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(content.toString());
				bufferWritter.close();	           	    	    
				System.out.println("contents written to "+fileName);
			}catch(Exception ex){
				
			}

		}
	}
	
	public static void writeDataSets(String directory,HashMap<String, ArrayList<String>> maps,String line1){
		File f=new File(directory);
		if(!f.exists()){
			f.mkdir();
		}
		String fileName=null;//entry.getKey()+".CSV";
		List<String> fileDetails=null;//entry.getValue();
		Iterator<String> itr;//=fileDetails.iterator();
		String temp;
		for (Map.Entry<String, ArrayList<String>> entry : maps.entrySet()) {

			StringBuffer trainingData=new StringBuffer();
			StringBuffer testData=new StringBuffer();
			trainingData.append(line1+"\r\n");
			testData.append(line1+"\r\n");
			
			fileName=entry.getKey();
			fileDetails=entry.getValue();
			int size=fileDetails.size();
			int trainingDataSize=80*size/100;
			int i=0;
			while(i<size){
				if(i<=trainingDataSize)
					trainingData.append(fileDetails.get(i)+"\r\n");				
				else
					testData.append(fileDetails.get(i)+"\r\n");
				i++;
			}	
			
			createFile(directory+fileName+"-training.csv",trainingData);
			createFile(directory+fileName+"-test.csv",testData);		
			

		}
		
		
		
		

	}
	
	public static void writeTrainingAndTest(String directory,HashMap<String, ArrayList<String>> maps,String line1){
		File f=new File(directory);
		if(!f.exists()){
			f.mkdir();
		}
		String fileName=null;//entry.getKey()+".CSV";
		List<String> fileDetails=null;//entry.getValue();
		Iterator<String> itr;//=fileDetails.iterator();
		String temp;
		StringBuffer trainingData=new StringBuffer();
		StringBuffer testData=new StringBuffer();
		
		trainingData.append(line1+"\r\n");
		testData.append(line1+"\r\n");
	
		for (Map.Entry<String, ArrayList<String>> entry : maps.entrySet()) {

				
			fileName=entry.getKey();
			fileDetails=entry.getValue();
			int size=fileDetails.size();
			int trainingDataSize=80*size/100;
			int i=0;
			while(i<size){
				if(i<=trainingDataSize)
					trainingData.append(fileDetails.get(i)+"\r\n");				
				else
					testData.append(fileDetails.get(i)+"\r\n");
				i++;
			}	

		}
		createFile(directory+"training.csv",trainingData);
		createFile(directory+"test.csv",testData);		
		
		

	}
	
	
	
	public static void createFile(String fileName,StringBuffer content){
		try{
			  
			FileWriter fileWritter = new FileWriter(fileName);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(content.toString());
			bufferWritter.close();	           	    	    
			System.out.println("contents written to "+fileName);
		}catch(Exception ex){
			
		}


	}
	
	/*public void getTransationElements(HashMap<String, List<String>> maps){
		
		String fileName="";
		List<String> fileDetails=null;//entry.getValue();
		Iterator<String> itr;//=fileDetails.iterator();
		
		HashMap<String, ArrayList<TransitionElement>> returnValues=new HashMap<String, ArrayList<TransitionElement>>();
		
		
		for (Map.Entry<String, List<String>> entry : maps.entrySet()) {

			HashMap
			fileName=entry.getKey();
			fileDetails=entry.getValue();
			int size=fileDetails.size();			
			int i=0;
			while(i<size){
				
			}	

		}
	}*/
	
	public static ArrayList<TransitionObject> check(ArrayList<String> result,ArrayList<String> test){
		int i=0;
		int size=result.size();
		ArrayList<TransitionObject> tObjects=new ArrayList<TransitionObject>();
		while(i<(size-1)){
			
			if(result.get(i).equals(result.get(i+1))){
				i++;
				continue;
			}
			TransitionObject object=new TransitionObject(result.get(i),result.get(i+1));
			boolean objectFound=false;
			Iterator<TransitionObject> itr=tObjects.iterator();
			while(itr.hasNext()){
				if(itr.next().equals(object)){
					objectFound=true;
					break;
				}
			}
			if(!objectFound){
				System.out.println("adding t object");
				tObjects.add(getTransitionObject(object,test));
			}
			i++;
		}
		return tObjects;
	}
	
	public static ArrayList<TransitionObject> check2(ArrayList<ProbabilityElement> result,ArrayList<String> test){
		int i=0;
		int size=result.size();
		ArrayList<TransitionObject> tObjects=new ArrayList<TransitionObject>();
		while(i<(size)){
			TransitionObject object=
				new TransitionObject(result.get(i).getCurrentApp(),result.get(i).getNextApp());
			Iterator<TransitionObject> itr=tObjects.iterator();
			tObjects.add(getTransitionObject(object,test));
			
			i++;
		}
		return tObjects;
	}
	
	public static ArrayList<TransitionObject2> check3(ArrayList<ProbabilityElement2> result,ArrayList<String> test){
		int i=0;
		int size=result.size();
		ArrayList<TransitionObject2> tObjects=new ArrayList<TransitionObject2>();
		while(i<(size)){
			TransitionObject2 object=
				new TransitionObject2(result.get(i).getCurrentApp(),result.get(i).getNextApp());
			Iterator<TransitionObject2> itr=tObjects.iterator();
			tObjects.add(getTransitionObject2(object,test));
			
			i++;
		}
		return tObjects;	
		
	}
	
	
	public static TransitionObject getTransitionObject(TransitionObject input,ArrayList<String> test){
		
		
		int size=test.size();
		int i=0;
		
		String currentElement=input.getCurrentElement();
		String nextElement=input.getNextElement();
		
		while(i<(size-1)){
			/*if(test.get(i).equals(test.get(i+1))){
				i++;
				continue;
			}*/
			if(test.get(i).equals(currentElement)){
				if(test.get(i+1).equals(nextElement))
					input.addFavour();
				else
					input.addOppose();
				
			}
			
			i++;
		}
		return input;
	}
	
	public static TransitionObject2 getTransitionObject2(TransitionObject2 input,ArrayList<String> test){
		
		
		int size=test.size();
		int i=0;
		
		String currentElement=input.getCurrentElement();
		String[] nextElement=input.getNextElement();
		//System.out.println(nextElement);
		
		while(i<(size-1)){
			
			if(test.get(i).equals(currentElement)){
				//System.out.println(test.get(i+1));
				if(!test.get(i+1).equals(currentElement)){
					if(input.check(test.get(i+1)))
						input.addFavour();
					else
						input.addOppose();
				}
				
			}
			
			i++;
		}
		return input;
	}
	
	
	
	
	public static HashMap<String,ArrayList<String>> getTestData(){
		String[] fileNames=new String[]{"Sunday","Monday",
				"Tuesday","Wednesday","Thursday","Friday","Saturday","Complete"};
		HashMap<String,ArrayList<String>> maps = new HashMap<String,ArrayList<String>>();
		
		for(String fileName:fileNames){
			
			ArrayList<String> appList=new ArrayList<String>();		
			
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			
			String line1="";
			String middlePart="";


			try {

				String split[]=null;
				String dayname="";
				if(fileName.equals("Complete"))
					br = new BufferedReader(new FileReader("C://Users//gc//Desktop//markov//test.CSV"));
				else
					br = new BufferedReader(new FileReader("C://Users//gc//Desktop//markov//javaoutput//"+fileName+"-test.CSV"));
				int i=0;
				while ((line = br.readLine()) != null) {

					//System.out.println(line);
					
					split=line.split(cvsSplitBy);
					//System.out.println(split.length);
					i++;
					if(i==1){
						line1=line;
						continue;
						
					}
					
					
					appList.add(split[30]);	
					
					
					
					//System.out.println(i);
					
					
					//writeToFiles(maps);
					

				}
				
				System.out.println("app list size is"+appList.size());

			
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
			maps.put(fileName, appList);
		}
		
		return maps;

	}
	public static HashMap<String,ArrayList<String>> readExcelData(){
		String[] fileNames=new String[]{"Sunday","Monday",
				"Tuesday","Wednesday","Thursday","Friday","Saturday","Complete"};
		HashMap<String,ArrayList<String>> maps = new HashMap<String,ArrayList<String>>();
		
		for(String fileName:fileNames){
			
			ArrayList<String> appList=new ArrayList<String>();
			
			
			try{
			
				FileInputStream file=null;
				if(fileName.equals("Complete"))
					file= new FileInputStream(new File("C://Users//gc//Desktop//markov//complete_Markov.xlsx"));
				else
					file= new FileInputStream(new File("C://Users//gc//Desktop//markov//"+fileName+".xlsx"));
				
				//	Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(file);		
				
				XSSFSheet sheet = workbook.getSheetAt(0);
				//	Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				Cell cell;
				
				String fp="";
				int i=0;
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					fp=row.getCell(1).toString();
					i++;
					if(i==1)
						continue;
					fp=fp.replace(',', ' ');
					appList.add(fp);
								
				}
				file.close();
		
				//System.out.print("app list size is "+appList.size());
				maps.put(fileName, appList);
			}catch(Exception ex){
				try{
					Thread.currentThread().sleep(20000);						
				}catch(Exception exp){
					exp.printStackTrace();
				}
				return readExcelData();
			}
		}
		
		return maps;

	}
	
	public static HashMap<String,ArrayList<TransitionObject>> fullCheck(HashMap<String,ArrayList<String>> result,HashMap<String,ArrayList<String>> test){
		HashMap<String,ArrayList<TransitionObject>> finalResult=new HashMap<String,ArrayList<TransitionObject>>();
		for (Map.Entry<String, ArrayList<String>> entry : result.entrySet()) {
			String key=entry.getKey();
			ArrayList<String> res=entry.getValue();
			ArrayList<String> tes=test.get(key);
			System.out.println(key);
			System.out.println("result size is "+res.size());
			System.out.println("test size is "+tes.size());
			finalResult.put(key, check(res, tes));			
		}
		
		return finalResult;

	}
	
	public static HashMap<String,ArrayList<TransitionObject>> fullCheck2(HashMap<String,ArrayList<ProbabilityElement>> result,HashMap<String,ArrayList<String>> test){
		HashMap<String,ArrayList<TransitionObject>> finalResult=new HashMap<String,ArrayList<TransitionObject>>();
		for (Map.Entry<String, ArrayList<ProbabilityElement>> entry : result.entrySet()) {
			String key=entry.getKey();
			ArrayList<ProbabilityElement> res=entry.getValue();
			ArrayList<String> tes=test.get(key);
			System.out.println(key);
			System.out.println("result size is "+res.size());
			System.out.println("test size is "+tes.size());
			finalResult.put(key, check2(res, tes));			
		}
		
		return finalResult;

	}
	
	public static HashMap<String,ArrayList<TransitionObject2>> fullCheck3(HashMap<String,ArrayList<ProbabilityElement2>> result,HashMap<String,ArrayList<String>> test){
		HashMap<String,ArrayList<TransitionObject2>> finalResult=new HashMap<String,ArrayList<TransitionObject2>>();
		for (Map.Entry<String, ArrayList<ProbabilityElement2>> entry : result.entrySet()) {
			String key=entry.getKey();
			ArrayList<ProbabilityElement2> res=entry.getValue();
			ArrayList<String> tes=test.get(key);
			System.out.println(key);
			System.out.println("result size is "+res.size());
			System.out.println("test size is "+tes.size());
			finalResult.put(key, check3(res, tes));			
		}
		
		return finalResult;

	}
	
	public static HashMap<String,ArrayList<ProbabilityElement>> getProbabilityElements(){
		HashMap<String,ArrayList<ProbabilityElement>> results=new HashMap<String,ArrayList<ProbabilityElement>>();
			
		String[] fileNames=new String[]{"Sunday","Monday",
				"Tuesday","Wednesday","Thursday","Friday","Saturday","Complete"};
		
		
		for(String fileName:fileNames){
			
			ArrayList<ProbabilityElement> appList=new ArrayList<ProbabilityElement>();
			ArrayList<String> topRowElements=new ArrayList<String>();
			ArrayList<String> leftColumnElements=new ArrayList<String>();
			ProbabilityElement pe;
			System.out.println(fileName);
			try{
			
				FileInputStream file =null;
				if(fileName.equals("Complete"))
					file= new FileInputStream(new File("C://Users//gc//Desktop//markov//complete_Markov.xlsx"));
				else
					file= new FileInputStream(new File("C://Users//gc//Desktop//markov//"+fileName+"_matrix.xlsx"));
				
				//	Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(file);		
				
				XSSFSheet sheet = workbook.getSheetAt(0);
				//	Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				Cell cell;
				
				String fp="";
				int i=0;
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					int j=0;
					if(i==0){
						
						Iterator<Cell> cells=row.cellIterator();
						while(cells.hasNext()){
							String nextValue=cells.next().getStringCellValue();
							nextValue=nextValue.replaceAll("\"","");
							topRowElements.add(nextValue);			
							
						}
						//System.out.println(topRowElements.size());
					}else{
						j=0;
						Iterator<Cell> cells=row.cellIterator();
						String currentElement="";
						while(cells.hasNext()){
							if(j==0){
								currentElement=cells.next().getStringCellValue();
								currentElement=currentElement.replaceAll(" ",".");
								currentElement=currentElement.replaceAll("-",".");
							}
							else{
								double prob=cells.next().getNumericCellValue();
								//System.out.println(j);
								if(prob!=0 && !currentElement.equals(topRowElements.get(j))){
									pe=new ProbabilityElement(currentElement,topRowElements.get(j),prob);
									appList.add(pe);
								}
							}
							j++;
						}
					}
					i++;
								
				}
				file.close();
		
				//System.out.print("app list size is "+appList.size());
				results.put(fileName, appList);
			}catch(Exception ex){
				ex.printStackTrace();
				try{
					Thread.currentThread().sleep(20000);						
				}catch(Exception exp){
					exp.printStackTrace();
				}
				return getProbabilityElements();
			}
		}
		
		

		
		return results;
	}
	
	public static HashMap<String,ArrayList<ProbabilityElement2>> getProbabilityElement2s(){
		HashMap<String,ArrayList<ProbabilityElement2>> results=new HashMap<String,ArrayList<ProbabilityElement2>>();
			
		String[] fileNames=new String[]{"Sunday","Monday",
				"Tuesday","Wednesday","Thursday","Friday","Saturday","Complete"};
		
		
		for(String fileName:fileNames){
			
			ArrayList<ProbabilityElement2> appList=new ArrayList<ProbabilityElement2>();
			ArrayList<String> topRowElements=new ArrayList<String>();
			ArrayList<String> leftColumnElements=new ArrayList<String>();
			ProbabilityElement pe;
			try{
			
				FileInputStream file=null;
				if(fileName.equals("Complete"))
					file= new FileInputStream(new File("C://Users//gc//Desktop//markov//complete_Markov.xlsx"));
				
				else
					file= new FileInputStream(new File("C://Users//gc//Desktop//markov//"+fileName+"_matrix.xlsx"));
				
				//	Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(file);		
				
				XSSFSheet sheet = workbook.getSheetAt(0);
				//	Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				Cell cell;
				
				String fp="";
				int i=0;
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					int j=0;
					if(i==0){
						j=0;
						Iterator<Cell> cells=row.cellIterator();
						while(cells.hasNext()){
							
							String nextValue=cells.next().getStringCellValue();
							nextValue=nextValue.replaceAll("\"","");
							topRowElements.add(nextValue);		
							
						}
					}else{
						j=0;
						Iterator<Cell> cells=row.cellIterator();
						String currentElement="";
						ArrayList<ProbabilityElement> temp=new ArrayList<ProbabilityElement>();
						double totalProb=0;
						while(cells.hasNext()){
							if(j==0){
								currentElement=cells.next().getStringCellValue();
								currentElement=currentElement.replaceAll(" ",".");
								currentElement=currentElement.replaceAll("-",".");
							}
							else{
								double prob=cells.next().getNumericCellValue();
								if(prob!=0 && !currentElement.equals(topRowElements.get(j))){
									pe=new ProbabilityElement(currentElement,topRowElements.get(j),prob);
									if(fileName.equals("Thursday") && currentElement.equals("systemui"))
										System.out.println(pe.getProbability());
										
									temp.add(pe);
								}
							}
							j++;
						}
						Collections.sort(temp);
						if(temp.size()>0){
							int tempjk=0;
							String[] nextElements=new String[3];
							for(;tempjk<3 && tempjk<temp.size();tempjk++){
								nextElements[tempjk]=temp.get(tempjk).getNextApp();
								totalProb+=temp.get(tempjk).getProbability();
							}
							ProbabilityElement2 pb=new ProbabilityElement2(currentElement,nextElements,totalProb);
							if(fileName.equals("Thursday") && currentElement.equals("systemui")){
								System.out.println();
							}
							appList.add(pb);
						}
					}
					i++;
										
				}
				file.close();
				results.put(fileName, appList);
				//System.out.print("app list size is "+appList.size());
				
			}catch(Exception ex){
				ex.printStackTrace();
				try{
					Thread.currentThread().sleep(20000);						
				}catch(Exception exp){
					exp.printStackTrace();
				}
				return getProbabilityElement2s();
			}
		}
		
		

		
		return results;
	}
	
}
