import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class WriteExcelData {
	public static void writeToExcel(String[] columnNames, String[][] args,String filename) 
    {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");
          
        //This data needs to be written (Object[])
        int rownum = 0;
        Row row;
        Cell cell;
        int j=0;
        row=sheet.createRow(0);
        
        for(j=0;j<args[0].length;j++){
    		cell = row.createCell(j);
    		cell.setCellValue(columnNames[j]);
    	}
        
        System.out.println("cleared writing column names");
        
        System.out.println(args.length);
        
        for (int i=0;i<args.length;i++){
        	row = sheet.createRow(i+1);
        	for(j=0;j<args[0].length;j++){
        		cell = row.createCell(j);
        		
        		cell.setCellValue((int)Double.parseDouble(args[i][j]));
        	}
        	System.out.println(i);
        }
        
        System.out.println("cleared writing ccells");
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            System.out.println(filename+ " written successfully on disk.");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	
	public static void writeToExcels(String[] columnNames,String[][] args){
		Map<String,ArrayList<String[]>> maps=new HashMap<String,ArrayList<String[]>>();
		ArrayList<String[]> temp2;
		for(String[] temp:args){
			temp2=maps.get(temp[6]);
			if(temp2==null){
				temp2=new ArrayList<String[]>();
				temp2.add(temp);
				maps.put(temp[0], temp2);
			}else{
				temp2.add(temp);
			}
			
		}
		
		for(Map.Entry<String, ArrayList<String[]>> temp:maps.entrySet()){
			String key=temp.getKey();
			ArrayList<String[]> value=temp.getValue();
			writeFiles(key,value,columnNames);
		}
		
	}
	
	private static void writeFiles(String key,ArrayList<String[]> value,String[] columnNames){
		int trainCount=(int)(1.0*(value.size()*7)/10);
		String[][] train=new String[trainCount][columnNames.length];
		String[][] test=new String[value.size()-trainCount][columnNames.length];
		
		for(int i=0;i<trainCount;i++){
			train[i]=value.get(i);
		}
		
		int k=0;
		for(int i=trainCount;i<value.size();i++){
			test[k]=value.get(i);
			k++;
		}
		
		writeToExcel(columnNames, train,key+" train.xlsx");
		writeToExcel(columnNames, test,key+" test.xlsx");
		
		
	}
}
