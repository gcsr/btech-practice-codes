package collections;

//list iterator can add elements
//iterator cannot add elements




import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;


public class LinkedListTest {
    public static void main(String[] args) {
    	List<String> a=new LinkedList<String>();
    	
    	a.add("amy");
    	a.add("carl");
    	a.add("erica");
    	
    	
    	
    	List<String> b=new LinkedList<String>();
    	b.add("bob");
    	b.add("Doug");
    	b.add("frances");
    	b.add("gloria");
    	
    	ListIterator<String> alter=a.listIterator();
    	Iterator<String> bIter=b.iterator();
    	
    	
    	bIter.remove();

    	bIter.next();
    	bIter.next();
    	bIter.next();
    	bIter.next();
    	bIter.next();

		System.out.println(b);

    }
}
