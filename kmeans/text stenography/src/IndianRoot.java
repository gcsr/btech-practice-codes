import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class IndianRoot {
	
	String msg="";
	String pass="";
	char[] chars;
	char[] encodeChars;
	Map frequencies;
	int[] intChars;
	int encoLength;
	
	public IndianRoot(String pass){
		this.pass=pass;
		constructSentence();	
		
	}
	
	public String listChars(){
		String chars="";
		System.out.println("start chars");
		System.out.println();
		for(char c:encodeChars){
			chars+=c+",";
			System.out.print((int)c+" ");
		}
		System.out.println();
		System.out.println("ended char");
		chars=chars.substring(0,chars.length()-1);
		
		return chars;
	}
	
	private void constructSentence(){
		
		byte[] bytes=pass.getBytes();
		StringBuilder binary = new StringBuilder();
		  for (byte b : bytes){
		     int val = b;
		     for (int i = 0; i < 8; i++){
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     //binary.append(' ');
		  }
		  
		  //System.out.println("'" + cs + "' to binary: " + binary);
		  
		  String s=new String(binary);
		  System.out.println(s);
		  findChars(s);
		  assignFrequencies();
		  encodeChars=new char[encoLength];
		  for(int i=0;i<encoLength;i++){
				
				encodeChars[i]=CharsTable.getChar(intChars[i],getFrequency(intChars[i]));
			}	
		  
		  for(int i=0;i<encoLength;i++){
				
				System.out.println(encodeChars[i]);
			}	
		  
		  
	}
	
	public static void main(String[] gcs){
		
		//new IndianRoot();
	}
	
	private void findChars(String s){
		
		encoLength=s.length()/4;
		
		intChars=new int[encoLength];
		for(int i=0;i<encoLength;i++){
			
			String dd=s.substring(0,4);
			intChars[i]=Integer.parseInt(dd,2);
			s=s.substring(4);
			System.out.println("substring is "+s);
		}
		
		
		
	}
	
	private void assignFrequencies(){
		
		frequencies=new HashMap<Integer,Integer>();
		System.out.println("characters are \n ");
		
		
		for(int c:intChars){
			System.out.print(c);
			if(frequencies.containsKey(c)){
				int d=(int)frequencies.get(c);
				frequencies.put(c,d+1);
			}
			else
				frequencies.put(c,1);
		}
		
		System.out.println("\nintCharacters are \n ");
		for(Object entry:frequencies.entrySet()){
			
			Map.Entry en=(Map.Entry)entry;
			System.out.println("key is "+en.getKey()+ "and value is "+en.getValue());
		}
	}
	
	
	private double getFrequency(int no){
		
		double fr=(int)frequencies.get(no)+0.0;
		return (fr/encoLength)*100;
		
	}
	
	
	

}
