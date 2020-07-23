package collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class SetExxample {
	public static void main(String[] gcs){
		Set set;
		
		HashSet<String> hashSet=new HashSet<String>();
		
		hashSet.add("Surendra");
		hashSet.add("gc");
		hashSet.add("gc2");
		hashSet.add("Surendra2");
		hashSet.add("a");
		
		boolean added=hashSet.add("gc");
		System.out.println("addition status "+added);
		//System.out.println("addition status "+hashSet);
		
		
		Iterator<String> iterator=hashSet.iterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next());
			
		}
		
	}
}
