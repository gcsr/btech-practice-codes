import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class HolidayChecker {
	
	private static List<Day> holidays=new LinkedList<Day>();
	private static List<Day> weekEnds=new ArrayList<Day>();
	
	public static boolean isHoliday(Day input){
		if(holidays.contains(input))
			return true;
		return false;
	}
	
	static{
		try{
			for(int year=2004;year<2016;year++){
				//"C:\\Users\\gc\\Desktop\\yashwanth\\data\\central excise circulars\\columns.xlsx"));
				FileInputStream file = new FileInputStream(new File("data/"+year+".xlsx"));

				//	Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(file);		
				
				XSSFSheet sheet = workbook.getSheetAt(0);
				//Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				Cell cell;
				
				ArrayList<ArrayList<String>> toExcel=new ArrayList<ArrayList<String>>();
				int counter=0;
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					//	ArrayList<String> columns=new ArrayList<String>();
					//	Iterator<Cell> cit=row.cellIterator();
					if(counter!=0){
						String data=row.getCell(0).toString();
						String[] split=data.split(" ");
						holidays.add(new Day(year,getMonth(split[0]),Integer.parseInt(split[1])));
					}
					counter++;
					//		toExcel.add(columns);
					//	System.out.print(row.getCell(0).toString()+"\t");			
				}
	    
				System.out.println(toExcel.size());
				file.close();
			}
			getWeekEnds();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//return toExcel;
		
	}
	
	private static void getWeekEnds(){
	
		int days=0;
		
		int value=0;
		
		for(int year=2000;year<2017;year++){
			for(int month=1; month<13;month++){
				days=getMonthDays(year,month);
				for(int counter=1;counter<=days;counter++){
					value++;
					if((value%7)==1 || (value%7)==2){
						weekEnds.add(new Day(year,month,counter));
					}//if(value==1)
						//System.out.println("called "+value%7+" "+isWeekEnd(new Day(year,month,counter)));
				}
			}
		}
		//return weekEnds;
		
	}
	private static int getMonthDays(int year,int month){
		switch(month){
			case 1: return 31;
			case 2:if(year%4==0)
						return 29;
					else return 28;
			case 3: return 31;
			case 4: return 30;
			case 5: return 31;
			case 6: return 30;
			case 7: return 31;
			case 8: return 31;
			case 9: return 30;
			case 10: return 31;
			case 11: return 31;
			case 12: return 30;			
			default: return -1;
		}
	}
	
	public static int isWeekEnd(Day input){
		int returnValue=weekEnds.indexOf(input);
		if(returnValue<0)
			return 0;
		return 1;
	}
	
	public static int getMonth(String input){
		switch(input){
			case "Jan":return 1;
			case "Feb":return 2;
			case "Mar":return 3;
			case "Apr":return 4;
			case "May":return 5;
			case "Jun":return 6;
			case "Jul":return 7;
			case "Aug":return 8;
			case "Sep":return 9;
			case "Oct":return 10;
			case "Nov":return 11;
			case "Dec":return 12;
			default: return -1;
		
		}
	}
	
	

}
