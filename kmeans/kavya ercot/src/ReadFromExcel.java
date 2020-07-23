import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ReadFromExcel {
	public static ArrayList<ArrayList<String>> readExcel(String fileName)throws InvalidFormatException, IOException{
		
		System.out.println(fileName);
		ArrayList<String> ids=new ArrayList<String>();
		//"C:\\Users\\gc\\Desktop\\yashwanth\\data\\central excise circulars\\columns.xlsx"));
		FileInputStream file = new FileInputStream(new File(fileName));

		//Create Workbook instance holding reference to .xlsx file
		//XSSFWorkbook workbook = new XSSFWorkbook(file);	
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
		
		//XSSFSheet sheet = workbook.getSheetAt(0);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		Cell cell;
		
		 ArrayList<ArrayList<String>> toExcel=new ArrayList<ArrayList<String>>();
		 int i=0;
	    while (rowIterator.hasNext()) {
	    	
			Row row = rowIterator.next();
			ArrayList<String> columns=new ArrayList<String>();
			Iterator<Cell> cit=row.cellIterator();
			while(cit.hasNext()){
				columns.add(cit.next().toString());
			}
			//ids.add(row.getCell(0).toString());
			if(i!=0)
				toExcel.add(columns);
			i++;
			//System.out.print(row.getCell(0).toString()+"\t");			
		}
	    
	    System.out.println(toExcel.size());
		file.close();
		
		return toExcel;
		
		/*for(int i=0;i<ids.size();i++){
			ReadReviews.createReview(ids.get(i));
		}*/
		//ReadReviews.createReview(ids.get(0));

	}
	public static void main(String args[]) throws  IOException
	{
		ReadFromExcel DT = new ReadFromExcel();
		//DT.readExcel();
	}
	
	public static ArrayList<ArrayList<String>> readFromFolder(String folderName)throws IOException{
		try{
			ArrayList<ArrayList<String>> toExcel=new ArrayList<ArrayList<String>>();
			File f=new File(folderName);
			System.out.println(f.listFiles().length);
			System.out.println(f.getAbsolutePath());
			for(File ff:f.listFiles()){
				// 	if(f.getAbsolutePath().endsWith(".xlx"))
				toExcel.addAll(readExcel(ff.getAbsolutePath()));
			}
			return toExcel;
		 }catch(InvalidFormatException ex){
			 return null;
		 }
		
	}
	
	
}