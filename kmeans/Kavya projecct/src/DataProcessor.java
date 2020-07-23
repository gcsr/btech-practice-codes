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
		ArrayList<String[]> tempData=getConvertedData("temp.CSV");
		ArrayList<String[]> loadData=getConvertedData("load.CSV");
		//Map<String,String>input1= getMap(tempData);
		Map<String,String>input2= getMap(loadData);
		String[] loadArray=getFinalBeforeData(tempData,input2);
		ArrayList<String[]> finalValues=new ArrayList<String[]>();
		
		Iterator<String[]> itr=tempData.iterator();
		String[] temp=null;
		int i=0;
		String[] temp2=null;
		int counter=0;
		while(itr.hasNext()){
			temp=itr.next();
			temp2=new String[7];
			for(i=0;i<6;i++){
				temp2[i]=temp[i];
			}
			
			if(loadArray[counter]!=null && !loadArray[counter].equals("")){
				temp2[6]=loadArray[counter];//.replaceAll(",", "");
						
				finalValues.add(temp2);
			}//else continue;
			counter++;	
			//System.out.println(counter);
			//if(counter==250000)
				//break;
		}
		
		
		
		String[] columnNames=new String[]{"station","year","month","day","hour","temperature","energy"};
		
		
		String[][] finalData=new String[finalValues.size()][7];
		finalValues.toArray(finalData);
		//System.gc(finalValues);
		
		return new Epp(columnNames,finalData);
		
	}
	
	private static String[] getFinalBeforeData(ArrayList<String[]> input1,Map<String,String>input2){
		String[] result=new String[input1.size()];
		Iterator<String[]> itr=input1.iterator();
		String[] temp=null;
		int i=0;
		String temp2="";
		int counter=0;
		while(itr.hasNext()){
			temp=itr.next();
			temp2="";
			for(i=0;i<5;i++){
				temp2+=temp[i];
			}
			
			//System.out.println(temp2);
			result[counter]=input2.get(temp2);
			counter++;
			input2.remove(temp2);
		}
		System.out.println(result.length);
		return result;
	
		
	}
	
	private static Map<String,String> getMap(ArrayList<String[]> input){
		
		Map<String,String> result=new HashMap<String,String>();
		Iterator<String[]> itr=input.iterator();
		String[] temp=null;
		int i=0;
		String temp2="";
		while(itr.hasNext()){
			temp=itr.next();
			temp2="";
			for(i=0;i<5;i++){
				temp2+=temp[i];
			}
			if(temp[5]!=null && !temp[5].equals(""))
				result.put(temp2, temp[5]);
			else continue;
		}
		
		
		System.out.println(result.size());
		return result;
		
		
	}
	 
	public static ArrayList<String[]> getConvertedData(String fileName){
		
		ArrayList<ArrayList<String>> fileData=null;
		try{
			fileData=ReadFromCSV.readFromCSV(fileName);//ReadExcelData.readExcel(fileName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		ArrayList<String[]> convertedData=new ArrayList<String[]>();
		int j=0;
		Iterator<ArrayList<String>> itr=fileData.iterator();
		while(itr.hasNext()){
			ArrayList<String> temp=itr.next();
			String[] array=new String[6];
			for(j=0;j<4;j++){
				array[j]=temp.get(j);
			}
			
			for(int i=4;i<temp.size();i++){
				array[4]=(i-3)+"";
				array[5]=temp.get(i);
				convertedData.add(array.clone());
				array=new String[6];
				for(j=0;j<4;j++){
					array[j]=temp.get(j);
				}
				
			}
		}
		
		/*for(int i=0;i<30;i++){
			for(String ss:convertedData.get(i))
				System.out.print(ss+"\t");
			System.out.println();
		}*/
		
		return convertedData;
	}
}
