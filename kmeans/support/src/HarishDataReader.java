
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class HarishDataReader {
	
	public static void readExcel(String fileName)throws InvalidFormatException, IOException{
		
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
		
		 ArrayList<String> transactions=new ArrayList<String>();
		 HashMap<String, Integer> products = new HashMap<String, Integer>();
		 int i=0;
	    while (rowIterator.hasNext()) {
	    	
			Row row = rowIterator.next();
			//ArrayList<String> columns=new ArrayList<String>();
			Iterator<Cell> cit=row.cellIterator();
			//ids.add(row.getCell(0).toString());
			
			if(i!=0){
				String transactionID = row.getCell(0).toString();
				String productID = row.getCell(1).toString();
				if(! transactions.contains(transactionID)){
					transactions.add(transactionID);
				}
				
				Integer numberOfTransactions = products.get(productID);
				if(numberOfTransactions == null){
					products.put(productID, 1);
				}else{
					products.put(transactionID, products.get(productID)+1);
				}
				
			}
			i++;
			//System.out.print(row.getCell(0).toString()+"\t");			
		}
	    
	

	}
	
	public void calculateSupports(HashMap<String, Integer> products, int numberOFTransactions){
		
		String[] columnHeaders={"Product Id", "Number Of Transactions", "Support" };
		String[][] columns=new String[products.size()][3];
		
		int i=0;
		
		for(Map.Entry<String, Integer> product : products.entrySet()){
			columns[i][0]= product.getKey();
			columns[i][1]= ""+product.getValue();
			columns[i][2]= ""+(product.getValue()/numberOFTransactions);
			
		}
		
		WriteExcelData.writeToExcel(columnHeaders, columns, "support.xlsx");
	}
	public static void main(String args[]) {
		HarishDataReader DT = new HarishDataReader();
		String input = "input.xlsx" ;
		try{
			DT.readExcel(input);
		}catch(Exception exx){
			
		}
	}
	


}
