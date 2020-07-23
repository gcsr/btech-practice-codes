/**
 * @(#)Shuffle1.java
 *
 *
 * @author 
 * @version 1.00 2010/7/18
 */

public class Shuffle1 {
        
    /**
     * Creates a new instance of <code>Shuffle1</code>.
     */
    public Shuffle1() {
    }
    
    /**
     *this is gc
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	int x=3;
    	while(x>0)
    	{
    		if(x<0)
    			System.out.println();
    		if(x>2)
    			System.out.print("a");
    		x=x-1;
    		System.out.print("-");
    		if(x==2)
    			System.out.print("b c");
    		if(x==1)
    			System.out.print("d");
    					
    					
    	}

    }
}
