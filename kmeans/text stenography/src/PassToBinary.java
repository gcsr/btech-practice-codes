import java.util.Scanner;


public class PassToBinary {

	public static String getBinary(String args) {
		// TODO Auto-generated method stub
		
		
		byte[] bytes=args.getBytes();
		  StringBuilder binary = new StringBuilder();
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     //binary.append(' ');
		  }
		  
		  //System.out.println("'" + cs + "' to binary: " + binary);
		  
		  String s=new String(binary);
		  
		  return s;
		 /* char[] chs=s.toCharArray();
		  
		  int i=0;
		  
		  String sp="";
		  for(char p:chs){
			  sp+=p;
			  i++;
			  if(i%8==0){
				  System.out.print(sp);
				  printChar(sp);
				  sp="";
			  }
		  }
		  
		  System.out.println("length of the binary string is "+binary.length());*/

	}
	
	public static void printChar(String sp){
		
		int charCode=Integer.parseInt(sp,2);
		System.out.print((char)charCode);
	}
	
	
	public static void main(String[] gcs){
		
		System.out.println("enter password");
		Scanner scanner=new Scanner(System.in);
		String pass=scanner.next();
		System.out.println(getBinary(pass));
	}

}
