
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetTransactionElements {
	/*public static HashMap<String, ArrayList<TransitionElement>> getTransactionElements(ArrayList<String> inputs){
		
		
		HashMap<String, ArrayList<TransitionElement>> returnValues=new HashMap<String, ArrayList<TransitionElement>>();
		File folder=new File(directoryName);
		File[] listOfFiles=folder.listFiles();
		for(File f:listOfFiles){
			if(exists(f.getName())){
				returnValues.add(getMap(f.getAbsolutePath()));
			}
		}
		return null;
	}*/
	
	private static boolean exists(String fileName){
		String[] fileNames=new String[]{"Sunday.xlsx","Monday.xlsx",
				"Tuesday.xlsx","Wednesday.xlsx","Thursday.xlsx","Friday.xlsx","Saturday.xlsx"};
		
		for(String name:fileNames){
			if(name.equals(fileName))
				return true;
		}
		return false;
	}
	
	/*private static getMap(String file){
		List<String> fileData=getData(file);
		ArrayList<TransitionElement> transitionElements=new ArrayList<TransitionElement>();
		for(int i=0;i<fileData.size();i++){
			int returnValue=check(transitionElements,fileData.get(i));
			if(returnValue>-1){
				
			}else{
				TransitionElement te=new TransitionElement(fileData.get(i));
				transitionElements.add(te);
				//te.addCount(nextElement);
			}
		}
		
	}*/
	
	private static int check(ArrayList<TransitionElement> list,String currentName){
		Iterator<TransitionElement> itr=list.iterator();
		int i=0;
		while(itr.hasNext()){
			if(itr.next().currentElement.equals(currentName))
				return i;
		}
		return -25;
	}
	
	private static List<String> getData(String fileName){
		List<String> apps=new ArrayList<String>();
		try{
		
			FileInputStream file = new FileInputStream(new File("G:/excelresults/demo.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);		
		
			XSSFSheet sheet = workbook.getSheetAt(0);
				//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Cell cell;
			
			
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				apps.add(row.getCell(0).toString());
				//System.out.print(row.getCell(0).toString()+"\t");			
			}
			file.close();
		}catch(Exception ex){
			
		}
		
		return apps;

	}
}
