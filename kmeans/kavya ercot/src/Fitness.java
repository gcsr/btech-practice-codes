import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class Fitness {
	public static ArrayList<Product> readExcel(String fileName) throws Exception {

		
		System.out.println(fileName);
		FileInputStream file = new FileInputStream(new File(fileName));
		int[] ks = new int[]{1,2,3,4,5};

		// Create Workbook instance holding reference to .xlsx file
		// XSSFWorkbook workbook = new XSSFWorkbook(file);
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory
				.create(file);

		// XSSFSheet sheet = workbook.getSheetAt(0);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();

		ArrayList<String> transactions = new ArrayList<String>();
		
		int i = 0;
		
		double fitness = 0;
		
		ArrayList<Product> products = new ArrayList<Product>();
		Product p = null;
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			// ArrayList<String> columns=new ArrayList<String>();
			  Iterator<Cell> cit = row.cellIterator();
			// ids.add(row.getCell(0).toString());

			fitness = 0;
			if (i != 0) {
				//String transactionID = row.getCell(0).toString();
				
				for(int j=0; j< ks.length ;j++){
					fitness += Integer.parseInt(row.getCell(j+1).toString())*ks[j];					
				}
				
				p = new Product();
				p.setFitness(fitness);
				p.setId(i);
				products.add(p);
				

			}
			i++;
			// System.out.print(row.getCell(0).toString()+"\t");
		}
		Collections.sort(products);
		return products;

	}

	public static void calculateSupports(HashMap<String, Integer> products,
			int numberOFTransactions) {

		String[] columnHeaders = { "Product Id", "2 Gb Ram",
				"32 Gb HDD", "OTG", "Front Flash", "Dual Sim" };
		String[][] columns = new String[products.size()][6];

		int i = 0;
		
		Random rand = new Random();

		String[] str = { "pcid01", "pcid02", "pcid03", "pcid04", "pcid05" };
		
		int x = 11;
		for (Map.Entry<String, Integer> product : products.entrySet()) {
			
	
			
			
		/*	idx = new Random().nextInt(str.length);*/
			
			


		 
			columns[i][0] = "" + "prod" + x++; 
			
			columns[i][1] = "" + rand.nextInt(2); 
			columns[i][2] = ""+  rand.nextInt(2);
			columns[i][3] = "" +  rand.nextInt(2);
			columns[i][4] = "" +  rand.nextInt(2);
			columns[i][5] = "" +  rand.nextInt(2);
			i++;
			
			
			
			//i++;
		}

		WriteExcelDemo.writeToExccel(columnHeaders, columns, "C:\\Users\\vinay.j\\Desktop\\talend output\\support.xlsx");
	}

	

	public static void main(String args[]) {
		String input = "C:\\Users\\gc\\Desktop\\generate excel files code\\generate excel files code\\support.xlsx";
		ArrayList<Product> products = null;
		try {
			products = readExcel(input);
		} catch (Exception exx) {
			exx.printStackTrace();
		}
		
		System.out.println("Enter product for which to calculate the nearest neighbours");
		System.out.println("Sample inputs 1 or 2 or .........n");
		Scanner scanner = new Scanner(System.in);
		int productNo = scanner.nextInt();
		printProducts(productNo, products);
		
	}
	
	public static void printProducts(int productNo, ArrayList<Product> products){
		int index =-100;
		Iterator<Product> itr = products.iterator();
		
		int counter =0 ;
		Product p = null;
		while(itr.hasNext()){
			
			p = itr.next();
			if(p.getId()==productNo){
				index = counter;
				break;
			}
			counter++;
		}
		
		ArrayList<Product> finalResult = null;
		if(index >= 0)
			finalResult = getProducts(index, products);			
		
		if(finalResult.size() >0){
			itr = finalResult .iterator();
			
			p = null;
			System.out.println("Nearest Neighbours are products");
			while(itr.hasNext()){
				
				p = itr.next();
				System.out.print(p.getId()+"\t");
			}	
			System.out.println();
		}else{
			System.out.println("No products to Display");
		}
		
	}
	
	public static ArrayList<Product> getProducts(int index, ArrayList<Product> products){
		
		ArrayList<Product> result = new ArrayList<Product>();
		int top = index;
		top--;
		while(top>=0 && top >= (index-2)){
			result.add(products.get(top));
			top--;
		}
		
		int bottom = index;
		bottom++;
		while(bottom < products.size() && bottom <= (index+2)){
			result.add(products.get(bottom));
			bottom++;
		}
		
		return result;
	}
	
	

}