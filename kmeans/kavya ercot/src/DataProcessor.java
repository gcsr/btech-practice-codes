import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DataProcessor {
	
	static class Epp{
		public Epp(String[] columnNames,String[][] args){
			this.columnNames=columnNames;
			this.args=args;
		}
		String[] columnNames;
		String[][] args;
	}
	public static void main(String[] gcs){
		System.out.println("before writing");
		Epp app=getData();
		WriteExcelData.writeToExcels(app.columnNames, app.args);
		//WriteExcelData.writeToExcel(app.columnNames, app.args, "out.xlsx");
		System.out.println("after writing");
		//System.out.println(tempData.size());
		//System.out.println(loadData.size());
	}
	
	public static Epp getData(){
		ArrayList<String[]> tempData=new ArrayList<String[]>();
		
		File f=new File("ercot/data");
		File[] fs=f.listFiles();
		String fileName;
		try{
			for(File ffs:fs){
				if(ffs.getName().contains("2016"))
				tempData.addAll(ReadExcelData.readExcel(ffs.getAbsolutePath()));			
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		String[] columnNames=new String[]{"year","month","day","hour","holiday","weekend","station","energy"};
		
		String[][] finalData=new String[tempData.size()][6];
		tempData.toArray(finalData);
		
		return new Epp(columnNames,finalData);
		
	}
	
}
