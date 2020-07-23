//removes the last element returned by the iterator

import java.util.LinkedList;
import java.util.List;

import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
public class SetTest {
        
    public static void main(String[] args) {
   		
    	Set<String> words=new HashSet<String>();

   		String word="gc";
   		 	
		for(int i=0;i<20;i++)
		{
			word="gc"+i;
    		words.add(word);

		}
		
		Iterator<String> iter=words.iterator();
		
		
		System.out.println(iter.next().hashCode());
    		
    	
    	
    		
    	List<String> a=new LinkedList<String>();
    	
    	a.add("amy");
    	a.add("carl");
    	a.add("erica");
    	
    		
    	Set<String> morewords=new HashSet<String>(a);

    	

    		
    	
    }
}
