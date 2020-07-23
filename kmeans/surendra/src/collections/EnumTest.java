package collections;

import java.util.EnumSet;


 	enum weekday{MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY};

public class EnumTest {
        
    public static void main(String[] args) {
    	
    	EnumSet<weekday> always=EnumSet.allOf(weekday.class);
    	for(weekday ss:always)
    		System.out.println(ss);
    	
    	
    	
    }
}

