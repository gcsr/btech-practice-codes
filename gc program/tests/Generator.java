/**
 * @(#)Generator.java
 *
 *
 * @author 
 * @version 1.00 2011/1/5
 */
import  java.lang.Math;

public class Generator {
        
    /**
     * Creates a new instance of <code>Generator</code>.
     */
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	long  ashok;
    	ashok=(long)Math.pow(26,5);
    	
    	for(long i=ashok;i>0;i--)
    	{
    		long j=i;
    		String s="";
    		while(j>0)
    	{
    		long sp=(j)%26;
    		j=j/26;
    		s+=(char)(sp+65);
    		
    		
    	}
    	System.out.println(s);
    	}
        // TODO code application logic here
    }
}
