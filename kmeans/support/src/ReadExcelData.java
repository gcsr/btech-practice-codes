import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import jxl.read.biff.BiffException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.DateTime;


public class ReadExcelData {
	
	public static ArrayList<String[]> readExcel(String fileName) throws BiffException, IOException,InvalidFormatException{
		
		//"C:\\Users\\gc\\Desktop\\yashwanth\\data\\central excise circulars\\columns.xlsx"));
		FileInputStream file = new FileInputStream(new File(fileName));

		
		//Create Workbook instance holding reference to .xlsx file
		/*HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet worksheet = workbook.getSheetAt(0);
		//Iterate through each rows one by one
		Iterator<Row> rowIterator = worksheet.iterator();
		Cell cell;
		*/
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
		
		//XSSFSheet sheet = workbook.getSheetAt(0);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		Cell cell;
	
		
		 ArrayList<String[]> toExcel=new ArrayList<String[]>();
		 String[] oneRecord=new String[5];
		 DateTime dateTime=null;
		
		 int rowCounter=0;
		 try{
			 	 while (rowIterator.hasNext()) {
			 		 oneRecord=new String[8];			 		 
			 		 Row row = rowIterator.next();
			 		 if(rowCounter==0){
			 			 rowCounter++;
			 			 continue;
			 		 }
			 		 Date tempCell=null;
			 		 
			 		 Iterator<Cell> cit=row.cellIterator();
			 		 int counter=0;
			 		 int columnCounter=0;
			 		 int year,month,day;
			 		 Day dayTemp=null;
			 		 while(cit.hasNext()){
			 			 if(counter==0){
			 				 tempCell=cit.next().getDateCellValue();
			 				 
			 				 dateTime = new DateTime(tempCell);
			 				dateTime=dateTime.minus(10000);
			 				 year=dateTime.getYear();
			 				 oneRecord[counter++]=""+year;
			 				 month=dateTime.getMonthOfYear();
			 				 oneRecord[counter++]=""+month;
			 				 day=dateTime.getDayOfMonth();
			 				 oneRecord[counter++]=""+day;
			 				 oneRecord[counter++]=""+(dateTime.getHourOfDay()+1);
			 				 //System.out.println(""+year+" "+month+" "+day);
			 				//System.out.println(tempCell);
			 				 
			 				 dayTemp=new Day(year,month,day);
			 				if(HolidayChecker.isHoliday(dayTemp))
			 					oneRecord[counter++]=""+1;				
							else oneRecord[counter++]=""+0;
			 				oneRecord[counter]=""+HolidayChecker.isWeekEnd(dayTemp);
			 			 }else{
			 				 oneRecord[counter+1]=""+(columnCounter);
			 				 oneRecord[counter+2]=""+cit.next().toString();
			 				 toExcel.add(oneRecord.clone());
			 			 }	
			 			 columnCounter++;			 			 
			 		 }
			 		 //	ids.add(row.getCell(0).toString());
			 		
			 		 //	System.out.print(row.getCell(0).toString()+"\t");			
			 	 }
		 }catch(Exception ex){
			 ex.printStackTrace();
		 		//System.out.println(temp); 
		 }
	    System.out.println(toExcel.size());
		file.close();
		
		return toExcel;
		
		/*for(int i=0;i<ids.size();i++){
			ReadReviews.createReview(ids.get(i));
		}*/
		//ReadReviews.createReview(ids.get(0));

	}
	
	public static void main(String[] gcs){
		try{
			ArrayList<String[]> data=readExcel("ercot/2011.xls");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


}
