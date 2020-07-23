import java.util.PriorityQueue;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class PriorityQueueTest {
        
    
    public static void main(String[] args) {
    	PriorityQueue<GregorianCalendar> pq=new PriorityQueue<GregorianCalendar>();
    	pq.add(new GregorianCalendar(1906,Calendar.DECEMBER,9));
 		pq.add(new GregorianCalendar(1815,Calendar.DECEMBER,10));
 		pq.add(new GregorianCalendar(1903,Calendar.DECEMBER,3));
 		
 		System.out.println("iterating through elements...");
 		
 		
 		for(GregorianCalendar date:pq)
 			System.out.println(date.get(Calendar.YEAR));
 			
 			System.out.println("removing elements");
 			
 			
 			
    }		
 		
 		

}
