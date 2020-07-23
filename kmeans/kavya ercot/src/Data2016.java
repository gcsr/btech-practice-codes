import java.util.ArrayList;


public class Data2016 {
	public static void main(String[] gcs){
		int year=2016;
		int month=1;
		int day=1;
		int hour=1;
		 Day dayTemp=null;
		
		 ArrayList<String[]> toExcel=new ArrayList<String[]>();
		 String[] oneRecord=new String[8];
		while(true){
			oneRecord=new String[8];
			System.out.println(year+" "+month+" "+day+" "+hour);
			oneRecord[0]=""+year;
			oneRecord[1]=""+month;
			oneRecord[2]=""+day;
			oneRecord[3]=""+hour;
			dayTemp=new Day(year,month,day);
			if(HolidayChecker.isHoliday(dayTemp))
					oneRecord[4]=""+1;				
			else oneRecord[4]=""+0;
				oneRecord[5]=""+HolidayChecker.isWeekEnd(dayTemp);
			oneRecord[6]="1";
			oneRecord[7]="0";
			 toExcel.add(oneRecord.clone());
			hour++;
			if(hour==25){
				hour=1;
				day++;
			}
			if(day==30 || day==31|| day==32){
				if(getMonthDays(month)==(day-1)){
					month++;
					day=1;
				}
			}
			
			
			
			if(month==13)
				break;
		}
		
		String[] columnNames=new String[]{"year","month","day","hour","holiday","weekend","station","energy"};
		
		String[][] finalData=new String[toExcel.size()][6];
		toExcel.toArray(finalData);
		WriteExcelData.writeToExcel(columnNames, finalData,"prediction.xlsx");
		
	}
	
	private static int getMonthDays(int month){
		switch(month){
			case 1: return 31;
			case 2:	return 29;					
			case 3: return 31;
			case 4: return 30;
			case 5: return 31;
			case 6: return 30;
			case 7: return 31;
			case 8: return 31;
			case 9: return 30;
			case 10: return 31;
			case 11: return 30;
			case 12: return 31;			
			default: return -1;
		}
	}
	
}
