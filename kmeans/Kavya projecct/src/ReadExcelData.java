import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import jxl.read.biff.BiffException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelData {
	
	public static ArrayList<ArrayList<String>> readExcel(String fileName) throws BiffException, IOException{
		ArrayList<String> ids=new ArrayList<String>();
		//"C:\\Users\\gc\\Desktop\\yashwanth\\data\\central excise circulars\\columns.xlsx"));
		FileInputStream file = new FileInputStream(new File(fileName));

		//Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);		
		
		XSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		Cell cell;
		
		 ArrayList<ArrayList<String>> toExcel=new ArrayList<ArrayList<String>>();
	    while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			ArrayList<String> columns=new ArrayList<String>();
			Iterator<Cell> cit=row.cellIterator();
			while(cit.hasNext()){
				columns.add(cit.next().toString());
			}
			//ids.add(row.getCell(0).toString());
			toExcel.add(columns);
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


}
