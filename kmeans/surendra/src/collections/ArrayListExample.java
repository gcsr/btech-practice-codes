package collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListExample {
	public static void main(String[] gcs){
		
		int[] a=new int[10];
		Integer obj=new Integer(1);
		
		ArrayList<String> example=new ArrayList<String>();
		//example.add(obj);
		example.add("6");
		//ArrayList<int> example=new ArrayList<int>();
		
		//example.add("Surendra");
		example.get(3);
		Iterator<String> itr=example.iterator();
		while(itr.hasNext()){
			String pp=itr.next();
			//process
		}
		
		//contains
		if(example.contains("3")){
			int index=example.indexOf("3");
		}
		
		
		
		
		
		
		for(int i=0;i<100;i++){
			System.out.println(example.get(i));
		}
		
		//example.
		
	
 	}
}
