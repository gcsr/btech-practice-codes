import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AddHolidays {
	public static void main(String[] gcs){
		File f=new File("data");
		File[] fs=f.listFiles();
		String fileName;
		for(File ffs:fs){
			fileName=ffs.getName();
			if(fileName.contains("train") || fileName.contains("test"))
				addForFile(fileName);
		}
	}
	
	
	public static void addForFile(String fileName){
		try{
			ArrayList<ArrayList<String>> input=ReadExcelData.readExcel("data/"+fileName);
			ArrayList<String> temp=null;
			Day day=null;
			input.get(0).add("holiday");
			input.get(0).add("week end");
			int month;
			int year;
			int daytemp;
			for(int i=1;i<input.size();i++){
				temp=input.get(i);
				year=(int)Double.parseDouble(temp.get(1));
				month=(int)Double.parseDouble(temp.get(2));
				daytemp=(int)Double.parseDouble(temp.get(3));
				
				day=new Day(year,month,daytemp);
				if(HolidayChecker.isHoliday(day))
					temp.add(""+1);				
				else temp.add(""+0);
				temp.add(""+HolidayChecker.isWeekEnd(day));
			}
			
			String columnNames[]=new String[9];
			columnNames=input.get(0).toArray(columnNames);
			
			String[][] args=new String[input.size()-1][9];
			String row[]=new String[9];
			for(int i=1;i<input.size();i++){
				temp=input.get(i);
				row=new String[9];
				args[i-1]=temp.toArray(row);
			}
			
			WriteExcelData.writeToExcel(columnNames, args, "data/holidays/weekends/"+fileName);
			
		}catch(Exception ex){
			System.out.println(fileName);
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	
}
