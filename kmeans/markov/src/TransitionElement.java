
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionElement {

	String currentElement;
	ArrayList<String> nextElements=new ArrayList<String>();
	ArrayList<Integer> counts=new ArrayList<Integer>();
	
	public TransitionElement(String currentElement){
		this.currentElement=currentElement;
	}
	
	
	
	public void addCount(String nextElement){
		Iterator<String> iterator=nextElements.iterator();
		int i=0;
		
		while(iterator.hasNext()){
			if(iterator.next().equals(nextElement))
				break;
			i++;
		}
		
		if(i==nextElements.size()){
			nextElements.add(nextElement);
			counts.add(1);
		}else{
			counts.set(i,(counts.get(i)+1));
		}
	}
	public int getCount(String nextElement){
		Iterator<String> iterator=nextElements.iterator();
		int i=0;
		
		while(iterator.hasNext()){
			if(iterator.next().equals(nextElement))
				break;
			i++;
		}
		
		if(i==nextElements.size()){
			return 0;
		}else{
			return counts.get(i);
		}
	}
	
	

}
